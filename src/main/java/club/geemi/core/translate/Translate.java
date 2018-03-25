package club.geemi.core.translate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.translate
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/10 16:38
 * Copyright©2018 All rights reserved.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Translate {
}
