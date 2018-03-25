package club.geemi.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.utils
 * @description:
 * @author: Geemi @Everlin
 * @date: 2018/3/10 22:38
 * Copyright©2018 All rights reserved.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    private static final String stringClassName = String.class.getName();
    private static final String toSplit = "->";

    public static Map<String, Converter> converterMap = new HashMap() {
        {
            put(stringClassName + toSplit + Long.class.getName(), new Converter() {
                @Override
                public Long convert(String value, Class targetType) {
                    return Long.valueOf(value);
                }
            });
            put(stringClassName + toSplit + Integer.class.getName(), new Converter() {
                @Override
                public Integer convert(String value, Class targetType) {
                    return Integer.valueOf(value);
                }
            });
        }
    };

    public static <T> List<T> split(String str, String separator, Class<T> type) {
        Converter converter = converterMap.get(String.class.getName() + toSplit + type.getName());
        if (CheckUtils.isNull(converter)) {
            throw new IllegalArgumentException("不能将字符创转换为类型" + type.getName());
        }
        List<T> result = new ArrayList<>();
        for (String s : split(str, separator)) {
            result.add(converter.convert(s, type));
        }
        return result;
    }

    abstract static class Converter {
        public abstract <T> T convert(String value, Class targetType);
    }
}
