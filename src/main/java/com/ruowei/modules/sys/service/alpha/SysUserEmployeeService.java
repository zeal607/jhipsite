package com.ruowei.modules.sys.service.alpha;

import com.querydsl.core.BooleanBuilder;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataAlreadyExistException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.domain.table.*;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.repository.SysEmployeeRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.api.SysUserEmployeeApi;
import com.ruowei.modules.sys.service.query.SysUserQueryService;
import com.ruowei.modules.sys.service.util.SysEmployeeUtil;
import com.ruowei.modules.sys.service.util.SysUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserEmployeeService implements SysUserEmployeeApi {

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
     * @param sysUserEmployeeDTO
     * @return
     * @author 刘东奇
     * @date 2019/11/8
     */
    @Override
    public SysUserEmployeeDTO createSysUserEmployee(SysUserEmployeeDTO sysUserEmployeeDTO) {

        // 判断机构是否有效存在
        SysOffice sysOffice = sysOfficeService.checkOfficeExistsById(Long.valueOf(sysUserEmployeeDTO.getSysOfficeId()));
        // 判断公司是否有效存在
        SysCompany sysCompany = sysCompanyService.checkCompanyExistsById(Long.valueOf(sysUserEmployeeDTO.getSysCompanyId()));
        // 判断登录ID、手机号是否重复
        this.checkSysUserExists(sysUserEmployeeDTO.getLoginCode(),sysUserEmployeeDTO.getMobile());

        //先创建员工
        SysEmployee sysEmployee = sysUserEmployeeMapper.projectIntoEmployee(sysUserEmployeeDTO);
        sysEmployee.setOfficeName(sysOffice.getOfficeName());
        sysEmployee.setCompanyName(sysCompany.getCompanyName());
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        sysEmployee.setEmpCode(SysEmployeeUtil.generateEmpCode(sysUserEmployeeDTO.getSysOfficeId(),sysOffice.getOfficeCode()));
        sysEmployee = sysEmployeeRepository.insert(sysEmployee);

        //再创建用户
        SysUser sysUser = sysUserEmployeeMapper.projectIntoUser(sysUserEmployeeDTO);
        sysUser.setId(sysEmployee.getId());
        sysUser.setUserType(UserType.EMPLOYEE);
        sysUser.setRefCode(sysEmployee.getId().toString());
        sysUser.setRefName(sysEmployee.getEmpName());
        sysUser.setStatus(UserStatusType.NORMAL);
        sysUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        sysUser.setUserCode(SysUserUtil.generateUserCode());
        sysUser = sysUserRepository.insert(sysUser);
        sysUserEmployeeDTO.setId(sysUser.getId());
        return sysUserEmployeeDTO;
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
}
