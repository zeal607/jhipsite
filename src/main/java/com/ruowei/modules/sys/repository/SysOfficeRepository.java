package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysOffice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysOfficeRepository
    extends BaseRepository<SysOffice, Long> {
}
