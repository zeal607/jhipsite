package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysUserTable;
import com.ruowei.modules.sys.domain.table.SysUserTable;
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
public interface SysUserRepository
    extends BaseRepository<Long, SysUserTable, QSysUserTable> {

    String USERS_BY_LOGIN_CODE_CACHE = "usersByLoginCode";
    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    /**
     * 通过usercode倒叙找第一个Sysuser
     * @author 刘东奇
     * @date 2019/9/25
     */
    Optional<SysUserTable> findFirstByUserCodeNotNullOrderByUserCodeDesc();

    Optional<SysUserTable> findOneByActivationKey(String activationKey);

    List<SysUserTable> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<SysUserTable> findOneByResetKey(String resetKey);

    Optional<SysUserTable> findOneByEmailIgnoreCase(String email);

    Optional<SysUserTable> findOneByLoginCode(String loginCode);

    @EntityGraph(attributePaths = "authorities")
    Optional<SysUserTable> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CODE_CACHE)
    Optional<SysUserTable> findOneWithAuthoritiesByLoginCode(String loginCode);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<SysUserTable> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<SysUserTable> findAllByLoginCodeNot(Pageable pageable, String loginCode);
}
