package com.jnote.ttl.util.ttl;

/**
 * <p>InheritableThreadLocalUtil</p>
 * <p>description.</p>
 *
 * @author : jiangbing.yang
 * @version 0.1
 * @date : 2019/5/27 14:55
 * @company :
 */

public class InheritableThreadLocalUtil {
    public static final InheritableThreadLocal<String> THREAD_LOCAL = new InheritableThreadLocal<>();

    //获取线程中保存的值
    public static ThreadLocal getThreadLocal() {
        return THREAD_LOCAL;
    }
}
