package club.geemi.utils;

import club.geemi.core.BusinessException;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.utils
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/10 17:01
 * Copyright©2018 All rights reserved.
 */
public class CheckUtils {

    public static final boolean isNull(Object object){
        return object == null;
    }

    public static final void assertNotNull(Object object, String message){
        if(isNull(object)){
            throw new BusinessException(message);
        }
    }

    public static final boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    public static final boolean isEmpty(Collection collection){
        return collection == null || collection.size() <= 0;
    }

    public static final boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }

    public static final void assertNotEmpty(Collection collection, String message){
        if(isEmpty(collection)){
            throw new BusinessException(message);
        }
    }
}
