package com.ruowei.modules.sys.api;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.pojo.user.SysUserRegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

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
     * @param pageable
     * @return
     */
    QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByCriteria(
        SysUserCriteria userCriteria,
        SysEmployeeCriteria employeeCriteria,
        Pageable pageable);

    /**
     * 通过条件分页查询员工信息
     * @author 刘东奇
     * @date 2019/11/4
     * @param sysUserPredicate
     * @param sysEmployeePredicate
     * @param pageable
     * @return
     */
    QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByPredicate(
        @QuerydslPredicate(root = SysUser.class) Predicate sysUserPredicate,
        @QuerydslPredicate(root = SysEmployee.class) Predicate sysEmployeePredicate,
        Pageable pageable);

    /**
     * 通过主键获取员工信息
     * @author 刘东奇
     * @date 2019/9/16
     * @param id
     * @return
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

    /**
     * 根据激活码激活用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param key
     * @return
     */
    Optional<SysUser> activateRegistration(String key);

    /**
     * 根据新密码和重置码完成密码重置
     * @author 刘东奇
     * @date 2019/10/21
     * @param newPassword
     * @param key
     * @return
     */
    Optional<SysUser> completePasswordReset(String newPassword, String key);

    /**
     * 请求密码重置
     * @author 刘东奇
     * @date 2019/10/21
     * @param mail
     */
    void requestPasswordReset(String mail);

    /**
     * 注册用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param sysUserRegisterDTO 从注册页面传来的数据
     * @return
     */
    SysUserDTO registerUser(SysUserRegisterDTO sysUserRegisterDTO);

    /**
     * 创建用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param userDTO
     * @return
     */
    SysUserDTO createUser(SysUserDTO userDTO);


    /**
     * 更新用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param userDTO
     * @return
     */
    SysUserDTO updateUser(SysUserDTO userDTO);

    /**
     * 根据登录Id删除用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param loginCode
     */
    void deleteUser(String loginCode);

    /**
     * 修改密码
     * @author 刘东奇
     * @date 2019/10/21
     * @param currentClearTextPassword
     * @param newPassword
     */
    void changePassword(String currentClearTextPassword, String newPassword);

    /**
     * 获取用户列表
     * @author 刘东奇
     * @date 2019/10/21
     * @param page
     */
    Page<SysUserDTO> getAllUsers(Pageable page);

    /**
     * 获取所有权限
     * @author 刘东奇
     * @date 2019/10/21
     * @param
     */
    List<String> getAuthorities();

    /**
     * 根据登录Id获取用户信息并携带权限
     * @author 刘东奇
     * @date 2019/10/21
     * @param loginCode
     */
    Optional<SysUserDTO> getUserWithAuthoritiesByLogin(String loginCode);

    /**
     * 获取当前登录用户信息并携带权限
     * @author 刘东奇
     * @date 2019/10/21
     * @param
     */
    Optional<SysUserDTO> getUserWithAuthorities();
}
