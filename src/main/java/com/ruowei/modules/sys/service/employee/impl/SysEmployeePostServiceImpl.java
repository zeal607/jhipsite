package com.ruowei.modules.sys.service.employee.impl;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysEmployeePost;
import com.ruowei.modules.sys.mapper.SysEmployeePostMapper;
import com.ruowei.modules.sys.mapper.SysUserEmployeeMapper;
import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.repository.SysEmployeePostRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;

import com.ruowei.modules.sys.service.employee.SysEmployeePostService;
import com.ruowei.modules.sys.service.post.SysPostQueryService;
import com.ruowei.modules.sys.service.user.impl.SysUserServiceImpl;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SysEmployeePost}.
 * @author 刘东奇
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysEmployeePostServiceImpl
    extends CrudBaseService<SysEmployeePost, Long, SysEmployeePostRepository>
    implements SysEmployeePostService {

    private final Logger log = LoggerFactory.getLogger(SysEmployeePostServiceImpl.class);

    /**
     * service依赖
     */
    private final SysPostQueryService sysPostQueryService;
    /**
     * mapper依赖
     */
    private final SysEmployeePostMapper sysEmployeePostMapper;

    public SysEmployeePostServiceImpl(SysPostQueryService sysPostQueryService,
                                      SysEmployeePostMapper sysEmployeePostMapper){
        this.sysPostQueryService = sysPostQueryService;

        this.sysEmployeePostMapper = sysEmployeePostMapper;
    }

    /**
     * 保存员工岗位关系
     *
     * @param sysEmployee
     * @param sysPostDTOList
     * @author 刘东奇
     * @date 2019/9/30
     */
    @Override
    public void saveEmployeePostRelationship(SysEmployee sysEmployee, List<SysPostDTO> sysPostDTOList) {
        if(sysPostDTOList != null){
            for(SysPostDTO sysPostDTO:sysPostDTOList){
                // 判断岗位是否存在
                if(sysPostQueryService.existsById(sysPostDTO.getId())){
                    SysEmployeePost sysEmployeePost = sysEmployeePostMapper.assembleEntity(sysPostDTO,sysEmployee);
                    this.save(sysEmployeePost);
                }else{
                    throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("岗位",sysPostDTO.getId().toString()));
                }
            }
        }
    }
}
