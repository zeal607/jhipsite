package com.ruowei.modules.sys.api;

import com.querydsl.core.QueryResults;
import com.ruowei.modules.sys.pojo.SysEmployeeCriteria;
import com.ruowei.modules.sys.pojo.SysUserCriteria;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 用户
 * @author 刘东奇
 * @date 2019/9/1
 */
public interface SysUserApi {

    /**
     * 通过条件分页查询员工信息
     * @author 刘东奇
     * @date 2019/9/8
     * @param userCriteria
     * @param employeeCriteria
     * @param page
     */
    QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByCriteria(SysUserCriteria userCriteria, SysEmployeeCriteria employeeCriteria, Pageable page);

    /**
     * 通过主键获取员工信息
     * @author 刘东奇
     * @date 2019/9/16
     * @param id
     */
    Optional<SysUserEmployeeDTO> getSysUserEmployeeById(Long id);

    /**
     * 创建员工
     * @author 刘东奇
     * @date 2019/9/22
     * @param sysUserEmployeeDTO
     * @return SysUserEmployeeDTO
     */
    SysUserEmployeeDTO createSysUserEmployee(SysUserEmployeeDTO sysUserEmployeeDTO);

}
