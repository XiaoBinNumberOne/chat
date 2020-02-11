##模块开发

###### 首先引入依赖
```
        <dependency>
            <groupId>com.xbim</groupId>
            <artifactId>spring-cloud-alibaba-dubbo-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.xbim</groupId>
            <artifactId>im-cloud-chat-api</artifactId>
            <version>${project.parent.version}</version>
        </dependency>
```

###### 增加ProtocolConfig dubbo配置
```
 @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20880);
        protocolConfig.setName("dubbo");
        return protocolConfig;
    }
```

###### 配置文件application.properties
```
spring.main.allow-bean-definition-overriding=true
dubbo.application.name=demo
dubbo.application.qos-enable=false
dubbo.registry.address=nacos://localhost:8848
dubbo.scan.base-packages=com.xbim
management.endpoints.web.exposure.include="*"
```