package com.lee.kafka.aspect;

/**
 * @author lee
 * @date 2020/2/23 20:31
 */

import com.lee.kafka.model.KafkaLogModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Aspect
@Component
@Slf4j
public class KafkaLogAspect {

    @Autowired
    private KafkaTemplate<String, KafkaLogModel> kafkaTemplate;

    @Value("${lee.kafka.topics.log}")
    private String logTopics;


    @Around("execution(public * com.lee.kafka.service.BusinessService.doTrans(..))")
    public Object doAroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        KafkaLogModel kafkaLogModel = new KafkaLogModel();
        Object resp = buildMsgModel(proceedingJoinPoint, kafkaLogModel, "SERVICE");

        log.info("###开始发送给kafka,消息类型[SERVICE],数据{}", kafkaLogModel.toString());
        ProducerRecord<String, KafkaLogModel> record = new ProducerRecord<>(logTopics, kafkaLogModel);
        ListenableFuture<SendResult<String, KafkaLogModel>> future = kafkaTemplate.send(record);

        /**
         * 异步调用,发送消息以后,就不用管了,等收到ack以后,就会回调该方法
         */
        future.addCallback(new ListenableFutureCallback<SendResult<String, KafkaLogModel>>() {
            @Override
            public void onSuccess(SendResult<String, KafkaLogModel> result) {
                int partition = result.getRecordMetadata().partition();
                log.info("+++kafka消息[SERVICE]发送成功,分区为:{}", partition);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("---kafka消息[SERVICE]发送失败,失败原因为:{}", ex.getMessage());
            }
        });

        return resp;
    }


    @Around("execution(public * com.lee.kafka.controller.*.*(..))")
    public Object doAroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        KafkaLogModel kafkaLogModel = new KafkaLogModel();
        Object resp = buildMsgModel(proceedingJoinPoint, kafkaLogModel, "CONTROLLER");

        log.info("###开始发送给kafka,消息类型[CONTROLLER],数据{}", kafkaLogModel.toString());
        ProducerRecord<String, KafkaLogModel> record = new ProducerRecord<>(logTopics, kafkaLogModel);
        ListenableFuture<SendResult<String, KafkaLogModel>> send = kafkaTemplate.send(record);

        // 同步调用调用get()方法,会导致方法阻塞,一直到得到ack响应以后
        try {
            SendResult<String, KafkaLogModel> result = send.get();
            int partition = result.getRecordMetadata().partition();
            log.info("+++kafka消息[CONTROLLER]发送成功,分区为:{}", partition);
        } catch (Throwable ex) {
            log.info("---kafka消息[CONTROLLER]发送失败,失败原因为:{}", ex.getMessage());
        }
        return resp;
    }

    private Object buildMsgModel(ProceedingJoinPoint proceedingJoinPoint, KafkaLogModel kafkaLogModel, String service) throws Throwable {
        kafkaLogModel.setLogType(service);
        // 获取请求参数
        Object[] req = proceedingJoinPoint.getArgs();
        kafkaLogModel.setReqContent(req);
        // 执行
        Object resp = proceedingJoinPoint.proceed();
        // 设置响应内容
        kafkaLogModel.setResContent(resp);
        return resp;
    }
}
