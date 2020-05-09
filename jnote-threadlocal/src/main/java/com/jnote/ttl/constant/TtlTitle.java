package com.jnote.ttl.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author bowen.yan
 * @since 2020-04-28
 */
@Getter
@AllArgsConstructor
public enum TtlTitle {
    testThreadLocal("testThreadLocal_Value"),
    testThreadLocalAsync("testThreadLocalAsync_Value"),
    testInheritThreadLocalAsync("testInheritThreadLocalAsync_Value"),
    testInheritThreadLocalThreadPool("testInheritThreadLocalThreadPool_Value"),
    testTransmitThreadLocalThreadPool("testTransmitThreadLocalThreadPool_Value");

    String value;
}