package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.QSysUserEmployeeListVM;
import com.ruowei.modules.sys.domain.SysUserEmployeeListVM;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserEmployeeListVMRepository
    extends BaseRepository<Long, SysUserEmployeeListVM, QSysUserEmployeeListVM> {
}
