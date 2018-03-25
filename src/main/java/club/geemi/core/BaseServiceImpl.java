package club.geemi.core;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static club.geemi.utils.CheckUtils.*;

/**
 * @version V1.0
 * Copyright©2017 All rights reserved.
 * @package club.geemi.club.geemi.core
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/11/19 17:06
 */
public class BaseServiceImpl<E, ID extends Serializable, R extends JpaRepository<E, ID>> implements BaseService<E, ID> {

    @Autowired
    protected R repository;

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E get(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public List<E> find(E entity) {
        return repository.findAll(Example.of(entity));
    }

    @Override
    public Page<E> find(E entity, int pageNo, int pageSize) {
        Pageable page = new PageRequest(pageNo - 1, pageSize);
        return repository.findAll(Example.of(entity), page);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

    @Override
    public void delete(List<ID> ids) {
        assertNotEmpty(ids, "入参不允许为空！");
        for (ID id : ids) {
            repository.delete(id);
        }
    }


    protected <K> Page<K> transferPage(Page<E> topics, Class<K> clazz) {
        if (topics == null || topics.getTotalElements() <= 0) {
            return new PageImpl<K>(new ArrayList<>(), new PageRequest(topics.getNumber(), topics.getSize()), topics.getTotalElements());
        }
        List<K> contents = new ArrayList<>((int) topics.getTotalElements());
        for (E entity : topics.getContent()) {
            try {
                contents.add(BeanUtils.instantiateClass(clazz.getConstructor(entity.getClass()), entity));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                continue;
            }
        }
        return new PageImpl<K>(contents, new PageRequest(topics.getNumber(), topics.getSize()), topics.getTotalElements());
    }

}
