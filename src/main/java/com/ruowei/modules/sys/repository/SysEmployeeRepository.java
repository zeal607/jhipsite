package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysEmployee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeRepository
    extends BaseRepository<SysEmployee, Long> {
    /**
     * @author 刘东奇
     * @date 2019/9/25
     * @param sysCompanyId
     */
    Optional<SysEmployee> findFirstByEmpCodeNotNullAndSysCompanyIdOrderByEmpCodeDesc(String sysCompanyId);
}
