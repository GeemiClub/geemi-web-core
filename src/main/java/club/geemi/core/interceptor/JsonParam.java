package club.geemi.core.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.interceptor
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 22:14
 * Copyright©2017 All rights reserved.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {
}
