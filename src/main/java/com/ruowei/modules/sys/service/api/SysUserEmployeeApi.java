package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
import com.ruowei.modules.sys.domain.table.SysRole;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;

import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
public interface SysUserEmployeeApi {

    /**
     * 新增员工
     * @author 刘东奇
     * @date 2019/11/8
     * @param sysUserEmployeeDetailVM
     * @return
     */
    SysUserEmployeeDetailVM createSysUserEmployee(SysUserEmployeeDetailVM sysUserEmployeeDetailVM);

    /**
     * 判断用户是否已存在（登录ID、手机号是否被占用）
     * 如果存在，抛异常
     * @author 刘东奇
     * @date 2019/11/8
     * @param loginCode
     * @param mobile
     * @return
     */
    void checkSysUserExists(String loginCode, String mobile);

    /**
     * 获取员工的角色列表
     * @author 刘东奇
     * @date 2019/11/13
     * @param id
     * @return
     */
    List<SysRole> getSysRoleListBySysEmployeeId(Long id);

}
