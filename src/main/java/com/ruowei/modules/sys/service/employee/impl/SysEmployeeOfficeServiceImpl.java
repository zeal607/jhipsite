package com.ruowei.modules.sys.service.employee.impl;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysEmployeeOffice;

import com.ruowei.modules.sys.mapper.SysEmployeeOfficeMapper;
import com.ruowei.modules.sys.pojo.SysEmployeeOfficeDTO;
import com.ruowei.modules.sys.repository.SysEmployeeOfficeRepository;
import com.ruowei.modules.sys.service.employee.SysEmployeeOfficeService;
import com.ruowei.modules.sys.service.office.SysOfficeQueryService;
import com.ruowei.modules.sys.service.post.SysPostQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Interface for managing {@link SysEmployeeOffice}.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysEmployeeOfficeServiceImpl
    extends CrudBaseService<SysEmployeeOffice, Long, SysEmployeeOfficeRepository>
    implements SysEmployeeOfficeService {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeOfficeServiceImpl.class);

    /**
     * service依赖
     */
    private final SysOfficeQueryService sysOfficeQueryService;
    private final SysPostQueryService sysPostQueryService;
    /**
     * mapper依赖
     */
    private final SysEmployeeOfficeMapper sysEmployeeOfficeMapper;

    public SysEmployeeOfficeServiceImpl(SysOfficeQueryService sysOfficeQueryService,
                                        SysPostQueryService sysPostQueryService,
                                        SysEmployeeOfficeMapper sysEmployeeOfficeMapper){
        this.sysOfficeQueryService = sysOfficeQueryService;
        this.sysPostQueryService = sysPostQueryService;

        this.sysEmployeeOfficeMapper = sysEmployeeOfficeMapper;
    }

    /**
     * 保存员工附属机构及岗位关系
     *
     * @param sysEmployee
     * @param sysEmployeeOfficeDTOList
     * @author 刘东奇
     * @date 2019/9/30
     */
    @Override
    public void saveEmployeeOfficePostRelationship(SysEmployee sysEmployee, List<SysEmployeeOfficeDTO> sysEmployeeOfficeDTOList) {
        if(sysEmployeeOfficeDTOList != null){
            for(SysEmployeeOfficeDTO sysEmployeeOfficeDTO:sysEmployeeOfficeDTOList){
                if(!sysOfficeQueryService.existsById(Long.valueOf(sysEmployeeOfficeDTO.getSysOfficeId()))){
                    throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("机构",sysEmployeeOfficeDTO.getSysOfficeId()));
                }else if(sysPostQueryService.existsById(Long.valueOf(sysEmployeeOfficeDTO.getSysPostId()))){
                    throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage("岗位",sysEmployeeOfficeDTO.getSysOfficeId()));
                }else{
                    SysEmployeeOffice sysEmployeeOffice = sysEmployeeOfficeMapper.assembleEntity(sysEmployeeOfficeDTO,sysEmployee);
                    this.save(sysEmployeeOffice);
                }
            }
        }
    }
}
