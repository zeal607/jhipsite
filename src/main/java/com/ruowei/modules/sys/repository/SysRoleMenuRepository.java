package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysRoleMenu;
import com.ruowei.modules.sys.domain.table.SysRoleMenu;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleMenuRepository
    extends BaseRepository<Long,SysRoleMenu, QSysRoleMenu> {

}
