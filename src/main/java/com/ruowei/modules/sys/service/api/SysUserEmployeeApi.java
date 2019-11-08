package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
public interface SysUserEmployeeApi {

    /**
     * 新增员工
     * @author 刘东奇
     * @date 2019/11/8
     * @param sysUserEmployeeDTO
     * @return
     */
    SysUserEmployeeDTO createSysUserEmployee(SysUserEmployeeDTO sysUserEmployeeDTO);

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


}
