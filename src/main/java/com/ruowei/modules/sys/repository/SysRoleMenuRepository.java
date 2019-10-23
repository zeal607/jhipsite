package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysRoleMenu;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleMenuRepository
    extends BaseRepository<SysRoleMenu, Long> {

}
