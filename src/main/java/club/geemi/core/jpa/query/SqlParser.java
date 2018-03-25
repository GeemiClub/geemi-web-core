package club.geemi.core.jpa.query;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.jpa.query
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/12/2 18:17
 * Copyright©2017 All rights reserved.
 */
public interface SqlParser {
    /**
     * 解析SQL中的查询返回字段，若不存在则返回null
     *
     * @param sql
     * @param clazz
     * @return
     */
    SelectAlias getAlias(String sql, Class clazz);
}
