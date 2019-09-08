package com.ruowei.modules.sys.service.support;

import com.querydsl.core.types.Predicate;
import com.ruowei.common.service.CrudService;
import com.ruowei.domain.SysUserDataScope;
import com.ruowei.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysUserCriteria;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.api.SysUserServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceSupport
    extends CrudService<SysUser, Long, SysUserCriteria, SysUserEmployeeVM, SysUserDTO, SysUserRepository>
    implements SysUserServiceApi {

    private final Logger log = LoggerFactory.getLogger(SysUserServiceSupport.class);

    /**
     * 插入或更新
     *
     * @param sysUserDTO
     * @return
     */
    @Override
    public SysUserDTO save(SysUserDTO sysUserDTO) {
        return null;
    }

    /**
     * 把前端传过来的Query转换成后端能处理的predicate
     * 可以根据实际需求有不同的实现
     *
     * @param sysUserCriteria
     * @return
     */
    @Override
    public Predicate getPredicate(SysUserCriteria sysUserCriteria) {
        return null;
    }

    /**
     * 通过predicate查询唯一视图
     *
     * @param predicate
     * @return
     */
    @Override
    public SysUserEmployeeVM getVO(Predicate predicate) {
        return null;
    }

    /**
     * 通过predicate查询全部视图
     *
     * @param predicate
     * @return
     */
    @Override
    public List<SysUserEmployeeVM> findAllVO(Predicate predicate) {
        return null;
    }

    /**
     * 通过predicate查询分页视图
     *
     * @param predicate
     * @param pageable
     * @return
     */
    @Override
    public Page<SysUserEmployeeVM> findPageVO(Predicate predicate, Pageable pageable) {
        return null;
    }

    /**
     * 重置用户密码
     *
     * @param userId
     * @return
     */
    @Override
    public void resetPassWord(Long userId) {

    }

    /**
     * 修改USER信息时，检查登录ID
     * 如果新ID已存在，则返回false，如果新ID不存在，或原ID和新ID已有，则返回true
     *
     * @param oldLoginCode
     * @param loginCode
     * @return
     */
    @Override
    public Boolean checkLoginCode(String oldLoginCode, String loginCode) {
        return null;
    }

    /**
     * 保存用户权限
     *
     * @param sysUser
     * @param list
     */
    @Override
    public void saveAuthDataScope(SysUser sysUser, List<SysUserDataScope> list) {

    }

    @Override
    public List<SysUser> findList(SysUserCriteria sysUserCriteria) {
        return null;
    }

    /**
     * 通过角色查找用户
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysUser> findListByRoleId(String roleId) {
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param sysUser
     */
    @Override
    public void updateUserInfo(SysUser sysUser) {

    }

    /**
     * 更新用户登录信息
     *
     * @param sysUser
     */
    @Override
    public void updateUserLoginInfo(SysUser sysUser) {

    }

    /**
     * 更新状态
     *
     * @param sysUser
     * @return
     */
    @Override
    public SysUser updateStatus(SysUser sysUser) {
        return null;
    }

    /**
     * 更新用户的密码
     *
     * @param userCode
     * @param newPassword
     */
    @Override
    public void updatePassword(String userCode, String newPassword) {

    }

    /**
     * 通过登录ID获取用户
     *
     * @param loginCode
     * @return
     */
    @Override
    public SysUser getByLoginCode(String loginCode) {
        return null;
    }

    /**
     * 通过用户类型和相关ID查找用户
     *
     * @param userType
     * @param refCode
     * @return
     */
    @Override
    public SysUser getByUserTypeAndRefCode(UserType userType, String refCode) {
        return null;
    }

    /**
     * 保存权限
     */
    @Override
    public void saveAuth() {

    }

    /**
     * 查找用户权限
     *
     * @param var1
     * @return
     */
    @Override
    public List<SysUserDataScope> findDataScopeList(SysUserDataScope var1) {
        return null;
    }

    /**
     * 根据条件删除人员
     *
     * @param sysUser
     * @author 刘东奇
     * @date 2019/9/6
     */
    @Override
    public void delete(SysUser sysUser) {

    }

    /**
     * 根据条件查询指定人员
     *
     * @param sysUser
     * @author 刘东奇
     * @date 2019/9/6
     */
    @Override
    public SysUser get(SysUser sysUser) {
        return null;
    }
}
