# TransmittableThreadLocal示例
### 1. 测试接口（线程池类型）
- http://127.0.0.1:2001/thread-local
- http://127.0.0.1:2001/thread-local/async
- http://127.0.0.1:2001/inherit-thread-local/async
- http://127.0.0.1:2001/inherit-thread-local/thread-pool
- http://127.0.0.1:2001/transmit-thread-local/thread-pool

### 2. 测试接口（常用类型）
- http://127.0.0.1:2001/runnable
- http://127.0.0.1:2001/runnable-ttl
- http://127.0.0.1:2001/callable
- http://127.0.0.1:2001/callable-ttl

### 3. 测试接口（logback支持）
- http://127.0.0.1:2001/logback
- http://127.0.0.1:2001/logback/thread-pool

### 4. JavaAgent方式启动
##### 4.1 JavaAgent用途
这种方式，实现线程池的传递是透明的，业务代码中没有修饰Runnable或是线程池的代码。即可以做到应用代码 无侵入。
使用方式就是：TransmittableThreadLocal + 普通Runnable/Callable/普通线程池

##### 4.2 运行命令
java -Xbootclasspath/a:transmittable-thread-local-2.11.4.jar -javaagent:transmittable-thread-local-2.11.4.jar -jar spring-boot-inheritablethreadlocal-1.0.0.jar
java -javaagent:transmittable-thread-local-2.11.4.jar -jar spring-boot-inheritablethreadlocal-1.0.0.jar

##### 4.3 运行测试接口
http://127.0.0.1:2001/callable

注：此种方式需要将transmittable-thread-local-2.11.4.jar放到当前jar包同一目录;

### 5. 运行结果示例
### 5.1 运行结果示例（http://127.0.0.1:2001/callable）
2020-04-30 15:42:42.833  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-1，ThreadLocal值：testCallable_Value
2020-04-30 15:42:42.834  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-1，ThreadLocal值：testCallable_Value

2020-04-30 15:42:43.375  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-2，ThreadLocal值：testCallable_Value
2020-04-30 15:42:43.376  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-2，ThreadLocal值：testCallable_Value

2020-04-30 15:42:43.858  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-3，ThreadLocal值：testCallable_Value
2020-04-30 15:42:43.858  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-1，ThreadLocal值：null

2020-04-30 15:42:46.078  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-4，ThreadLocal值：testCallable_Value
2020-04-30 15:42:46.079  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-2，ThreadLocal值：null

2020-04-30 15:42:46.528  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-5，ThreadLocal值：testCallable_Value
2020-04-30 15:42:46.529  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-1，ThreadLocal值：null


### 5.2 运行结果示例（http://127.0.0.1:2001/callable Agent方式启动）
2020-04-30 15:43:19.514  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-1，ThreadLocal值：testCallable_Value
2020-04-30 15:43:19.516  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-1，ThreadLocal值：testCallable_Value

2020-04-30 15:43:20.129  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-2，ThreadLocal值：testCallable_Value
2020-04-30 15:43:20.130  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-2，ThreadLocal值：testCallable_Value

2020-04-30 15:43:20.601  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-3，ThreadLocal值：testCallable_Value
2020-04-30 15:43:20.602  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-1，ThreadLocal值：testCallable_Value

2020-04-30 15:43:21.060  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-4，ThreadLocal值：testCallable_Value
2020-04-30 15:43:21.060  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-2，ThreadLocal值：testCallable_Value

2020-04-30 15:43:21.400  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：http-nio-2001-exec-5，ThreadLocal值：testCallable_Value
2020-04-30 15:43:21.401  INFO  com.ttl.util.LogUtil Line:12  - testCallable ------- 当前线程：pool-3-thread-1，ThreadLocal值：testCallable_Value

### 6. 参考链接
@CallMe兵哥  简书
https://www.jianshu.com/p/d542d2773f7b
