package club.geemi.core;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 16:55
 * Copyright©2017 All rights reserved.
 */
public interface BaseService<E, ID extends Serializable> {

    E save(E entity);

    E get(ID id);

    List<E> findAll();

    List<E> find(E entity);

    Page<E> find(E entity, int pageNo, int pageSize);

    void delete(ID id);

    void delete(List<ID> ids);
}
