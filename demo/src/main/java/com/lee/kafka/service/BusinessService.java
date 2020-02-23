package com.lee.kafka.service;

/**
 * @author lee
 * @date 2020/2/23 20:33
 */
import com.lee.kafka.model.TransDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class BusinessService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public TransDataModel doTrans(TransDataModel defaultMqModel) {
        log.info("处理消息{}", defaultMqModel);

        defaultMqModel.setType("1111");

        return defaultMqModel;
    }
}
