package com.ruowei.modules.sys.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.lang.ObjectUtils;
import com.ruowei.common.lang.StringUtils;
import com.ruowei.common.service.BaseService;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployeeOfficePost;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.ralationship.QSysUserRoleRelationship;
import com.ruowei.modules.sys.domain.ralationship.SysUserRoleRelationship;
import com.ruowei.modules.sys.domain.table.QSysEmployeeTable;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
import com.ruowei.modules.sys.domain.table.SysCompany;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.repository.SysEmployeeTableRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.repository.entity.SysEmployeeRepository;
import com.ruowei.modules.sys.repository.relationship.SysUserRoleRelationshioRepository;
import com.ruowei.modules.sys.service.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.service.query.SysUserQueryService;
import com.ruowei.modules.sys.service.util.SysEmployeeUtil;
import com.ruowei.modules.sys.service.util.SysUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

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
    private final SysEmployeeTableRepository sysEmployeeTableRepository;
    private final SysUserRoleRelationshioRepository sysUserRoleRelationshioRepository;
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
                                  SysEmployeeTableRepository sysEmployeeTableRepository,
                                  SysUserRoleRelationshioRepository sysUserRoleRelationshioRepository, SysEmployeeRepository sysEmployeeRepository, SysUserEmployeeMapper sysUserEmployeeMapper,
                                  PasswordEncoder passwordEncoder) {
        this.sysCompanyService = sysCompanyService;
        this.sysOfficeService = sysOfficeService;

        this.sysUserQueryService = sysUserQueryService;

        this.sysUserRepository = sysUserRepository;
        this.sysEmployeeTableRepository = sysEmployeeTableRepository;
        this.sysUserRoleRelationshioRepository = sysUserRoleRelationshioRepository;
        this.sysEmployeeRepository = sysEmployeeRepository;

        this.sysUserEmployeeMapper = sysUserEmployeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 新增员工
     *
     * @param sysEmployee
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public SysEmployee createSysUserEmployee(SysEmployee sysEmployee) {
        // 判断机构是否有效存在
        SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(sysEmployee.getOffice().getId());
        sysEmployee.setOffice(sysOffice);
        // 判断公司是否有效存在
        SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(sysEmployee.getCompany().getId());
        sysEmployee.setCompany(sysCompany);
        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(
            sysEmployee.getUser().getLoginCode(),
            sysEmployee.getUser().getMobile(),
            sysEmployee.getUser().getEmail(),
            null);

        //员工信息
        sysEmployee.setEmpCode(SysEmployeeUtil.generateEmpCode(sysEmployee.getOffice().getId().toString(),sysEmployee.getOffice().getOfficeCode()));
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        sysEmployee.setCompanyName(sysEmployee.getCompany().getCompanyName());
        sysEmployee.setOfficeName(sysEmployee.getOffice().getOfficeName());
        //用户信息
        sysEmployee.getUser().setPassword((passwordEncoder.encode(DEFAULT_PASSWORD)));
        sysEmployee.getUser().setUserCode(SysUserUtil.generateUserCode());
        sysEmployee.getUser().setUserType(UserType.EMPLOYEE);
        sysEmployee.getUser().setStatus(UserStatusType.NORMAL);
        sysEmployee.getUser().setRefName(sysEmployee.getEmpName());
        sysEmployee.getUser().setEmployee(sysEmployee);
        //员工机构职务信息
        for(SysEmployeeOfficePost info: sysEmployee.getOfficePostList()){
            info.setEmployee(sysEmployee);
        }
        sysEmployeeRepository.save(sysEmployee);
        return sysEmployee;
    }

    /**
     * 修改员工
     *
     * @param newSysEmployee
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    @Override
    public SysEmployee modifySysUserEmployee(SysEmployee newSysEmployee) {
        // 判断登录ID、手机号、电子邮箱、员工编号是否重复
        this.checkSysUserExists(
            newSysEmployee.getUser().getLoginCode(),
            newSysEmployee.getUser().getMobile(),
            newSysEmployee.getUser().getEmail(),
            newSysEmployee.getId());
        // 判断员工是否存在
        Assert.isTrue(sysEmployeeRepository.existsById(newSysEmployee.getId()),
            ErrorMessageUtils.getNotFoundMessage(
                "员工", ObjectUtils.toString(newSysEmployee.getId()))
        );

        QSysEmployeeTable qSysEmployeeTable = QSysEmployeeTable.sysEmployeeTable;
        JPAUpdateClause sysEmployeeTableUpdateClause = this.queryFactory.update(qSysEmployeeTable);
        // 判断机构是否有效存在
        if(newSysEmployee.getOffice() !=null){
            SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(newSysEmployee.getOffice().getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.sysOfficeId,sysOffice.getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.officeName,sysOffice.getOfficeName());
        }
        // 判断公司是否有效存在
        if(newSysEmployee.getCompany() != null){
            SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(newSysEmployee.getCompany().getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.sysCompanyId,sysCompany.getId());
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.companyName,sysCompany.getCompanyName());
        }
        //判断员工编号
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpCode())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.empCode,newSysEmployee.getEmpCode());
        }
        //判断员工姓名
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpName())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.empName,newSysEmployee.getEmpName());
        }
        //判断员工英文名
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpNameEn())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.empNameEn,newSysEmployee.getEmpNameEn());
        }
        //判断备注信息
        if(StringUtils.isNotEmpty(newSysEmployee.getRemarks())){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.remarks,newSysEmployee.getRemarks());
        }
        //判断状态
        if(newSysEmployee.getStatus()!=null){
            sysEmployeeTableUpdateClause.set(qSysEmployeeTable.status,newSysEmployee.getStatus());
        }
        // 判断用户
        if(newSysEmployee.getUser() != null){
            SysUser sysUser = newSysEmployee.getUser();
            QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
            JPAUpdateClause sysUserTableUpdateClause = this.queryFactory.update(qSysUserTable);
            //用户编号
            if(StringUtils.isNotEmpty(sysUser.getUserCode())){
                sysUserTableUpdateClause.set(qSysUserTable.userCode,sysUser.getUserCode());
            }
            //登录账号
            if(StringUtils.isNotEmpty(sysUser.getLoginCode())){
                sysUserTableUpdateClause.set(qSysUserTable.loginCode,sysUser.getLoginCode());
            }
            //用户昵称
            if(StringUtils.isNotEmpty(sysUser.getUserName())){
                sysUserTableUpdateClause.set(qSysUserTable.userName,sysUser.getUserName());
            }
            //密码
            if(StringUtils.isNotEmpty(sysUser.getPassword())){
                sysUserTableUpdateClause.set(qSysUserTable.password,passwordEncoder.encode(sysUser.getPassword()));
            }
            //电子邮箱
            if(StringUtils.isNotEmpty(sysUser.getEmail())){
                sysUserTableUpdateClause.set(qSysUserTable.email,sysUser.getEmail());
            }
            //手机号
            if(StringUtils.isNotEmpty(sysUser.getMobile())){
                sysUserTableUpdateClause.set(qSysUserTable.mobile,sysUser.getMobile());
            }
            //办公电话
            if(StringUtils.isNotEmpty(sysUser.getPhone())){
                sysUserTableUpdateClause.set(qSysUserTable.phone,sysUser.getPhone());
            }
            //用户性别
            if(sysUser.getSex()!=null){
                sysUserTableUpdateClause.set(qSysUserTable.sex,sysUser.getSex());
            }
            //头像相对路径
            if(StringUtils.isNotEmpty(sysUser.getAvatar())){
                sysUserTableUpdateClause.set(qSysUserTable.avatar,sysUser.getAvatar());
            }
            //个性签名
            if(StringUtils.isNotEmpty(sysUser.getSign())){
                sysUserTableUpdateClause.set(qSysUserTable.sign,sysUser.getSign());
            }
            //绑定的微信号
            if(StringUtils.isNotEmpty(sysUser.getSign())){
                sysUserTableUpdateClause.set(qSysUserTable.wxOpenid,sysUser.getWxOpenid());
            }
            //绑定的手机MID
            if(StringUtils.isNotEmpty(sysUser.getMobileImei())){
                sysUserTableUpdateClause.set(qSysUserTable.mobileImei,sysUser.getMobileImei());
            }
            //用户类型
            if(sysUser.getUserType()!=null){
                sysUserTableUpdateClause.set(qSysUserTable.userType,sysUser.getUserType());
            }
            //用户类型引用姓名
            if(StringUtils.isNotEmpty(sysUser.getRefName())||
                StringUtils.isNotEmpty(newSysEmployee.getEmpName())
            ){
                sysUserTableUpdateClause.set(qSysUserTable.refName,newSysEmployee.getEmpName());
            }

            SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(newSysEmployee.getCompany().getId());
            jpaUpdateClause.set(qSysEmployeeTable.sysCompanyId,sysCompany.getId());
            jpaUpdateClause.set(qSysEmployeeTable.companyName,sysCompany.getCompanyName());
        }


        SysEmployee oldSysEmployee = sysEmployeeOptional.get();
        //获取空属性并处理成null
        Set<String> nullProperties = ObjectUtils.getNullPropertiesSet(newSysEmployee);
        nullProperties.add("user");
        //更新非空属性
        BeanUtils.copyProperties(newSysEmployee, oldSysEmployee, nullProperties.toArray(new String[0]));
        if(newSysEmployee.getUser()!=null){
            nullProperties = ObjectUtils.getNullPropertiesSet(newSysEmployee.getUser());
            //不支持用户变更
            nullProperties.add("id");
            //不支持密码修改
            nullProperties.add("password");
            BeanUtils.copyProperties(newSysEmployee.getUser(), oldSysEmployee.getUser(), nullProperties.toArray(new String[0]));
        }
        //更新
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpName())){
            oldSysEmployee.getUser().setRefName(oldSysEmployee.getEmpName());
        }
        //员工机构职务信息
        if(newSysEmployee.getOfficePostList()!=null){
            for(SysEmployeeOfficePost info: oldSysEmployee.getOfficePostList()){
                info.setEmployee(oldSysEmployee);
            }
        }
        sysEmployeeRepository.save(oldSysEmployee);
        return oldSysEmployee;
    }

    public SysEmployee modifySysUserEmployee2(SysEmployee newSysEmployee) {

        // 判断机构是否有效存在
        if(newSysEmployee.getOffice()!=null){
            SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(newSysEmployee.getOffice().getId());
            newSysEmployee.setOffice(sysOffice);
        }
        // 判断公司是否有效存在
        if(newSysEmployee.getCompany()!=null){
            SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(newSysEmployee.getCompany().getId());
            newSysEmployee.setCompany(sysCompany);
        }

        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(
            newSysEmployee.getUser().getLoginCode(),
            newSysEmployee.getUser().getMobile(),
            newSysEmployee.getUser().getEmail(),
            newSysEmployee.getId());

        Optional<SysEmployee> sysEmployeeOptional = sysEmployeeRepository.findById(newSysEmployee.getId());
        Assert.isTrue(sysEmployeeOptional.isPresent(),
            ErrorMessageUtils.getNotFoundMessage(
                "员工", ObjectUtils.toString(newSysEmployee.getId()))
        );
        SysEmployee oldSysEmployee = sysEmployeeOptional.get();
        //获取空属性并处理成null
        Set<String> nullProperties = ObjectUtils.getNullPropertiesSet(newSysEmployee);
        nullProperties.add("user");
        //更新非空属性
        BeanUtils.copyProperties(newSysEmployee, oldSysEmployee, nullProperties.toArray(new String[0]));
        if(newSysEmployee.getUser()!=null){
            nullProperties = ObjectUtils.getNullPropertiesSet(newSysEmployee.getUser());
            //不支持用户变更
            nullProperties.add("id");
            //不支持密码修改
            nullProperties.add("password");
            BeanUtils.copyProperties(newSysEmployee.getUser(), oldSysEmployee.getUser(), nullProperties.toArray(new String[0]));
        }
        //更新
        if(StringUtils.isNotEmpty(newSysEmployee.getEmpName())){
            oldSysEmployee.getUser().setRefName(oldSysEmployee.getEmpName());
        }
        //员工机构职务信息
        if(newSysEmployee.getOfficePostList()!=null){
            for(SysEmployeeOfficePost info: oldSysEmployee.getOfficePostList()){
                info.setEmployee(oldSysEmployee);
            }
        }
        sysEmployeeRepository.save(oldSysEmployee);
        return oldSysEmployee;
    }

    /**
     * 判断用户是否已存在（登录ID、手机号、邮箱是否被占用）
     * 如果存在，抛异常
     *
     * @param loginCode
     * @param mobile
     * @param email
     * @param employeeId 所更新员工的主键，如果传表示是更新操作，否则是新增操作
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public void checkSysUserExists(String loginCode, String mobile, String email, Long employeeId) {
        // 创建用户
        // 判断登录id、电话是否重复
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        HashMap<String,String> map= new HashMap<String,String>();
        if(StringUtils.isNotEmpty(loginCode)){
            booleanBuilder.or( qSysUserTable.loginCode.eq(loginCode));
            map.put("登录ID", loginCode);
        }
        if(StringUtils.isNotEmpty(mobile)){
            booleanBuilder.or(qSysUserTable.mobile.eq(mobile));
            map.put("手机号", mobile);
        }
        if(StringUtils.isNotEmpty(email)){
            booleanBuilder.or(qSysUserTable.email.eq(email));
            map.put("电子邮箱", email);
        }
        if(map.size()==0){
            return;
        }else if(employeeId != null){
            //curId不为空，表示是更新用户操作，且curId表示更新用户的主键
            booleanBuilder.and(qSysUserTable.refCode.castToNum(Long.class).ne(employeeId));
        }
        Assert.isTrue(!this.sysUserQueryService.exists(booleanBuilder),
            ErrorMessageUtils.getAlreadyExistMessageByOrMap("用户",map));
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
        QSysUserTable qSysUser = QSysUserTable.sysUserTable;
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
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.status,UserStatusType.DISABLE)
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
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
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.status,UserStatusType.NORMAL)
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
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
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.status,UserStatusType.DELETE)
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();

        QSysEmployeeTable qSysEmployeeTable = QSysEmployeeTable.sysEmployeeTable;
        this.queryFactory.update(qSysEmployeeTable).set(qSysEmployeeTable.status,EmployeeStatusType.DELETE)
            .where(qSysEmployeeTable.id.eq(sysEmployeeId)).execute();
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
        QSysUserTable qSysUserTable = QSysUserTable.sysUserTable;
        this.queryFactory.update(qSysUserTable).set(qSysUserTable.password,passwordEncoder.encode(DEFAULT_PASSWORD))
            .where(qSysUserTable.refCode.castToNum(Long.class).eq(sysEmployeeId)).execute();
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
        //员工ID转换成用户ID
        Long sysUserId = this.getSysUserIdBySysEmployeeId(sysEmployeeId);
        //先删除
        QSysUserRoleRelationship qSysUserRoleRelationship = QSysUserRoleRelationship.sysUserRoleRelationship;
        sysUserRoleRelationshioRepository.deleteAll(qSysUserRoleRelationship.sysUserId.eq(sysUserId));
        //后新增
        List<SysUserRoleRelationship> userRoleRelationshipList = new ArrayList<>();

        for(Long roldId:roleIdList){
            SysUserRoleRelationship userRoleRelationship = new SysUserRoleRelationship();
            userRoleRelationship.setSysRoleId(roldId);
            userRoleRelationship.setSysUserId(sysUserId);
            userRoleRelationshipList.add(userRoleRelationship);
        }
        sysUserRoleRelationshioRepository.saveAll(userRoleRelationshipList);

    }
}
