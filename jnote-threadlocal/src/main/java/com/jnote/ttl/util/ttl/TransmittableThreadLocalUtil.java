package com.jnote.ttl.util.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * <p>InheritableThreadLocalUtil</p>
 * <p>description.</p>
 *
 * @author : jiangbing.yang
 * @version 0.1
 * @date : 2019/5/27 14:55
 * @company :
 */

public class TransmittableThreadLocalUtil {
    public static final TransmittableThreadLocal<String> THREAD_LOCAL = new TransmittableThreadLocal<>();

    //获取线程中保存的值
    public static ThreadLocal getThreadLocal() {
        return THREAD_LOCAL;
    }
}
