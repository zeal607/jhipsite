package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysCompanyOffice;
import com.ruowei.modules.sys.domain.table.SysCompanyOffice;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCompanyOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCompanyOfficeRepository
    extends BaseRepository<Long, SysCompanyOffice, QSysCompanyOffice> {

}
