package com.ruowei.modules.sys.service.employee;

import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysEmployeeOffice;

import com.ruowei.modules.sys.repository.SysEmployeeOfficeRepository;
import io.github.jhipster.service.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Interface for managing {@link SysEmployeeOffice}.
 */
@Service
@Transactional
public class SysEmployeeOfficeService
    extends CrudBaseService<SysEmployeeOffice, Long, BaseView, BaseDTO, SysEmployeeOfficeRepository> {

}
