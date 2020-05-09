package com.jnote.ttl.service.impl;

import com.jnote.ttl.config.ThreadPoolConfig;
import com.jnote.ttl.constant.TtlTitle;
import com.jnote.ttl.service.TtlThreadPoolService;
import com.jnote.ttl.util.LogUtil;
import com.jnote.ttl.util.ttl.InheritableThreadLocalUtil;
import com.jnote.ttl.util.ttl.ThreadLocalUtil;
import com.jnote.ttl.util.ttl.TransmittableThreadLocalUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TtlThreadPoolServiceImpl implements TtlThreadPoolService {
    @Override
    public String testThreadLocal() {
        ThreadLocal threadLocal = ThreadLocalUtil.getThreadLocal();
        LogUtil.log(TtlTitle.testThreadLocal, Thread.currentThread(), threadLocal.get());
        threadLocal.remove();
        System.out.println();

        return TtlTitle.testThreadLocal.toString();
    }

    @Override
    public String testThreadLocalAsync() {
        Thread thread = new Thread(() -> {
            ThreadLocal threadLocal = ThreadLocalUtil.getThreadLocal();
            LogUtil.log(TtlTitle.testThreadLocalAsync, Thread.currentThread(), threadLocal.get());
            threadLocal.remove();
            System.out.println();
        });
        thread.start();

        return TtlTitle.testThreadLocalAsync.toString();
    }

    @Override
    public String testInheritThreadLocalAsync() {
        new Thread(() -> {
            ThreadLocal threadLocal = InheritableThreadLocalUtil.getThreadLocal();
            LogUtil.log(TtlTitle.testInheritThreadLocalAsync, Thread.currentThread(), threadLocal.get());
            threadLocal.remove();
            System.out.println();
        }).start();

        return TtlTitle.testInheritThreadLocalAsync.toString();
    }

    /**
     * JDK线程池.
     * {@link ThreadPoolConfig#taskExecutor()}
     *
     * @return
     */
    @Async("taskExecutor")
    @Override
    public String testInheritThreadLocalThreadPool() {
        ThreadLocal threadLocal = InheritableThreadLocalUtil.getThreadLocal();
        LogUtil.log(TtlTitle.testInheritThreadLocalThreadPool, Thread.currentThread(), threadLocal.get());
        threadLocal.remove();
        System.out.println();

        return TtlTitle.testInheritThreadLocalThreadPool.toString();
    }

    /**
     * 使用TTL修饰线程池.
     * {@link ThreadPoolConfig#taskExecutorTtl()}
     *
     * @return
     */
    @Async("taskExecutorTtl")
    @Override
    public String testTransmitThreadLocalThreadPool() {
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        LogUtil.log(TtlTitle.testTransmitThreadLocalThreadPool, Thread.currentThread(), threadLocal.get());
        threadLocal.remove();
        System.out.println();

        return TtlTitle.testTransmitThreadLocalThreadPool.toString();
    }
}
