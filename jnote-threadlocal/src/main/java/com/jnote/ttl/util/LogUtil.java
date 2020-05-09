package com.jnote.ttl.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bowen.yan
 * @since 2020-04-28
 */
@Slf4j
public class LogUtil {
    public static void log(Object title, Thread thread, Object value) {
        log.info(String.format("%s ------- 当前线程：%s，ThreadLocal值：%s",
            title, thread.getName(), value));
    }
}
