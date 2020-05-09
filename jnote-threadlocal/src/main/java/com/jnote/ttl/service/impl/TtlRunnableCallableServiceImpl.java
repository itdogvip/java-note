package com.jnote.ttl.service.impl;

import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.jnote.ttl.constant.TtlMiscTitle;
import com.jnote.ttl.service.TtlRunnableCallableService;
import com.jnote.ttl.util.LogUtil;
import com.jnote.ttl.util.ttl.TransmittableThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author bowen.yan
 * @since 2020-04-28
 */
@Service
public class TtlRunnableCallableServiceImpl implements TtlRunnableCallableService {
    private ExecutorService runnableExecutorService = Executors.newFixedThreadPool(2);
    private ExecutorService runnableTtlExecutorService = Executors.newFixedThreadPool(2);
    private ExecutorService callableExecutorService = Executors.newFixedThreadPool(2);
    private ExecutorService callableTtlExecutorService = Executors.newFixedThreadPool(2);

    @Override
    public String testRunnable() {
        TtlMiscTitle title = TtlMiscTitle.testRunnable;
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(title.getValue());
        Runnable runnable = getRunnable(title);

        runnableExecutorService.submit(runnable);

        return title.getValue();
    }

    @Override
    public String testRunnableTtl() {
        TtlMiscTitle title = TtlMiscTitle.testRunnableTtl;
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(title.getValue());
        Runnable runnable = getRunnable(title);

        runnableTtlExecutorService.submit(TtlRunnable.get(runnable));

        return title.getValue();
    }

    @Override
    public String testCallable() {
        TtlMiscTitle title = TtlMiscTitle.testCallable;
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(title.getValue());
        Callable<String> callable = getCallable(title);

        Future<String> future = callableExecutorService.submit(callable);
        return getResult(future);
    }

    @Override
    public String testCallableTtl() {
        TtlMiscTitle title = TtlMiscTitle.testCallable;
        ThreadLocal threadLocal = TransmittableThreadLocalUtil.getThreadLocal();
        threadLocal.set(title.getValue());
        Callable<String> callable = getCallable(title);

        Future<String> future = callableTtlExecutorService.submit(TtlCallable.get(callable));
        return getResult(future);
    }

    private Runnable getRunnable(TtlMiscTitle title) {
        return new Runnable() {
            @Override
            public void run() {
                ThreadLocal childThreadLocal = TransmittableThreadLocalUtil.getThreadLocal();
                LogUtil.log(title, Thread.currentThread(), childThreadLocal.get());
                childThreadLocal.remove();
                System.out.println();
            }
        };
    }

    private Callable<String> getCallable(TtlMiscTitle title) {
        return new Callable<String>() {
            @Override
            public String call() {
                ThreadLocal childThreadLocal = TransmittableThreadLocalUtil.getThreadLocal();
                LogUtil.log(title, Thread.currentThread(), childThreadLocal.get());
                childThreadLocal.remove();
                System.out.println();
                return title.toString();
            }
        };
    }

    private <T> T getResult(Future<T> future) {
        try {
            return future.get();
        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
            return null;
        }
    }
}
