package com.ruowei.modules.sys.service.employee;

import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysEmployeePost;
import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.repository.SysEmployeePostRepository;
import com.ruowei.modules.sys.repository.SysUserRepository;

import io.github.jhipster.service.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Interface for managing {@link SysEmployeePost}.
 */
@Service
@Transactional
public class SysEmployeePostService
    extends CrudBaseService<SysEmployeePost, Long, BaseView, SysEmployeePostDTO, SysEmployeePostRepository> {

}
