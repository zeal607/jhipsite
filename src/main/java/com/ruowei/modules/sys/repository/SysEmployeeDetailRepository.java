package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.entity.QSysEmployeeDetail;
import com.ruowei.modules.sys.domain.entity.SysEmployeeDetail;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeDetailRepository
    extends BaseRepository<Long, SysEmployeeDetail, QSysEmployeeDetail> {
}
