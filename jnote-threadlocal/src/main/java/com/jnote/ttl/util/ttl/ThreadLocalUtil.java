package com.jnote.ttl.util.ttl;

/**
 * <p>ThreadLocalUtil</p>
 * <p>description.</p>
 *
 * @author : jiangbing.yang
 * @version 0.1
 * @date : 2019/5/27 14:41
 * @company :
 */

public class ThreadLocalUtil {
    // 推荐用：private static，便于多个线程都访问同一个[方法区] 里面的ThreadLocal，多个线程共用一份ThreadLocal，目的：节约空间
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    //获取线程中保存的值
    public static ThreadLocal getThreadLocal() {
        return THREAD_LOCAL;
    }
}
