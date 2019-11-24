package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.entity.QSysEmployee;
import com.ruowei.modules.sys.domain.entity.SysEmployee;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.Optional;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeRepository
    extends BaseRepository<Long, SysEmployee, QSysEmployee> {
    /**
     * @author 刘东奇
     * @date 2019/9/25
     * @param sysOfficeId
     */
    Optional<SysEmployee> findFirstByEmpCodeIsNotNullAndSysOfficeIdOrderByEmpCodeDesc(@Size(max = 100) String sysOfficeId);

    /**
     * @author 刘东奇
     * @date 2019/9/25
     */
    Optional<SysEmployee> findFirstByEmpCodeNotNullAndSysOfficeIdIsNullOrderByEmpCodeDesc();
}
