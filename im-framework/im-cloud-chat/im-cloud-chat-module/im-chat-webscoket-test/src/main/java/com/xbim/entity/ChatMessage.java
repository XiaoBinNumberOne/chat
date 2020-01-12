package com.xbim.entity;

import lombok.Data;

/**
 * 创建时间： 2019/12/8  14:56
 * 备注：
 **/

@Data
public class ChatMessage {

    private Integer status;                             // 状态：
    private String sender;                             // 发送者
    private StatusTypeEnum statusTypeEnum;              // 消息的状态
    private MessageTypeEnum messageTypeEnum;            // 消息类型
    private String context;                             // 发送消息
    private String filePath;                            // 文件路径

}
