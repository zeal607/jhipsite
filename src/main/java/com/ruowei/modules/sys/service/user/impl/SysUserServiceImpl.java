package com.ruowei.modules.sys.service.user.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataAlreadyExistException;
import com.ruowei.common.error.exception.DataInvalidException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.*;
import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.mapper.SysEmployeeOfficeMapper;
import com.ruowei.modules.sys.mapper.SysEmployeePostMapper;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.mapper.SysUserRoleMapper;
import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.employee.SysEmployeeOfficeService;
import com.ruowei.modules.sys.service.employee.SysEmployeePostService;
import com.ruowei.modules.sys.service.employee.SysEmployeeService;
import com.ruowei.modules.sys.service.office.SysOfficeQueryService;
import com.ruowei.modules.sys.service.post.SysPostQueryService;
import com.ruowei.modules.sys.service.role.impl.SysRoleQueryService;
import com.ruowei.modules.sys.service.user.SysUserRoleService;
import com.ruowei.modules.sys.service.user.SysUserService;
import com.ruowei.modules.sys.service.company.SysCompanyQueryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysUserServiceImpl
    extends CrudBaseService<SysUser, Long, SysUserRepository>
    implements SysUserService {

    private final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * service依赖
     */
    private final SysUserQueryService sysUserQueryService;
    private final SysOfficeQueryService sysOfficeQueryService;
    private final SysCompanyQueryService sysCompanyQueryService;
    private final SysEmployeeService sysEmployeeService;
    private final SysEmployeePostService sysEmployeePostService;
    private final SysEmployeeOfficeService sysEmployeeOfficeService;
    private final SysUserRoleService sysUserRoleService;
    /**
     * mapper依赖
     */
    private final SysUserEmployeeMapper sysUserEmployeeMapper;;
    /**
     * 密码加密器
     */
    private final PasswordEncoder passwordEncoder;

    private final static String DEFAULT_PASSWORD = "123456";
    private final static String USER_CODE_REGEX = "^user\\d{5}$";
    private final static Pattern USER_CODE_PATTERN = Pattern.compile("^user");

    public SysUserServiceImpl(SysUserQueryService sysUserQueryService,
                              SysOfficeQueryService sysOfficeQueryService,
                              SysCompanyQueryService sysCompanyQueryService,
                              SysEmployeeService sysEmployeeService,
                              SysEmployeePostService sysEmployeePostService,
                              SysEmployeeOfficeService sysEmployeeOfficeService,
                              SysUserRoleService sysUserRoleService,
                              SysUserEmployeeMapper sysUserEmployeeMapper,
                              PasswordEncoder passwordEncoder) {
        this.sysUserQueryService = sysUserQueryService;
        this.sysOfficeQueryService = sysOfficeQueryService;
        this.sysCompanyQueryService = sysCompanyQueryService;
        this.sysEmployeeService = sysEmployeeService;
        this.sysEmployeePostService = sysEmployeePostService;
        this.sysEmployeeOfficeService = sysEmployeeOfficeService;
        this.sysUserRoleService = sysUserRoleService;

        this.sysUserEmployeeMapper = sysUserEmployeeMapper;

        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 通过条件分页查询机构员工
     *
     * @param userCriteria
     * @param employeeCriteria
     * @param page
     * @author 刘东奇
     * @date 2019/9/8
     */
    @Override
    public QueryResults<SysUserEmployeeVM> findSysUserEmployeeVMPageByCriteria(SysUserCriteria userCriteria, SysEmployeeCriteria employeeCriteria, Pageable page) {
        QueryResults<SysUserEmployeeVM> results = sysUserQueryService.findPageVMByCriteriaArray(page,userCriteria,employeeCriteria);
        return results;
    }

    /**
     * 通过主键获取员工信息
     *
     * @param id
     * @author 刘东奇
     * @date 2019/9/16
     */
    @Override
    public Optional<SysUserEmployeeDTO> getSysUserEmployeeById(Long id) {
        return sysUserQueryService.getDTOById(id);
    }

    /**
     * 创建员工
     *
     * @param sysUserEmployeeDTO
     * @author 刘东奇
     * @date 2019/9/22
     */
    @Override
    public SysUserEmployeeDTO createSysUserEmployee(SysUserEmployeeDTO sysUserEmployeeDTO) {
        String officeCode = null;

        if(sysUserEmployeeDTO.getSysOfficeId() != null){
            // 判断机构是否有效存在
            Optional<SysOfficeDTO> sysOfficeDTOOptional = sysOfficeQueryService.getDTOById(Long.valueOf(sysUserEmployeeDTO.getSysOfficeId()));
            if(sysOfficeDTOOptional.isPresent()){
                officeCode =sysOfficeDTOOptional.get().getOfficeCode();
            }else{
                // 机构不存在
                throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("机构",sysUserEmployeeDTO.getSysOfficeId()));
            }
        }

        if(sysUserEmployeeDTO.getSysCompanyId() != null){
            // 判断公司是否有效存在
            if(!sysCompanyQueryService.existsById(Long.valueOf(sysUserEmployeeDTO.getSysCompanyId()))){
                // 公司不存在
                throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("公司",sysUserEmployeeDTO.getSysCompanyId()));
            }
        }

        // 创建用户
        // 判断登录id、电话是否重复
        QSysUser qSysUser = QSysUser.sysUser;
        BooleanBuilder booleanBuilder = new BooleanBuilder(qSysUser.loginCode.eq(sysUserEmployeeDTO.getLoginCode()));
        HashMap map= new HashMap<String,String>();
        map.put("登录ID", sysUserEmployeeDTO.getLoginCode());
        if(StringUtils.isNotEmpty(sysUserEmployeeDTO.getMobile())){
            booleanBuilder.or(qSysUser.mobile.eq(sysUserEmployeeDTO.getMobile()));
            map.put("电话", sysUserEmployeeDTO.getMobile());
        }
        if(this.sysUserQueryService.exists(booleanBuilder)){
            throw new DataAlreadyExistException(ErrorMessageUtils.getAlreadyExistMessageByOrMap("用户",map));
        }
        SysUser sysUser = sysUserEmployeeMapper.projectIntoUser(sysUserEmployeeDTO);
        sysUser.setStatus(UserStatusType.NORMAL);
        sysUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
        sysUser.setUserCode(generateUserCode());
        sysUser = this.insert(sysUser);

        //创建员工
        SysEmployee sysEmployee = sysUserEmployeeMapper.projectIntoEmployee(sysUserEmployeeDTO);
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        // 生成员工编号
        sysEmployee.setEmpCode(sysEmployeeService.generateEmpCode(sysUserEmployeeDTO.getSysOfficeId(),officeCode));
        sysEmployeeService.insert(sysEmployee);

        // 保存岗位关系
        // 判断岗位是否存在
        sysEmployeePostService.saveEmployeePostRelationship(sysEmployee,sysUserEmployeeDTO.getSysPostDTOList());

        // 保存附属机构及岗位
        // 判断附属机构及岗位是否存在
        sysEmployeeOfficeService.saveEmployeeOfficePostRelationship(sysEmployee,sysUserEmployeeDTO.getSysEmployeeOfficeDTOList());

        // 保存角色关系
        sysUserRoleService.saveUserRoleRelationship(sysUser,sysUserEmployeeDTO.getSysRoleDTOList());

        Optional<SysUserEmployeeDTO> sysUserEmployeeDTOOptional = getSysUserEmployeeById(sysUser.getId());
        return sysUserEmployeeDTOOptional.get();
    }

    /**
     * 生成不重复的用户编码
     * @author 刘东奇
     * @date 2019/9/25
     * @param
     */
    private String generateUserCode(){
        //按UserCode倒叙查询用户表第一条数据
         Optional<SysUser> userOptional = this.jpaRepository.findFirstByUserCodeNotNullOrderByUserCodeDesc();
         if(userOptional.isPresent()){
             SysUser user = userOptional.get();
             String existUserCode=user.getUserCode();
             if( Pattern.matches(USER_CODE_REGEX, existUserCode)){
                //符合规则，则
                 String code = USER_CODE_PATTERN.matcher(existUserCode).replaceAll("");
                 Integer codeNum = new Integer(code);
                 String newCode = String.format("%05d",codeNum+1);
                 return "user"+newCode;
             }else{
                 //不符合规则
                 //抛异常
                 throw new DataInvalidException(ErrorMessageUtils.getDataInvalidMessage(
                     user.getEntityName(),
                     user.getId().toString(),
                     USER_CODE_REGEX,
                     existUserCode));
             }
         }else{
             return "user00001";
         }
    }


}
