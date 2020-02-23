package com.lee.kafka.controller;

/**
 * @author lee
 * @date 2020/2/23 20:32
 */
import com.lee.kafka.model.TransDataModel;
import com.lee.kafka.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafkaTest")
public class KafkaTestController {
    @Autowired
    BusinessService businessService;

    @RequestMapping(value = "/test")
    public TransDataModel test(TransDataModel defaultMqModel) {
        return businessService.doTrans(defaultMqModel);
    }

    @RequestMapping(value = "/test2")
    public TransDataModel test2(TransDataModel seccondMqModel) {
        return businessService.doTrans(seccondMqModel);
    }
}
