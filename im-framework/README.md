#### 🎯 注册中心问题本地测试
- 注册中心 使用的本地的nacos  
- 配置中心 使用的是线上nacos

#### 🎯 模块开发

[GOGOGOGO](https://github.com/XiaoBinNumberOne/chat/tree/master/im-framework/im-cloud-chat/im-cloud-chat-module "") 

#### 🎯 protobuf格式定义
```
syntax = "proto3"; // 指定protobuf版本

option java_package = "com.xbim.protobuf"; // 指定包名
option java_outer_classname = "Message"; // 指定生成的类名

message ChatMessage {
    Head head = 1;   // 消息头
    string body = 2; // 消息体
}

message Head {
    string msgId = 1;                    // 消息id
    MsgType msgType = 2;                 // 消息类型:每种不同消息的类型，用于区分
    MsgContentType msgContentType = 3;   // 消息内容类型：单聊和群聊需要用到此类型
    int64 fromId = 4;                    // 消息发送者id
    int64 toId = 5;                      // 消息接收者id
    int64 timestamp = 6;                 // 消息时间戳
    int32 statusReport = 7;              // 状态报告：服务端或者客户端消息发送/接收状态报告
    string extend = 8;                   // 扩展字段(JSON格式)
}

enum MsgType {
    HANDSHAKE = 0;                  // 握手消息：客户端主动发送握手消息，进行认证，认证成功服务端返回小共同的消息类型
    HEARTBEAT = 1;                  // 心跳信息：确认连接是否存活
      OFFLINE = 2;                  // 用户被挤下线：当账号在别的设备上登录，服务端发送OFFLINE状态信息，并断开连接
    OFFLINE_INFO = 3;               // 拉取离线信息：服务端收到此消息，查询离线信息，通过BACK_INFO状态返回消息
    BACK_INFO = 4;                  // 返回离线信息
    ACK_INFO = 5;                   // 应答离线信息：客户端收到离线消息，主动发送此状态到服务端，服务端应该删除离线列表中的离线消息
    CLIENT_STATUS_REPORT = 6;       // 客户端状态报告:客户端收到服务端消息后，发送此状态到服务端，服务端收到此状态信息，认为客户端收到消息
    SERVER_STATUS_REPORT = 7;       // 服务端状态报告：服务端收到客户端信息，发送此状态给客户端，告知已成功发送
    SINGLE_CHAT = 8;                // 单聊
    GROUP_CHAT = 9;                 // 群聊

}

enum MsgContentType {
    TEXT = 0;   // 文本消息
    VOICE = 1;  // 语音消息
    IMAGE = 2;  // 图片
}



```
