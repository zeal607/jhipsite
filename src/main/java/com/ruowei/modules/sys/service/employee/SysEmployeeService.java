package com.ruowei.modules.sys.service.employee;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataInvalidException;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysEmployeeCriteria;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.modules.sys.pojo.SysEmployeeDTO;

import com.ruowei.modules.sys.repository.SysEmployeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysEmployeeService
    extends CrudBaseService<SysEmployee, Long, SysEmployeeRepository> {
    private static String EMP_CODE_REGEX = "^emp\\d{8}$";
    private static Pattern EMP_CODE_PATTERN = Pattern.compile("^emp\\d{3}");

    /**
     * 生成不重复的员工编码
     * @author 刘东奇
     * @date 2019/9/25
     * @param officeId
     * @param officeCode
     */
    public String generateEmpCode(String officeId,String officeCode){
        //按EmpCode倒叙查询员工表第一条数据
        Optional<SysEmployee> sysEmployeeOptional = null;
        if(officeId == null){
            sysEmployeeOptional = this.jpaRepository.findFirstByEmpCodeNotNullAndSysOfficeIdIsNullOrderByEmpCodeDesc();
        }else{
            sysEmployeeOptional = this.jpaRepository.findFirstByEmpCodeIsNotNullAndSysOfficeIdOrderByEmpCodeDesc(officeId);
        }
        if(StringUtils.isEmpty(officeCode)){
            officeCode = "000";
        }

        if(sysEmployeeOptional.isPresent()){
            SysEmployee sysEmployee = sysEmployeeOptional.get();
            String existEmpCode=sysEmployee.getEmpCode();
            if( Pattern.matches(EMP_CODE_REGEX, existEmpCode)){
                //符合规则，则
                String code = existEmpCode.replaceAll("emp"+officeCode,"");
                Integer codeNum = new Integer(code);
                String newCode = String.format("%05d",codeNum+1);
                return "emp" + officeCode + newCode;
            }else{
                //不符合规则
                //抛异常
                throw new DataInvalidException(ErrorMessageUtils.getDataInvalidMessage(
                    sysEmployee.getEntityName(),
                    sysEmployee.getId().toString(),
                    EMP_CODE_REGEX,
                    existEmpCode));
            }
        }else{
            //不存在
            return "emp"+officeCode+"00001";
        }
    }

}
