package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysUser;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRepository extends BaseRepository<SysUser,Long> {

}
