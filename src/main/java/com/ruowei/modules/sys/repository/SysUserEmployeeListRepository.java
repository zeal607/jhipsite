package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysUserEmployeeList;
import com.ruowei.modules.sys.domain.SysUserEmployeeList;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserEmployeeListRepository
    extends BaseRepository<Long, SysUserEmployeeList, QSysUserEmployeeList> {
}
