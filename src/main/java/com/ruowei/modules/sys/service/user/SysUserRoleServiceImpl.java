package com.ruowei.modules.sys.service.user;

import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.pojo.SysUserRoleDTO;
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
    extends CrudBaseService<SysUserRole, Long, BaseView, SysUserRoleDTO, SysUserRoleRepository>
    implements SysUserRoleService {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);
    /**
     * service依赖
     */
    private final SysUserRoleQueryService sysUserRoleQueryService;

    public SysUserRoleServiceImpl(SysUserRoleQueryService sysUserRoleQueryService){
        this.sysUserRoleQueryService = sysUserRoleQueryService;
    }

    /**
     * 通过用户id获取角色列表
     *
     * @param sysUserId
     * @author 刘东奇
     * @date 2019/9/27
     */
    @Override
    public List<SysRoleDTO> getSysRoleDTOListBySysUserId(Long sysUserId) {

        return null;
    }
}
