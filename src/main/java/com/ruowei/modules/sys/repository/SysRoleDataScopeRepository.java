package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysRoleDataScope;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRoleDataScope entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleDataScopeRepository
    extends BaseRepository<SysRoleDataScope, Long> {

}
