package com.ruowei.modules.sys.service.user;

import com.ruowei.modules.sys.api.SysUserApi;
import com.ruowei.modules.sys.pojo.SysUserDTO;

import java.util.Optional;

/**
 * @author 刘东奇
 * @date 2019/9/27
 */
public interface SysUserService extends SysUserApi {

    /**
     * 通过LoginCode查询用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param loginCode
     * @return
     */
    Optional<SysUserDTO> findOneByLoginCode(String loginCode);

    /**
     * 通过email查询用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param email
     */
    Optional<SysUserDTO> findOneByEmailIgnoreCase(String email);

}
