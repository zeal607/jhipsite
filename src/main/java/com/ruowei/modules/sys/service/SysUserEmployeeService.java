package com.ruowei.modules.sys.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.lang.StringUtils;
import com.ruowei.common.service.BaseService;
import com.ruowei.modules.sys.domain.SysUserEmployeeDetail;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.table.*;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.repository.SysEmployeeRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.service.query.SysUserQueryService;
import com.ruowei.modules.sys.service.util.SysEmployeeUtil;
import com.ruowei.modules.sys.service.util.SysUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserEmployeeService extends BaseService implements SysUserEmployeeApi {

    private final Logger log = LoggerFactory.getLogger(SysUserEmployeeService.class);

    private final SysCompanyService sysCompanyService;
    private final SysOfficeService sysOfficeService;

    private final SysUserQueryService sysUserQueryService;

    private final SysUserRepository sysUserRepository;
    private final SysEmployeeRepository sysEmployeeRepository;

    private final SysUserEmployeeMapper sysUserEmployeeMapper;
    /**
     * 密码加密器
     */
    private final PasswordEncoder passwordEncoder;

    private final static String DEFAULT_PASSWORD = "123456";

    public SysUserEmployeeService(SysCompanyService sysCompanyService,
                                  SysOfficeService sysOfficeService,
                                  SysUserQueryService sysUserQueryService,
                                  SysUserRepository sysUserRepository,
                                  SysEmployeeRepository sysEmployeeRepository,
                                  SysUserEmployeeMapper sysUserEmployeeMapper,
                                  PasswordEncoder passwordEncoder) {
        this.sysCompanyService = sysCompanyService;
        this.sysOfficeService = sysOfficeService;

        this.sysUserQueryService = sysUserQueryService;

        this.sysUserRepository = sysUserRepository;
        this.sysEmployeeRepository = sysEmployeeRepository;

        this.sysUserEmployeeMapper = sysUserEmployeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 新增员工
     *
     * @param sysUserEmployeeDetail
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public SysUserEmployeeDetail createSysUserEmployee(SysUserEmployeeDetail sysUserEmployeeDetail) {

        // 判断机构是否有效存在
        SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(Long.valueOf(sysUserEmployeeDetail.getSysOfficeId()));
        // 判断公司是否有效存在
        SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(Long.valueOf(sysUserEmployeeDetail.getSysCompanyId()));
        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(sysUserEmployeeDetail.getLoginCode(), sysUserEmployeeDetail.getMobile(),null);

        //先创建员工
        SysEmployee sysEmployee = sysUserEmployeeMapper.SysUserEmployeeDetailVMToSysEmployee(sysUserEmployeeDetail);
        sysEmployee.setOfficeName(sysOffice.getOfficeName());
        sysEmployee.setCompanyName(sysCompany.getCompanyName());
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        sysEmployee.setEmpCode(SysEmployeeUtil.generateEmpCode(sysUserEmployeeDetail.getSysOfficeId(),sysOffice.getOfficeCode()));
        sysEmployee = sysEmployeeRepository.insert(sysEmployee);

        //再创建用户
        SysUser sysUser = sysUserEmployeeMapper.SysUserEmployeeDetailVMToSysUser(sysUserEmployeeDetail);
        sysUser.setUserType(UserType.EMPLOYEE);
        sysUser.setRefCode(sysEmployee.getId().toString());
        sysUser.setRefName(sysEmployee.getEmpName());
        sysUser.setStatus(UserStatusType.NORMAL);
        sysUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        sysUser.setUserCode(SysUserUtil.generateUserCode());
        sysUser = sysUserRepository.insert(sysUser);
        //返回员工表主键
        sysUserEmployeeDetail.setId(sysEmployee.getId());
        return sysUserEmployeeDetail;
    }

    /**
     * 修改员工
     *
     * @param sysUserEmployeeDetail
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public SysUserEmployeeDetail modifySysUserEmployee(SysUserEmployeeDetail sysUserEmployeeDetail) {
        // 判断机构是否有效存在
        if(StringUtils.isNotEmpty(sysUserEmployeeDetail.getSysOfficeId())){
            SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(Long.valueOf(sysUserEmployeeDetail.getSysOfficeId()));
        }
        // 判断公司是否有效存在
        if(StringUtils.isNotEmpty(sysUserEmployeeDetail.getSysCompanyId())){
            SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(Long.valueOf(sysUserEmployeeDetail.getSysCompanyId()));
        }
        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(sysUserEmployeeDetail.getLoginCode(), sysUserEmployeeDetail.getMobile(), sysUserEmployeeDetail.getId());

        //先更新员工
        SysEmployee sysEmployee = sysUserEmployeeMapper.SysUserEmployeeDetailVMToSysEmployee(sysUserEmployeeDetail);
        sysEmployee = sysEmployeeRepository.updateIgnoreNull(sysEmployee);

        //再更新用户
        SysUser sysUser = sysUserEmployeeMapper.SysUserEmployeeDetailVMToSysUser(sysUserEmployeeDetail);
        if(StringUtils.isNotEmpty(sysEmployee.getEmpName())){
            sysUser.setRefName(sysEmployee.getEmpName());
        }
        if(StringUtils.isNotEmpty(sysUser.getPassword())){
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        sysUser.setId(this.getSysUserIdBySysEmployeeId(sysEmployee.getId()));
        sysUser = sysUserRepository.updateIgnoreNull(sysUser);
        //返回员工表主键
        sysUserEmployeeDetail.setId(sysEmployee.getId());
        return sysUserEmployeeDetail;
    }

    /**
     * 判断用户是否已存在（登录ID、手机号是否被占用）
     * 如果存在，抛异常
     *
     * @param loginCode
     * @param mobile
     * @param employeeId 所更新员工的主键，如果传表示是更新操作，否则是新增操作
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public void checkSysUserExists(String loginCode, String mobile,Long employeeId) {
        // 创建用户
        // 判断登录id、电话是否重复
        QSysUser qSysUser = QSysUser.sysUser;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        HashMap map= new HashMap<String,String>();
        if(StringUtils.isNotEmpty(mobile)){
            booleanBuilder.or( qSysUser.loginCode.eq(loginCode));
            map.put("登录ID", loginCode);
        }
        if(StringUtils.isNotEmpty(mobile)){
            booleanBuilder.or(qSysUser.mobile.eq(mobile));
            map.put("手机号", mobile);
        }
        if(map.size()==0){
            return;
        }else if(employeeId != null){
            //curId不为空，表示是更新用户操作，且curId表示更新用户的主键
            booleanBuilder.and(qSysUser.refCode.castToNum(Long.class).ne(employeeId));
        }
        Assert.isTrue(!this.sysUserQueryService.exists(booleanBuilder),
            ErrorMessageUtils.getAlreadyExistMessageByOrMap("用户",map));
    }

    /**
     * 获取员工的角色列表
     *
     * @param id
     * @return
     * @author 刘东奇
     * @date 2019/11/13
     */
    @Override
    public List<SysRole> getSysRoleListBySysEmployeeId(Long id) {
        QSysRole qSysRole = QSysRole.sysRole;
        QSysUserRole qSysUserRole = QSysUserRole.sysUserRole;
        QSysUser qSysUser = QSysUser.sysUser;
        JPAQuery<SysRole> jpaQuery = this.queryFactory.selectFrom(qSysRole).leftJoin(qSysUserRole).on(qSysRole.id.eq(qSysUserRole.sysRoleId.castToNum(Long.class)))
            .leftJoin(qSysUser).on(qSysUser.id.eq(qSysUserRole.sysUserId.castToNum(Long.class)))
        .where(qSysUser.refCode.castToNum(Long.class).eq(id));
        return jpaQuery.fetch();
    }

    /**
     * 根据员工ID获取用户ID
     *
     * @param id
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public Long getSysUserIdBySysEmployeeId(Long id) {
        QSysUser qSysUser = QSysUser.sysUser;
        JPAQuery<Long> jpaQuery = this.queryFactory.select(qSysUser.id).from(qSysUser)
            .where(qSysUser.refCode.castToNum(Long.class).eq(id));
        return jpaQuery.fetchOne();
    }
    /**
     * 停用员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     */
    @Override
    public void disableSysEmployee(Long sysEmployeeId) {
        QSysUser qSysUser = QSysUser.sysUser;
        this.queryFactory.update(qSysUser).set(qSysUser.status,UserStatusType.DISABLE)
            .where(qSysUser.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
    }

    /**
     * 启用用户
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    @Override
    public void enableSysEmployee(Long sysEmployeeId) {
        QSysUser qSysUser = QSysUser.sysUser;
        this.queryFactory.update(qSysUser).set(qSysUser.status,UserStatusType.NORMAL)
            .where(qSysUser.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
    }

    /**
     * 删除员工
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    @Override
    public void deleteSysEmployee(Long sysEmployeeId) {
        QSysUser qSysUser = QSysUser.sysUser;
        this.queryFactory.update(qSysUser).set(qSysUser.status,UserStatusType.DELETE)
            .where(qSysUser.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();

        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        this.queryFactory.update(qSysEmployee).set(qSysEmployee.status,EmployeeStatusType.DELETE)
            .where(qSysEmployee.id.eq(sysEmployeeId)).execute();
    }

    /**
     * 重置员工密码
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @return
     */
    @Override
    public void resetSysEmployeePassword(Long sysEmployeeId) {
        QSysUser qSysUser = QSysUser.sysUser;
        this.queryFactory.update(qSysUser).set(qSysUser.password,passwordEncoder.encode(DEFAULT_PASSWORD))
            .where(qSysUser.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
    }

    /**
     * 给员工分配角色
     * @author 刘东奇
     * @date 2019/11/16
     * @param sysEmployeeId
     * @param roleIdList
     * @return
     */
    @Override
    public void assignRoleToSysEmployee(Long sysEmployeeId, List<Long> roleIdList) {

        if(roleIdList.size() > 0){
            Long sysUserId = this.getSysUserIdBySysEmployeeId(sysEmployeeId);
            SysUser sysUser = new SysUser();
            sysUser.setId(sysUserId);
            List<SysRole> sysRoleList = new ArrayList<>();
            for(Long roleId:roleIdList){
                SysRole sysRole = new SysRole();
                sysRole.setId(roleId);
                sysRoleList.add(sysRole);
            }
            sysUser.setSysRoleList(sysRoleList);
            this.sysUserRepository.updateIgnoreNull(sysUser);
        }


    }
}
