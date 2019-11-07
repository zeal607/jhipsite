package com.ruowei.modules.sys.service.user.impl;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.service.crud.CrudBaseService;
import com.ruowei.modules.sys.domain.table.QSysUserRole;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.domain.table.SysUserRole;
import com.ruowei.modules.sys.mapper.SysUserRoleMapper;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.service.role.impl.SysRoleQueryService;
import com.ruowei.modules.sys.service.user.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘东奇
 * @date 2019/9/27
 */
@Service
@Transactional
public class SysUserRoleServiceImpl
    extends CrudBaseService<
    SysUserRole,
    QSysUserRole,
    SysUserRoleRepository>
    implements SysUserRoleService {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);
    /**
     * service依赖
     */
    private final SysRoleQueryService sysRoleQueryService;
    /**
     * mapper依赖
     */
    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleServiceImpl(SysRoleQueryService sysRoleQueryService,
                                  SysUserRoleMapper sysUserRoleMapper){
        this.sysRoleQueryService = sysRoleQueryService;

        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * 保存角色关系
     *
     * @param sysUser
     * @param sysRoleDTOList
     * @author 刘东奇
     * @date 2019/9/30
     */
    @Override
    public void saveUserRoleRelationship(SysUser sysUser, List<SysRoleDTO> sysRoleDTOList) {
        if(sysRoleDTOList != null){
            for(SysRoleDTO sysRoleDTO:sysRoleDTOList){
                if(sysRoleQueryService.existsById(sysRoleDTO.getId())){
                    SysUserRole sysUserRole = sysUserRoleMapper.assembleEntity(sysRoleDTO,sysUser);
                    this.save(sysUserRole);
                }else{
                    throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("角色",sysRoleDTO.getId().toString()));
                }
            }
        }
    }
}
