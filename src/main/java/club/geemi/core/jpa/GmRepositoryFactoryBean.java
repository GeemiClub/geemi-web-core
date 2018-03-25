package club.geemi.core.jpa;

import club.geemi.core.jpa.query.JpqlBeanPostProcessor;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.jpa
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/12/2 15:52
 * Copyright©2017 All rights reserved.
 */
public class GmRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    public GmRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        jpaRepositoryFactory.addRepositoryProxyPostProcessor(new JpqlBeanPostProcessor());
        return jpaRepositoryFactory;
    }
}