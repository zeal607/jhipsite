package com.ruowei.modules.sys.api;

import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeCriteria;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeTreeVM;

import java.util.Optional;

/**
 * 机构
 * @author 刘东奇
 * @date 2019/9/30
 */
public interface SysOfficeApi {

    /**
     * 获取机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     * @return TreeDTO
     */
    TreeDTO getOfficeTree();


    /**
     * 通过条件查询机构树信息
     * @author 刘东奇
     * @date 2019/10/6
     * @param sysOfficeCriteria
     */
    SysOfficeTreeVM findSysOfficeTreeVMByCriteria(SysOfficeCriteria sysOfficeCriteria);

    /**
     * 通过主键获取机构信息
     * @author 刘东奇
     * @date 2019/10/6
     * @param id
     */
    Optional<SysOfficeDTO> getSysOfficeById(Long id);

    /**
     * 创建机构
     * @author 刘东奇
     * @date 2019/10/6
     * @param sysOfficeDTO
     */
    SysOfficeDTO createSysOffice(SysOfficeDTO sysOfficeDTO);
}
