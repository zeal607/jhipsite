package com.ruowei.modules.sys.service.alpha;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataAlreadyExistException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.modules.sys.domain.SysUserEmployeeDetailVM;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.table.*;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.pojo.SysEmployeeOfficeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.repository.SysEmployeeRepository;
import com.ruowei.modules.sys.repository.SysRoleRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.service.query.SysUserQueryService;
import com.ruowei.modules.sys.service.util.SysEmployeeUtil;
import com.ruowei.modules.sys.service.util.SysUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserEmployeeService implements SysUserEmployeeApi {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

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
     * @param sysUserEmployeeDetailVM
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public SysUserEmployeeDetailVM createSysUserEmployee(SysUserEmployeeDetailVM sysUserEmployeeDetailVM) {

        // 判断机构是否有效存在
        SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(Long.valueOf(sysUserEmployeeDetailVM.getSysOfficeId()));
        // 判断公司是否有效存在
        SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(Long.valueOf(sysUserEmployeeDetailVM.getSysCompanyId()));
        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(sysUserEmployeeDetailVM.getLoginCode(),sysUserEmployeeDetailVM.getMobile());

        //先创建员工
        SysEmployee sysEmployee = sysUserEmployeeMapper.SysUserEmployeeDetailVMToSysEmployee(sysUserEmployeeDetailVM);
        sysEmployee.setOfficeName(sysOffice.getOfficeName());
        sysEmployee.setCompanyName(sysCompany.getCompanyName());
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        sysEmployee.setEmpCode(SysEmployeeUtil.generateEmpCode(sysUserEmployeeDetailVM.getSysOfficeId(),sysOffice.getOfficeCode()));
        sysEmployee = sysEmployeeRepository.insert(sysEmployee);

        //再创建用户
        SysUser sysUser = sysUserEmployeeMapper.SysUserEmployeeDetailVMToSysUser(sysUserEmployeeDetailVM);
        sysUser.setUserType(UserType.EMPLOYEE);
        sysUser.setRefCode(sysEmployee.getId().toString());
        sysUser.setRefName(sysEmployee.getEmpName());
        sysUser.setStatus(UserStatusType.NORMAL);
        sysUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        sysUser.setUserCode(SysUserUtil.generateUserCode());
        sysUser = sysUserRepository.insert(sysUser);
        //返回员工表主键
        sysUserEmployeeDetailVM.setId(sysEmployee.getId());
        return sysUserEmployeeDetailVM;
    }

    /**
     * 判断用户是否已存在（登录ID、手机号是否被占用）
     * 如果存在，抛异常
     *
     * @param loginCode
     * @param mobile
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public void checkSysUserExists(String loginCode, String mobile) {
        // 创建用户
        // 判断登录id、电话是否重复
        QSysUser qSysUser = QSysUser.sysUser;
        BooleanBuilder booleanBuilder = new BooleanBuilder(qSysUser.loginCode.eq(loginCode));
        HashMap map= new HashMap<String,String>();
        map.put("登录ID", loginCode);
        if(StringUtils.isNotEmpty(mobile)){
            booleanBuilder.or(qSysUser.mobile.eq(mobile));
            map.put("手机号", mobile);
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
}
