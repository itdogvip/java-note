package com.jnote.ttl.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//@EnableAsync//开启注解：开启异步支持
public class ThreadPoolConfig {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 4 < 256 ? 256 : CORE_POOL_SIZE * 4;
    private static final int KEEP_ALIVE_TIME = 10; //允许线程空闲时间（单位为秒）
    private static final int QUEUE_CAPACITY = 200; // 缓冲队列数
    private static final int AWAIT_TERMINATION = 60;//线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁
    private static final Boolean WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = true;//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean

    @Bean("taskExecutor")
    public static Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = getThreadPoolTaskExecutor("taskExecutor-Service-");

        return taskExecutor;
    }

    @Bean("taskExecutorTtl")
    public static Executor taskExecutorTtl() {
        ThreadPoolTaskExecutor taskExecutor = getThreadPoolTaskExecutor("taskExecutorTtl-Service-");

        // 用TtlExecutors修饰线程池
        return TtlExecutors.getTtlExecutor(taskExecutor);
    }

    private static ThreadPoolTaskExecutor getThreadPoolTaskExecutor(String taskPoolPrefix) {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(2);
        taskExecutor.setMaxPoolSize(2);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setThreadNamePrefix(taskPoolPrefix);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);
        taskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION);
        // 线程池对拒绝任务的处理策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        taskExecutor.initialize();
        return taskExecutor;
    }
}
