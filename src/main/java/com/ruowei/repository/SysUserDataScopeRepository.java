package com.ruowei.repository;

import com.ruowei.domain.SysUserDataScope;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysUserDataScope entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserDataScopeRepository extends JpaRepository<SysUserDataScope, Long>, JpaSpecificationExecutor<SysUserDataScope> {

}
