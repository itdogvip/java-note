package com.jnote.ttl.contoller;

import com.jnote.ttl.constant.KeyConstant;
import com.jnote.ttl.constant.TtlMiscTitle;
import com.jnote.ttl.service.TtlBizService;
import com.jnote.ttl.util.LogUtil;
import org.slf4j.MDC;
import org.slf4j.TtlMDCAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bowen.yan
 * @since 2020-04-28
 */
@RequestMapping("/")
@RestController
public class TtlBizController {
    @Autowired
    private TtlBizService ttlBizService;

    // 主线程赋值，子线程获取值，同时logback.xml中 %X{userFlagKey}也可以获取值
    @RequestMapping("logback/async")
    public String testLogback() {
        TtlMiscTitle title = TtlMiscTitle.testLogback;
        // 当前登录的用户
        MDC.put(KeyConstant.USER_SESSION_KEY, "TRANSMITTABLE-THREAD-LOCAL-USER");
        LogUtil.log(title, Thread.currentThread(), MDC.get(KeyConstant.USER_SESSION_KEY));

        return ttlBizService.testLogback();
    }

    /**
     * 主线程赋值，子线程获取值，同时logback.xml中 %X{userFlagKey}也可以获取值
     * {@link TtlMDCAdapter#put(String, String)}
     *
     * @return
     */
    @RequestMapping("logback/thread-pool")
    public String testLogbackThreadPool() {
        TtlMiscTitle title = TtlMiscTitle.testLogbackThreadPool;

        MDC.put(KeyConstant.USER_SESSION_KEY, "TRANSMITTABLE-THREAD-LOCAL-THREAD-POOL-USER");
        LogUtil.log(title, Thread.currentThread(), MDC.get(KeyConstant.USER_SESSION_KEY));

        return ttlBizService.testLogbackThreadPool();
    }
}
