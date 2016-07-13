package org.spring.hello;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *
 * @author Constantin Alin
 */
public class InitHelloWorld implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Executing postProcessBeforeInitialization() for bean id '" + beanName + "'");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Executing postProcessAfterInitialization() for bean id '" + beanName + "'");
        return bean;
    }

}
