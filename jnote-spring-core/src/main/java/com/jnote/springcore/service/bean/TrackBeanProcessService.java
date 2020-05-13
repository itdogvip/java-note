package com.jnote.springcore.service.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author bowen.yan
 * @since 2020-05-13
 */
@Slf4j
@Service
public class TrackBeanProcessService implements InitializingBean {
    public TrackBeanProcessService() {
        log.info("TrackBeanProcessService ------> constructor executed!");
    }

    @PostConstruct
    public void init() {
        log.info("TrackBeanProcessService ------> @PostConstruct executed!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("TrackBeanProcessService ------> InitializingBean.afterPropertiesSet() executed!");
    }
}
