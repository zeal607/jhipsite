package com.ruowei.modules.sys.web.api;

import com.ruowei.modules.sys.pojo.PasswordChangeDTO;
import com.ruowei.modules.sys.pojo.UserDTO;
import com.ruowei.modules.sys.pojo.user.SysUserRegisterDTO;
import com.ruowei.modules.sys.web.vm.KeyAndPasswordVM;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/11/14
 */
public interface SysDeveloperApi {

    /**
     * 获取所有权限
     * @author 刘东奇
     * @date 2019/10/21
     * @param
     */
    List<String> getAuthorities();

    /**
     * 根据loginCode获取用户信息
     * @author 刘东奇
     * @date 2019/11/14
     * @param login
     * @return
     */
    ResponseEntity<UserDTO> getUser(@PathVariable String login);

    /**
     * 根据loginCode删除用户信息
     * @author 刘东奇
     * @date 2019/11/14
     * @param login
     * @return
     */
    ResponseEntity<Void> deleteUser(@PathVariable String login);

    /**
     * 注册用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param sysUserRegisterDTO
     * @return
     */
    void registerAccount(@Valid @RequestBody SysUserRegisterDTO sysUserRegisterDTO);

    /**
     * 激活用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param key
     * @return
     */
    void activateAccount(@RequestParam(value = "key") String key);

    /** 检查登录者是否有权限并且返回其登录ID
     * @author 刘东奇
     * @date 2019/11/14
     * @param request
     * @return
     */
    String isAuthenticated(HttpServletRequest request);

    /**
     * 获取当前用户信息及权限
     * @author 刘东奇
     * @date 2019/11/14
     * @return
     */
    UserDTO getAccount();


    /** 更新当前账号信息
     * @author 刘东奇
     * @date 2019/11/14
     * @param userDTO
     * @return
     */
    void saveAccount(@Valid @RequestBody UserDTO userDTO);

    /**
     * 修改当前账号密码
     * @author 刘东奇
     * @date 2019/11/14
     * @param passwordChangeDto
     * @return
     */
    void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto);

    /**
     * 重置账号密码
     * @author 刘东奇
     * @date 2019/11/14
     * @param mail
     * @return
     */
    void requestPasswordReset(@RequestBody String mail);

    /**
     * 完成密码重置
     * @author 刘东奇
     * @date 2019/11/14
     * @param keyAndPassword
     * @return
     */
    void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword);

    /**
     * 更新用户信息
     * @author 刘东奇
     * @date 2019/11/14
     * @param userDTO
     * @return
     */
    ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO);

    /**
     * 分页获取用户数据
     * @author 刘东奇
     * @date 2019/11/14
     * @param queryParams
     * @param uriBuilder
     * @param pageable
     * @return
     */
    ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder, Pageable pageable);

    /**
     * 创建用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param userDTO
     * @return
     * @throws URISyntaxException
     */
    ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException;
}
