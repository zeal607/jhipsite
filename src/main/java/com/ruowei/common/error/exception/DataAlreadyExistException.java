package com.ruowei.common.error.exception;


/**
 * 数据已存在
 * @author 刘东奇
 */
public class DataAlreadyExistException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public DataAlreadyExistException(String title){
        super(ErrorConstants.DEFAULT_TYPE, title, null, null);
    }
}
