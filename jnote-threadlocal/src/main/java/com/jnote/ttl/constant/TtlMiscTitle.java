package com.jnote.ttl.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author bowen.yan
 * @since 2020-04-28
 */
@Getter
@AllArgsConstructor
public enum TtlMiscTitle {
    testRunnable("testRunnable_Value"),
    testRunnableTtl("testRunnableTtl_Value"),
    testCallable("testCallable_Value"),
    testCallableTtl("testCallable_Value"),

    testLogback("testLogback_Value"),
    testLogbackThreadPool("testLogbackThreadPool_Value");

    String value;
}