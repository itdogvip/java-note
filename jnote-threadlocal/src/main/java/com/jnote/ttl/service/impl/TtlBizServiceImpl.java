package com.jnote.ttl.service.impl;

import com.jnote.ttl.constant.KeyConstant;
import com.jnote.ttl.constant.TtlMiscTitle;
import com.jnote.ttl.service.TtlBizService;
import com.jnote.ttl.util.LogUtil;
import org.slf4j.MDC;
import org.slf4j.TtlMDCAdapter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TtlBizServiceImpl implements TtlBizService {
    @Override
    public String testLogback() {
        TtlMiscTitle title = TtlMiscTitle.testLogback;
        Thread thread = new Thread(() -> {
            LogUtil.log(title, Thread.currentThread(), MDC.get(KeyConstant.USER_SESSION_KEY));
            System.out.println();
            MDC.remove(KeyConstant.USER_SESSION_KEY);
        });
        thread.start();

        return title.toString();
    }

    /**
     * 主线程赋值，子线程获取值，同时logback.xml中 %X{userFlagKey}也可以获取值
     * {@link TtlMDCAdapter#get(String)}
     *
     * @return
     */
    @Async("taskExecutorTtl")
    @Override
    public String testLogbackThreadPool() {
        TtlMiscTitle title = TtlMiscTitle.testLogbackThreadPool;

        LogUtil.log(title, Thread.currentThread(), MDC.get(KeyConstant.USER_SESSION_KEY));
        System.out.println();
        MDC.remove(KeyConstant.USER_SESSION_KEY);

        return title.toString();
    }
}
