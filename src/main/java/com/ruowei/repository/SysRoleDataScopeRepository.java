package com.ruowei.repository;

import com.ruowei.domain.SysRoleDataScope;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRoleDataScope entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleDataScopeRepository extends JpaRepository<SysRoleDataScope, Long>, JpaSpecificationExecutor<SysRoleDataScope> {

}
