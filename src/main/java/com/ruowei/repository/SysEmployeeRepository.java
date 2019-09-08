package com.ruowei.repository;

import com.ruowei.domain.SysEmployee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysEmployeeRepository extends JpaRepository<SysEmployee, Long>, JpaSpecificationExecutor<SysEmployee> {

}
