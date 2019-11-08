package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.domain.table.SysOffice;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
public interface SysOfficeApi {
    /**
     * 判断是否存在该机构
     * 如果存在，返回机构；如果不存在，抛异常
     * @author 刘东奇
     * @date 2019/11/2
     * @param id
     * @return SysOffice
     */
    SysOffice checkOfficeExistsById(Long id);
}
