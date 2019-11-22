package com.ruowei.common.enumeration;

/**
 * @author 刘东奇
 */
public interface BaseEnum {

    /**
     *
     * 值，数据库中，以及程序中一般传递的都是这个值
     */
    public String getCode();

    public String getMessage();
}
