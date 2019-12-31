package com.ruowei.modules.sys.service.util;

import java.util.UUID;

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
        return UUID.randomUUID().toString();
    }

}
