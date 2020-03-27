package com.lee.kafka.model;

import lombok.Data;

/**
 * @author lee
 * @date 2020/2/23 20:34
 */
@Data
public class KafkaLogModel {
    /**
     * 日志ID
     */
    private Long logId;
    /**
     * 日志类型 controller日志：CONTROLLER;service日志： SERVICE
     */
    private String logType;

    /**
     * 请求内容
     */
    private Object reqContent;

    /**
     * 响应内容
     */
    private Object resContent;

}
