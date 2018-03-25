package club.geemi.core.translate;

import java.lang.annotation.*;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.translate
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/10 16:16
 * Copyright©2018 All rights reserved.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmDict {

    String key();

    String defaultMessage() default "";

}
