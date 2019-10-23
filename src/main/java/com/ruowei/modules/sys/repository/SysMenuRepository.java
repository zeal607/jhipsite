package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysMenu;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMenuRepository
    extends BaseRepository<SysMenu, Long> {

}
