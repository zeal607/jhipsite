package com.ruowei.modules.sys.service.office;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysOffice;

import com.ruowei.modules.sys.repository.SysOfficeRepository;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysOfficeService
    extends CrudBaseService<SysOffice, Long, BaseView, BaseDTO, SysOfficeRepository> {

    private final Logger log = LoggerFactory.getLogger(SysOfficeService.class);

    public SysOfficeService(){

    }
}
