package com.jnote.ttl.contoller;

import com.jnote.ttl.constant.TtlMiscTitle;
import com.jnote.ttl.service.TtlRunnableCallableService;
import com.jnote.ttl.util.LogUtil;
import com.jnote.ttl.util.ttl.TransmittableThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 子线程永远能获取到值的正确使用方式：
 * 1. TransmittableThreadLocal + TtlRunnable.get(Runnable) + Executors.newFixedThreadPool(2);
 * 2. TransmittableThreadLocal + TtlCallable.get(Callable) + Executors.newFixedThreadPool(2);
 *
 * @author bowen.yan
 * @since 2020-04-28
 */
@RequestMapping("/")
@RestController
public class TtlRunnableCallableController {
    @Autowired
    private TtlRunnableCallableService ttlRunnableCallableService;

    // 前2次结果：testRunnable_Value，之后：null（线程回收）; 线程池coreSize=2
    @RequestMapping("runnable")
    public String testRunnable() {
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlMiscTitle.testRunnable.getValue());
        LogUtil.log(TtlMiscTitle.testRunnable, Thread.currentThread(), threadLocal.get());

        return ttlRunnableCallableService.testRunnable();
    }

    // 前2次结果：testRunnableTtl_Value，之后：testRunnableTtl_Value; 线程池coreSize=2
    @RequestMapping("runnable-ttl")
    public String testRunnableTtl() {
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlMiscTitle.testRunnableTtl.getValue());
        LogUtil.log(TtlMiscTitle.testRunnableTtl, Thread.currentThread(), threadLocal.get());

        return ttlRunnableCallableService.testRunnableTtl();
    }

    @RequestMapping("callable")
    public String testCallable() {
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlMiscTitle.testCallable.getValue());
        LogUtil.log(TtlMiscTitle.testCallable, Thread.currentThread(), threadLocal.get());

        return ttlRunnableCallableService.testCallable();
    }

    @RequestMapping("callable-ttl")
    public String testCallableTtl() {
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlMiscTitle.testCallableTtl.getValue());
        LogUtil.log(TtlMiscTitle.testCallableTtl, Thread.currentThread(), threadLocal.get());

        return ttlRunnableCallableService.testCallableTtl();
    }
}
