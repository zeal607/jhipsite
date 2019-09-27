package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysEmployeeOffice;
import com.ruowei.modules.sys.domain.SysEmployeePost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployeeOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeOfficeRepository
    extends BaseRepository<SysEmployeeOffice, Long> {

}
