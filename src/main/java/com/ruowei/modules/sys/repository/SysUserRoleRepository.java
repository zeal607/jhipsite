package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.domain.SysUserRole;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysUserRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRoleRepository extends BaseRepository<SysUserRole, Long> {

}
