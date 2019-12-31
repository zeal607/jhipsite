package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.domain.entity.SysEmployee;

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
     * @param sysEmployee
     * @return
     */
    SysEmployee createSysUserEmployee(SysEmployee sysEmployee);

    /**
     * 修改员工
     * @author 刘东奇
     * @date 2019/11/14
     * @param sysEmployee
     * @return
     */
    SysEmployee modifySysUserEmployee(SysEmployee sysEmployee);

    /**
     * 判断用户是否已存在（登录ID、手机号、邮箱是否被占用）
     * 如果存在，抛异常
     * @author 刘东奇
     * @date 2019/11/8
     * @param loginCode
     * @param mobile
     * @param email
     * @param employeeId 所更新员工的主键，如果传表示是更新操作，否则是新增操作
     * @return
     */
    void checkSysUserExists(String loginCode, String mobile,String email, Long employeeId);

    /**
     * 根据员工ID获取用户ID
     * @author 刘东奇
     * @date 2019/11/14
     * @param id
     * @return
     */
    Long getSysUserIdBySysEmployeeId(Long id);

    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     */
    void disableSysEmployee(Long sysEmployeeId);

    /**
     * 启用用户
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    void enableSysEmployee(Long sysEmployeeId);

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    void deleteSysEmployee(Long sysEmployeeId);

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    void resetSysEmployeePassword(Long sysEmployeeId);

    /**
     * 给员工分配角色
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @param roleIdList
     * @return
     */
    void assignRoleToSysEmployee(Long sysEmployeeId, List<Long> roleIdList);
}
