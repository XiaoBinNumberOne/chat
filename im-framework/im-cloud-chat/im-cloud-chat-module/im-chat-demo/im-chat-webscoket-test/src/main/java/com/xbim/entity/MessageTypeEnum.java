package com.xbim.entity;

public enum MessageTypeEnum {
    CONTEXT(0, "context"),
    IMAGE(1, "image");


    private Integer code;
    private String type;

    MessageTypeEnum(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
