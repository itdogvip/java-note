# jnote-spring-core

### 1. 测试接口（线程池类型）
##### 1.1 JavaAgent用途
这种方式，实现线程池的传递是透明的，业务代码中没有修饰Runnable或是线程池的代码。即可以做到应用代码 无侵入。
使用方式就是：TransmittableThreadLocal + 普通Runnable/Callable/普通线程池
