package com.lee.kafka;

import com.lee.kafka.thread.ThreadPoolTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author lee
 * @date 2020/2/23 12:36
 */
@SpringBootApplication
public class KafkaDemoApplication implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ThreadPoolTest threadPoolTest;
    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        threadPoolTest.startup();
    }
}
