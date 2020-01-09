package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployeeOfficePost;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.domain.table.SysPost;
import com.ruowei.modules.sys.domain.table.SysRole;

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
     * @param employeeId 所更新员工的主键，如果传表示是更新操作，否则是新增操作
     * @param loginCode
     * @param mobile
     * @param email     *
     * @return
     */
    void checkSysUserExists(String employeeId, String loginCode, String mobile,String email);

    /**
     * 根据员工ID获取用户ID
     * @author 刘东奇
     * @date 2019/11/14
     * @param id
     * @return
     */
    String getSysUserIdBySysEmployeeId(String id);

    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     */
    void disableSysEmployee(String sysEmployeeId);

    /**
     * 启用用户
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    void enableSysEmployee(String sysEmployeeId);

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    void deleteSysEmployee(String sysEmployeeId);

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    void resetSysEmployeePassword(String sysEmployeeId);

    /**
     * 给员工分配角色
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @param roleList
     * @return
     */
    void assignRoleToSysEmployee(String sysEmployeeId, List<SysRole> roleList);

    /**
     * 更新员工表
     * @author 刘东奇
     * @date 2020/1/2
     * @param newSysEmployee
     * @return
     */
    void updateSysEmployeeTable(SysEmployee newSysEmployee);

    /**
     * 更新用户表
     * @author 刘东奇
     * @date 2020/1/2
     * @param newSysUser
     * @param empName
     */
    void updateSysUserTable(SysUser newSysUser, String empName);

    /**
     * 更新员工职位关系表
     * @author 刘东奇
     * @date 2020/1/2
     * @param sysEmployeeId
     * @param postList
     */
    void updateSysEmployeePostRelationship(String sysEmployeeId, List<SysPost> postList);

    /**
     * 更新员工机构职位关系表
     * @author 刘东奇
     * @date 2020/1/2
     * @param sysEmployeeId
     * @param employeeOfficePostList
     */
    void updateSysEmployeeOfficePostRelationship(String sysEmployeeId, List<SysEmployeeOfficePost> employeeOfficePostList);

    /**
     * 更新用户角色关系表
     * @author 刘东奇
     * @date 2020/1/2
     * @param sysUserId
     * @param roleList
     */
    void updateSysUserRoleRelationship(String sysUserId, List<SysRole> roleList);
}
