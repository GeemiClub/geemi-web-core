package club.geemi.core.translate;

import club.geemi.core.RestResponse;
import club.geemi.utils.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import static club.geemi.utils.CheckUtils.*;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.interceptor
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/10 15:40
 * Copyright©2018 All rights reserved.
 */
@Component
@Aspect
@Order(2)
public class GmTranslateHandler {

    @Autowired
    private MessageSource messageSource;

    @Around("@annotation(club.geemi.core.translate.Translate)")
    public Object translatePointCut(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        if (result instanceof RestResponse) {
            handleTranslate((RestResponse) result, request.getLocale());
        }
        return result;
    }

    protected void handleTranslate(RestResponse response, Locale locale) {
        Object output = response.getData();
        if (output == null) {
            return;
        } else if (output instanceof Collection) {
            JSONArray array = new JSONArray();
            translateCollection((Collection) output, array, locale);
            response.setData(array);
        } else if (output instanceof Page) {
            JSONObject result = objectToJson(output);
            translateObject(output, result, locale);
            response.setData(result);
        } else {
            JSONObject result = objectToJson(output);
            translateObject(output, result, locale);
            response.setData(result);
        }
    }

    private JSONObject objectToJson(Object output) {
        return JSON.parseObject(JSON.toJSONString(output));
    }

    private void translateObject(Object object, JSONObject result, Locale locale) {
        List<Field> fields = BeanUtils.getFields(object.getClass());
        for (Field field : fields) {
            GmDict dict = field.getAnnotation(GmDict.class);
            if (isNull(dict)) {
                translateArray(field, object, result, locale);
                continue;
            }
            Object value = getFieldValue(object, field);
            if (isBlank(dict.defaultMessage()) && isNull(value)) {
                continue;
            }
            result.put("_" + field.getName(), isBlank(dict.defaultMessage()) ?
                    translateNotNull(dict.key(), value, locale) : translate(dict.key(), value, dict.defaultMessage(), locale));
        }
    }

    private void translateArray(Field field, Object object, JSONObject result, Locale locale) {
        Class type = field.getType();
        if (!Collection.class.isAssignableFrom(type)) {
            return;
        }
        JSONArray array = result.getJSONArray(field.getName());
        if (isNull(array)) {
            return;
        }
        array.clear();
        translateCollection((Collection) getFieldValue(object, field), array, locale);
    }

    private void translateCollection(Collection value, JSONArray result, Locale locale) {
        for (Object item : value) {
            JSONObject itemResult = objectToJson(item);
            translateObject(item, itemResult, locale);
            result.add(itemResult);
        }
    }

    private String translateNotNull(String key, Object value, Locale locale) {
        return translate(key, value, value == null ? null : value.toString(), locale);
    }

    private String translate(String key, Object value, String defaultMessage, Locale locale) {
        String message = translate(key, value, locale);
        return isNull(message) ? defaultMessage : message;
    }

    private String translate(String key, Object value, Locale locale) {
        try {
            return isNull(value) ? null : messageSource.getMessage(key + "." + value, null, locale);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取指定字段的值，找不到该字段时返回null
     *
     * @param object
     * @param field
     * @return
     */
    private Object getFieldValue(Object object, Field field) {
        Object value = null;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            value = null;
        }
        return value;
    }
}
