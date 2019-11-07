package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysEmployeeOffice;
import com.ruowei.modules.sys.domain.table.SysEmployeeOffice;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployeeOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeOfficeRepository
    extends BaseRepository<Long, SysEmployeeOffice, QSysEmployeeOffice> {

}
