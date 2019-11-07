package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysUserEmployeeLVM;
import com.ruowei.modules.sys.domain.SysUserEmployeeLVM;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserEmployeeLVMRepository
    extends BaseRepository<Long,SysUserEmployeeLVM, QSysUserEmployeeLVM> {
}
