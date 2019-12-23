package com.ruowei.modules.sys.repository;

import com.ruowei.modules.sys.domain.entity.SysDeveloperUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface SysDeveloperUserRepository extends JpaRepository<SysDeveloperUser, Long> {

    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<SysDeveloperUser> findOneByActivationKey(String activationKey);


    List<SysDeveloperUser> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);


    Optional<SysDeveloperUser> findOneByResetKey(String resetKey);

    Optional<SysDeveloperUser> findOneByEmailIgnoreCase(String email);

    Optional<SysDeveloperUser> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<SysDeveloperUser> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<SysDeveloperUser> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<SysDeveloperUser> findOneWithAuthoritiesByEmail(String email);

    Page<SysDeveloperUser> findAllByLoginNot(Pageable pageable, String login);
}
