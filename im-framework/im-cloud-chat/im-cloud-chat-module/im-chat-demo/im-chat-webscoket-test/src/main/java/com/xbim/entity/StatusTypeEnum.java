package com.xbim.entity;

public enum StatusTypeEnum {
    JOIN(0, "join"), CHAT(1, "chat"), LEAVE(2, "leave");

    private Integer code;
    private String type;

    StatusTypeEnum(Integer code, String type) {
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
