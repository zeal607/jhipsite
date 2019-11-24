package com.ruowei.common.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 刘东奇
 */
public interface BaseEnum {

    @JsonValue
    String getMessage();

    String getCode();
}
