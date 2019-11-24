package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.entity.QSysRole;
import com.ruowei.modules.sys.domain.entity.SysRole;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRole entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleRepository
    extends BaseRepository<Long,SysRole, QSysRole> {

}
