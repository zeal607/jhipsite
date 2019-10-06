package com.ruowei.modules.sys.service.office.impl;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataInvalidException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysOffice;

import com.ruowei.modules.sys.mapper.SysOfficeMapper;
import com.ruowei.modules.sys.mapper.SysUserRoleMapper;
import com.ruowei.modules.sys.pojo.SysOfficeCriteria;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysOfficeTreeVM;
import com.ruowei.modules.sys.repository.SysOfficeRepository;
import com.ruowei.modules.sys.service.office.SysOfficeQueryService;
import com.ruowei.modules.sys.service.office.SysOfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysOfficeServiceImpl
    extends CrudBaseService<SysOffice, Long, SysOfficeRepository>
    implements SysOfficeService {

    private final Logger log = LoggerFactory.getLogger(SysOfficeServiceImpl.class);

    /**
     * service依赖
     */
    private final SysOfficeQueryService sysOfficeQueryService;
    /**
     * mapper依赖
     */
    private final SysOfficeMapper sysOfficeMapper;

    public SysOfficeServiceImpl(SysOfficeQueryService sysOfficeQueryService,
                                SysOfficeMapper sysOfficeMapper){
        this.sysOfficeQueryService = sysOfficeQueryService;

        this.sysOfficeMapper = sysOfficeMapper;
    }

    /**
     * 获取机构树
     *
     * @return TreeDTO
     * @author 刘东奇
     * @date 2019/9/30
     */
    @Override
    public TreeDTO getOfficeTree() {
//        return sysOfficeQueryService.getOfficeTreeByRecursiveQuery();
        return sysOfficeQueryService.getOfficeTreeByOneQuery();
    }

    /**
     * 通过条件查询机构树信息
     *
     * @param sysOfficeCriteria
     * @author 刘东奇
     * @date 2019/10/6
     */
    @Override
    public SysOfficeTreeVM findSysOfficeTreeVMByCriteria(SysOfficeCriteria sysOfficeCriteria) {
        return null;
    }

    /**
     * 通过主键获取机构信息
     *
     * @param id
     * @author 刘东奇
     * @date 2019/10/6
     */
    @Override
    public Optional<SysOfficeDTO> getSysOfficeById(Long id) {
        return Optional.empty();
    }

    /**
     * 创建机构
     *
     * @param sysOfficeDTO
     * @author 刘东奇
     * @date 2019/10/6
     */
    @Override
    public SysOfficeDTO createSysOffice(SysOfficeDTO sysOfficeDTO) {
        return null;
    }


}
