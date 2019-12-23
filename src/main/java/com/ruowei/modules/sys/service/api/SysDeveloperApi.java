package com.ruowei.modules.sys.service.api;

import com.ruowei.modules.sys.domain.entity.SysDeveloperUser;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
     * 根据登录ID获取用户信息并携带权限
     * @author 刘东奇
     * @date 2019/11/14
     * @param loginCode
     * @return
     */
    Optional<UserDTO> getUserWithAuthoritiesByLoginCode(String loginCode);

    /**
     * 根据登录ID删除用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param loginCode
     * @return
     */
    void deleteUserByLoginCode(String loginCode);

    /**
     * 注册用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param userDTO
     * @param password
     * @return
     */
    SysDeveloperUser registerUser(UserDTO userDTO, String password);


    /**
     * 根据激活码激活用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param key
     * @return
     */
    Optional<SysUser> activateRegistration(String key);

    /**
     * 获取当前登录用户信息并携带权限
     * @author 刘东奇
     * @date 2019/10/21
     * @param
     * @return
     */
    Optional<UserDTO> getCurUserWithAuthorities();
    /**
     * 通过LoginCode查询用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param loginCode
     * @return
     */
    Optional<SysDeveloperUser> findOneByLoginCode(String loginCode);

    /**
     * 通过email查询用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param email
     * @return
     */
    Optional<SysDeveloperUser> findOneByEmailIgnoreCase(String email);

    /**
     * 更新用户
     * @author 刘东奇
     * @date 2019/10/21
     * @param userDTO
     * @return
     */
    Optional<UserDTO> updateUser(UserDTO userDTO);

    /**
     * 更新用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param firstName
     * @param lastName
     * @param email
     * @param langKey
     * @param imageUrl
     * @return
     */
    void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl);

        /**
         * 修改密码
         * @author 刘东奇
         * @date 2019/10/21
         * @param currentClearTextPassword
         * @param newPassword
         */
    void changePassword(String currentClearTextPassword, String newPassword);

    /**
     * 请求密码重置
     * @author 刘东奇
     * @date 2019/10/21
     * @param mail
     */
    void requestPasswordReset(String mail);

    /**
     * 根据新密码和重置码完成密码重置
     * @author 刘东奇
     * @date 2019/10/21
     * @param newPassword
     * @param key
     * @return
     */
    Optional<SysUser> completePasswordReset(String newPassword, String key);

    /**
     * 分页获取用户
     * @author 刘东奇
     * @date 2019/11/14
     * @param pageable
     * @return
     */
    Page<UserDTO> getAllManagedUsers(Pageable pageable);

    /**
     * 创建用户
     * @author 刘东奇
     * @date 2019/11/15
     * @param userDTO
     * @return
     */
    SysDeveloperUser createUser(UserDTO userDTO);

}
