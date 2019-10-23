package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the SysUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRepository extends BaseRepository<SysUser,Long> {
    String USERS_BY_LOGIN_CODE_CACHE = "usersByLoginCode";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";


    /**
     * 通过usercode倒叙找第一个Sysuser
     * @author 刘东奇
     * @date 2019/9/25
     */
    Optional<SysUser> findFirstByUserCodeNotNullOrderByUserCodeDesc();

    Optional<SysUser> findOneByActivationKey(String activationKey);

    List<SysUser> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<SysUser> findOneByResetKey(String resetKey);

    Optional<SysUser> findOneByEmailIgnoreCase(String email);

    Optional<SysUser> findOneByLoginCode(String loginCode);

    @EntityGraph(attributePaths = "authorities")
    Optional<SysUser> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CODE_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByLoginCode(String loginCode);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<SysUser> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<SysUser> findAllByLoginCodeNot(Pageable pageable, String loginCode);

}
