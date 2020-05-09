package com.jnote.ttl.contoller;

import com.jnote.ttl.constant.TtlTitle;
import com.jnote.ttl.service.TtlThreadPoolService;
import com.jnote.ttl.util.LogUtil;
import com.jnote.ttl.util.ttl.InheritableThreadLocalUtil;
import com.jnote.ttl.util.ttl.ThreadLocalUtil;
import com.jnote.ttl.util.ttl.TransmittableThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class TtlThreadPoolController {
    @Autowired
    private TtlThreadPoolService ttlThreadPoolService;

    @RequestMapping("thread-local")
    public String testThreadLocal() {
        ThreadLocal threadLocal = ThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlTitle.testThreadLocal.getValue());
        LogUtil.log(TtlTitle.testThreadLocal, Thread.currentThread(), threadLocal.get());

        return ttlThreadPoolService.testThreadLocal();
    }

    @RequestMapping("thread-local/async")
    public String testThreadLocalAsync() {
        ThreadLocal threadLocal = ThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlTitle.testThreadLocalAsync.getValue());
        LogUtil.log(TtlTitle.testThreadLocalAsync, Thread.currentThread(), threadLocal.get());

        return ttlThreadPoolService.testThreadLocalAsync();
    }

    @RequestMapping("inherit-thread-local/async")
    public String testInheritThreadLocalAsync() {
        ThreadLocal threadLocal = InheritableThreadLocalUtil.getThreadLocal();
        threadLocal.set(TtlTitle.testInheritThreadLocalAsync.getValue());
        LogUtil.log(TtlTitle.testInheritThreadLocalAsync, Thread.currentThread(), threadLocal.get());

        return ttlThreadPoolService.testInheritThreadLocalAsync();
    }

    private int index = 1;

    @RequestMapping("inherit-thread-local/thread-pool")
    public String testInheritThreadLocalThreadPool() {
        ThreadLocal threadLocal = InheritableThreadLocalUtil.getThreadLocal();

        String value = String.format("%s-%s", TtlTitle.testInheritThreadLocalThreadPool.getValue(), index++);
        threadLocal.set(value);

        LogUtil.log(TtlTitle.testInheritThreadLocalThreadPool, Thread.currentThread(), threadLocal.get());

        return ttlThreadPoolService.testInheritThreadLocalThreadPool();
    }

    @RequestMapping("transmit-thread-local/thread-pool")
    public String testTransmitThreadLocalThreadPool() {
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();

        String value = String.format("%s-%s", TtlTitle.testTransmitThreadLocalThreadPool.getValue(), index++);
        threadLocal.set(value);

        LogUtil.log(TtlTitle.testTransmitThreadLocalThreadPool, Thread.currentThread(), threadLocal.get());

        return ttlThreadPoolService.testTransmitThreadLocalThreadPool();
    }
}
