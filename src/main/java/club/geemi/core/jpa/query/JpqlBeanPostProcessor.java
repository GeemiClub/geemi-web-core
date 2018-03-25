package club.geemi.core.jpa.query;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

/**
 * @version V1.0
 * @package club.geemi.club.geemi.core.jpa
 * @description:
 * @author: Geemi @Everlin
 * @date: 2017/12/2 15:49
 * Copyright©2017 All rights reserved.
 */
public class JpqlBeanPostProcessor implements RepositoryProxyPostProcessor {

    @Override
    public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {
        factory.addAdvice(new JpqlBeanMethodInterceptor(repositoryInformation));
    }

}