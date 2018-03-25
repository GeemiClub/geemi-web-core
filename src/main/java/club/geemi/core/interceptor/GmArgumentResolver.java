package club.geemi.core.interceptor;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;
import java.util.Map;

import static club.geemi.utils.CheckUtils.isNull;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 22:09
 * Copyright©2017 All rights reserved.
 */
public class GmArgumentResolver implements HandlerMethodArgumentResolver {

    private ConversionService conversionService;

    public GmArgumentResolver() {
    }

    public GmArgumentResolver(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class<?> clazz = methodParameter.getParameterType();
        Object param = BeanUtils.instantiate(clazz);
        String objectName = methodParameter.getParameterName() + ".";
        Method[] methods = clazz.getMethods();
        Map<String, String[]> queryParams = nativeWebRequest.getParameterMap();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String p = club.geemi.utils.BeanUtils.lowerFirstLetter(method.getName().substring(3));
                if (!queryParams.containsKey(objectName + p)) {
                    continue;
                }
                Class[] arguments = method.getParameterTypes();
                if (arguments.length != 1) {
                    continue;
                }
                method.invoke(param, getParam(arguments[0], queryParams, objectName + p));
            }
        }
        return param;
    }

    private Object getParam(Class type, Map<String, ? extends Object> params, String key) {
        if (isNull(conversionService)) {
            return params.get(key);
        }
        return conversionService.convert(params.get(key), type);
    }
}
