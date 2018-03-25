package club.geemi.core.interceptor;

import club.geemi.core.BusinessException;
import club.geemi.core.RestResponse;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.interceptor
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/10 15:11
 * Copyright©2018 All rights reserved.
 */
@ControllerAdvice
public class GmGlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 业务异常处理
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {BusinessException.class})
    public ModelAndView handleBusinessException(HttpServletRequest request,
                                                HttpServletResponse response, BusinessException ex) {
        return resolveException(request, response, ex);
    }

    /**
     * 运行时异常
     *
     * @param request
     * @param response
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public ModelAndView handleAppRuntimeException(HttpServletRequest request,
                                                  HttpServletResponse response, RuntimeException ex) {
        return resolveException(request, response, ex);
    }

    protected ModelAndView resolveException(HttpServletRequest request,
                                            HttpServletResponse response, Exception ex) {
        log.error(ex.getMessage(), ex);
        response.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            RestResponse error;
            if (ex instanceof RuntimeException) {
                RuntimeException run = (RuntimeException) ex;
                error = RestResponse.error(-1L, run);
            } else if (ex instanceof BusinessException) {
                BusinessException busi = (BusinessException) ex;
                error = RestResponse.error(busi.getCode(), busi.getMessage());
            } else {
                error = RestResponse.error(-1L, ex.getMessage());
            }
            writer.write(JSON.toJSONString(error));
            writer.flush();
        } catch (IOException e1) {
            log.error(ex.getMessage(), e1);
        }
        return null;
    }
}
