package com.jnote.springcore.service.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author bowen.yan
 * @since 2020-05-13
 */
@Slf4j
//@Component
public class GlobalProcessService implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("GlobalProcessService ------> BeanPostProcessor.postProcessBeforeInitialization() executed! " +
            String.format("bean:%s,beanName:%s", bean, beanName));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("GlobalProcessService ------> BeanPostProcessor.postProcessAfterInitialization() executed! " +
            String.format("bean:%s,beanName:%s", bean, beanName));
        return bean;
    }
}
