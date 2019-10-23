package com.ruowei.modules.sys.api;

import com.ruowei.common.pojo.BaseTree;
import com.ruowei.modules.sys.pojo.SysOfficeCriteria;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeTree;

import java.util.List;
import java.util.Optional;

/**
 * 机构
 * @author 刘东奇
 * @date 2019/9/30
 */
public interface SysOfficeApi {

    /**
     * 一次性获取整个机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     * @return BaseTree
     */
    BaseTree getEntireOfficeTreeAtOnce();

    /**
     * 一次性获取某机构及其下属构成的树
     * @author 刘东奇
     * @date 2019/10/18
     * @param parentCode
     * @return BaseTree
     */
    SysOfficeTree getOfficeTreeAtOnce(String parentCode);


    /**
     * 通过条件查询机构信息
     * 在一个树中查询，可能会查询到一个或多个结果，每个结果都可以作为一个独立的树，树可以异步查询得到
     * @author 刘东奇
     * @date 2019/10/6
     * @param sysOfficeCriteria
     */
    List<SysOfficeDTO> findSysOfficeTreeVMByCriteria(SysOfficeCriteria sysOfficeCriteria);

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
