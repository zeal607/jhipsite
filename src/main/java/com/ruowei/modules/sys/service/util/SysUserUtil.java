package com.ruowei.modules.sys.service.util;

import com.ruowei.config.SpringUtil;
import com.ruowei.modules.sys.domain.entity.SysUser;
import com.ruowei.modules.sys.repository.SysUserRepository;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author 刘东奇
 * @date 2019/10/22
 */
public class SysUserUtil {
    private final static String USER_CODE_REGEX = "^user\\d{5}$";
    private final static Pattern USER_CODE_PATTERN = Pattern.compile("^user");

    private static final int DEF_COUNT = 20;
    /**
     * 生成不重复的用户编码
     * @author 刘东奇
     * @date 2019/10/22
     * @param
     */
    public static String generateUserCode(){
        SysUserRepository sysUserRepository = SpringUtil.getBean(SysUserRepository.class);
        //按UserCode倒叙查询用户表第一条数据
        Optional<SysUser> userOptional = sysUserRepository.findFirstByUserCodeNotNullOrderByUserCodeDesc();
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
                return "user10001";
                //抛异常
//                throw new DataInvalidException(ErrorMessageUtils.getDataInvalidMessage(
//                    user.getEntityName(),
//                    user.getId().toString(),
//                    USER_CODE_REGEX,
//                    existUserCode));
            }
        }else{
            return "user00001";
        }
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

}
