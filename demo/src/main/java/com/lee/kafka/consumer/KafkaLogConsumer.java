package com.lee.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.kafka.model.KafkaLogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lee
 * @date 2020/2/23 20:35
 */
@Component
public class KafkaLogConsumer {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    

    @KafkaListener(topics = {"${lee.kafka.topics.log}"})
    public void consumer(String message) {
        ObjectMapper mapper = new ObjectMapper();
        KafkaLogModel kafkaLogModel;
        try {
            kafkaLogModel = mapper.readValue(message, KafkaLogModel.class);
            log.info("[consumer_1]收到消息：{},消息ID为: {}", kafkaLogModel.toString(), kafkaLogModel.getLogId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = {"${lee.kafka.topics.log}"})
    public void consumer2(String message) {
        ObjectMapper mapper = new ObjectMapper();
        KafkaLogModel kafkaLogModel;
        try {
            kafkaLogModel = mapper.readValue(message, KafkaLogModel.class);
            log.info("[consumer_2]收到消息：{},消息ID为: {}", kafkaLogModel.toString(), kafkaLogModel.getLogId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}