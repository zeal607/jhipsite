package com.ruowei.modules.sys.service.api;

import com.ruowei.common.pojo.BaseTree;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.pojo.SysOfficeCriteria;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeTree;

import java.util.List;
import java.util.Optional;

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
