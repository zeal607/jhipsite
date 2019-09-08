package com.ruowei.common.error.exception;

import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.web.rest.errors.ErrorConstants;

/**
 * 数据不存在
 * @author 刘东奇
 */
public class DataNotFoundException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String title){
        super(ErrorConstants.DEFAULT_TYPE, title, null, null);
    }
}
