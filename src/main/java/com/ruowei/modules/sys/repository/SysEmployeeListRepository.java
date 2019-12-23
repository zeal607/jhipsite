package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.entity.QSysEmployeeList;
import com.ruowei.modules.sys.domain.entity.SysEmployeeList;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeListRepository
    extends BaseRepository<Long, SysEmployeeList, QSysEmployeeList> {
}
