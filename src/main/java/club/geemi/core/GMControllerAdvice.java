package club.geemi.core;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 22:00
 * Copyright©2017 All rights reserved.
 */
//@ControllerAdvice
public class GMControllerAdvice {

   //@InitBinder
    public void initBinder(WebDataBinder binder, NativeWebRequest request) throws Exception {
        //binder.registerCustomEditor();
    }

}
