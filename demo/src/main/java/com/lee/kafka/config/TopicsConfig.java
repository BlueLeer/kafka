package com.lee.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 * @date 2020/2/23 20:26
 */
@Configuration
public class TopicsConfig {
    @Bean
    public NewTopic logCenter() {
        // topic名称,分区数量,分区复制的个数,包括自己
        return new NewTopic("logCenter", 2, (short) 2);
    }

    @Bean
    public NewTopic logTest() {
        return new NewTopic("logCenter_test", 1, (short) 1);
    }
}
