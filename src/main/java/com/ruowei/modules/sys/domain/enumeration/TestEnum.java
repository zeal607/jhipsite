package com.ruowei.modules.sys.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;
import com.ruowei.common.enumeration.BaseEnum;

/**
 * @author 刘东奇
 */
public enum TestEnum implements BaseEnum {
    XIAO_MING("XIAO_MING", "小明"),
    XIAOWANG("XIAO_WANG", "小王");

    private String code;
    private String message;

    TestEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    @JsonValue
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }
}
