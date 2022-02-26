package com.k2tech.ehcacheprac.components;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomBeanProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
//        System.out.println("Calling bean post processor before init for bean:: "+beanName + "class name: "+bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
//        System.out.println("Calling bean post processor after init for bean:: "+beanName + "class name: "+bean.getClass());
        return bean;
    }
}
