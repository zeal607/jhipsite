package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysRoleDataScope;
import com.ruowei.modules.sys.domain.table.SysRoleDataScope;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRoleDataScope entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleDataScopeRepository
    extends BaseRepository<Long,SysRoleDataScope, QSysRoleDataScope> {

}
