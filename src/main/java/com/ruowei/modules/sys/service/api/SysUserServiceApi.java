package com.ruowei.modules.sys.service.api;

import com.ruowei.domain.SysUserDataScope;
import com.ruowei.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysUserCriteria;

import java.util.List;

/**
 * 用户
 * @author 刘东奇
 * @date 2019/9/1
 */
public interface SysUserServiceApi {

    /**
     * 重置用户密码
     * @param userId
     * @return
     */
    void resetPassWord(Long userId);

    /**
     * 修改USER信息时，检查登录ID
     * 如果新ID已存在，则返回false，如果新ID不存在，或原ID和新ID已有，则返回true
     * @param oldLoginCode
     * @param loginCode
     * @return
     */
    Boolean checkLoginCode(String oldLoginCode, String loginCode);

    /**
     * 保存用户权限
     * @param sysUser
     * @param list
     */
    void saveAuthDataScope(SysUser sysUser, List<SysUserDataScope> list);


    List<SysUser> findList(SysUserCriteria sysUserCriteria);

    /**
     * 通过角色查找用户
     * @param roleId
     * @return
     */
    List<SysUser> findListByRoleId(String roleId);

    /**
     * 更新用户信息
     * @param sysUser
     */
    void updateUserInfo(SysUser sysUser);

    /**
     * 更新用户登录信息
     * @param sysUser
     */
    void updateUserLoginInfo(SysUser sysUser);

    /**
     * 更新状态
     * @param sysUser
     * @return
     */
    SysUser updateStatus(SysUser sysUser);

    /**
     * 更新用户的密码
     * @param userCode
     * @param newPassword
     */
    void updatePassword(String userCode, String newPassword);

    /**
     * 通过登录ID获取用户
     * @param loginCode
     * @return
     */
    SysUser getByLoginCode(String loginCode);

    /**
     * 通过用户类型和相关ID查找用户
     * @param userType
     * @param refCode
     * @return
     */
    SysUser getByUserTypeAndRefCode(UserType userType, String refCode);

    /**
     * 保存权限
     */
    void saveAuth();

    /**
     * 查找用户权限
     * @param var1
     * @return
     */
    List<SysUserDataScope> findDataScopeList(SysUserDataScope var1);

    /**
     * 根据条件删除人员
     * @author 刘东奇
     * @date 2019/9/6
     * @param sysUser
     */
    void delete(SysUser sysUser);

    /**
     * 根据条件查询指定人员
     * @author 刘东奇
     * @date 2019/9/6
     * @param sysUser
     */
    SysUser get(SysUser sysUser);
}
