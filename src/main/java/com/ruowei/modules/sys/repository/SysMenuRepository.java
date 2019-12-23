package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysMenu;
import com.ruowei.modules.sys.domain.table.SysMenu;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMenuRepository
    extends BaseRepository<Long, SysMenu, QSysMenu> {

}
