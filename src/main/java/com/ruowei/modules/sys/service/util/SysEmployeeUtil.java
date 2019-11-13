package com.ruowei.modules.sys.service.util;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataInvalidException;
import com.ruowei.config.SpringUtil;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.repository.SysEmployeeRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author 刘东奇
 * @date 2019/10/22
 */
public class SysEmployeeUtil {

    private static String EMP_CODE_REGEX = "^emp\\d{8}$";

    /**
     * 生成不重复的员工编码
     * @author 刘东奇
     * @date 2019/9/25
     * @param officeId
     * @param officeCode
     */
    public static String generateEmpCode(String officeId,String officeCode){
        //按EmpCode倒叙查询员工表第一条数据
        Optional<SysEmployee> sysEmployeeOptional = null;
        SysEmployeeRepository sysEmployeeRepository = SpringUtil.getBean(SysEmployeeRepository.class);
        if(officeId == null){
            sysEmployeeOptional = sysEmployeeRepository.findFirstByEmpCodeNotNullAndSysOfficeIdIsNullOrderByEmpCodeDesc();
        }else{
            sysEmployeeOptional = sysEmployeeRepository.findFirstByEmpCodeIsNotNullAndSysOfficeIdOrderByEmpCodeDesc(officeId);
        }
        if(StringUtils.isEmpty(officeCode)){
            officeCode = "000";
        }

        if(sysEmployeeOptional.isPresent()){
            SysEmployee sysEmployee = sysEmployeeOptional.get();
            String existEmpCode=sysEmployee.getEmpCode();
//            if( Pattern.matches(EMP_CODE_REGEX, existEmpCode)){
                //符合规则，则
                String code = existEmpCode.replaceAll("emp"+officeCode,"");
                Integer codeNum = new Integer(code);
                String newCode = String.format("%05d",codeNum+1);
                return "emp" + officeCode + newCode;
//            }else{
//                //不符合规则
//                //抛异常
//                throw new DataInvalidException(ErrorMessageUtils.getDataInvalidMessage(
//                    sysEmployee.getEntityName(),
//                    sysEmployee.getId().toString(),
//                    EMP_CODE_REGEX,
//                    existEmpCode));
//            }
        }else{
            //不存在
            return "emp"+officeCode+"00001";
        }
    }

}
