package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysUser;
import com.ruowei.modules.sys.domain.table.QSysUserRole;
import com.ruowei.modules.sys.domain.table.SysUserRole;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysUserRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRoleRepository
    extends BaseRepository<Long,SysUserRole, QSysUserRole> {

}
