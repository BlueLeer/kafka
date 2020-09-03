package com.lee.kafka.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wl
 * @date 2020-09-03 17:41
 * @description
 **/
@Component
public class ThreadPoolTest implements Runnable{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private ScheduledExecutorService threadPoolExecutor;
    public void startup(){
        if (threadPoolExecutor == null) {
            this.threadPoolExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(Thread.currentThread().getThreadGroup(),r,"thread-lee"+atomicInteger.getAndIncrement());
                    return thread;
                }
            });
        }

        this.threadPoolExecutor.scheduleAtFixedRate(this,5,10, TimeUnit.SECONDS);
    }
    @Override
    public void run() {
        System.out.println("当前时间："+System.currentTimeMillis());
    }
}
