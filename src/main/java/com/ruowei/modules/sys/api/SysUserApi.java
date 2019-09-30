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

//    /**
//     * 重置用户密码
//     * @param userId
//     * @return
//     */
//    void resetPassWord(Long userId);
//
//    /**
//     * 修改USER信息时，检查登录ID
//     * 如果新ID已存在，则返回false，如果新ID不存在，或原ID和新ID已有，则返回true
//     * @param oldLoginCode
//     * @param loginCode
//     * @return
//     */
//    Boolean checkLoginCode(String oldLoginCode, String loginCode);
//
//    /**
//     * 保存用户权限
//     * @param sysUser
//     * @param list
//     */
//    void saveAuthDataScope(SysUser sysUser, List<SysUserDataScope> list);
//
//
//    List<SysUser> findList(SysUserCriteria sysUserCriteria);
//
//    /**
//     * 通过角色查找用户
//     * @param roleId
//     * @return
//     */
//    List<SysUser> findListByRoleId(String roleId);
//
//    /**
//     * 更新用户信息
//     * @param sysUser
//     */
//    void updateUserInfo(SysUser sysUser);
//
//    /**
//     * 更新用户登录信息
//     * @param sysUser
//     */
//    void updateUserLoginInfo(SysUser sysUser);
//
//    /**
//     * 更新状态
//     * @param sysUser
//     * @return
//     */
//    SysUser updateStatus(SysUser sysUser);
//
//    /**
//     * 更新用户的密码
//     * @param userCode
//     * @param newPassword
//     */
//    void updatePassword(String userCode, String newPassword);
//
//    /**
//     * 通过登录ID获取用户
//     * @param loginCode
//     * @return
//     */
//    SysUser getByLoginCode(String loginCode);
//
//    /**
//     * 通过用户类型和相关ID查找用户
//     * @param userType
//     * @param refCode
//     * @return
//     */
//    SysUser getByUserTypeAndRefCode(UserType userType, String refCode);
//
//    /**
//     * 保存权限
//     */
//    void saveAuth();
//
//    /**
//     * 查找用户权限
//     * @param var1
//     * @return
//     */
//    List<SysUserDataScope> findDataScopeList(SysUserDataScope var1);
//
//    /**
//     * 根据条件删除人员
//     * @author 刘东奇
//     * @date 2019/9/6
//     * @param sysUser
//     */
//    void delete(SysUser sysUser);
//
//    /**
//     * 根据条件查询指定人员
//     * @author 刘东奇
//     * @date 2019/9/6
//     * @param sysUser
//     */
//    SysUser get(SysUser sysUser);
}
