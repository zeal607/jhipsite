package com.ruowei.repository;

import com.ruowei.domain.SysRoleMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRoleMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenu, Long>, JpaSpecificationExecutor<SysRoleMenu> {

}
