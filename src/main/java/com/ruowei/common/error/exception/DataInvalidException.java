package com.ruowei.common.error.exception;


/**
 * 数据错误
 * @author 刘东奇
 */
public class DataInvalidException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public DataInvalidException(String title){
        super(ErrorConstants.DEFAULT_TYPE, title, null, null);
    }
}
