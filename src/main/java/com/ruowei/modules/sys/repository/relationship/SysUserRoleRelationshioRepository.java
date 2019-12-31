package com.ruowei.modules.sys.repository.relationship;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.ralationship.QSysUserRoleRelationship;
import com.ruowei.modules.sys.domain.ralationship.SysUserRoleRelationship;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysRole entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRoleRelationshioRepository
    extends BaseRepository<Long, SysUserRoleRelationship, QSysUserRoleRelationship> {

}
