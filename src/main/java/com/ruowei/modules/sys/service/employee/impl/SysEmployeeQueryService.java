package com.ruowei.modules.sys.service.employee.impl;

import java.util.List;

import com.ruowei.modules.sys.domain.SysEmployee_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.repository.SysEmployeeRepository;
import com.ruowei.modules.sys.pojo.SysEmployeeCriteria;
import com.ruowei.modules.sys.pojo.SysEmployeeDTO;
import com.ruowei.service.mapper.SysEmployeeMapper;

/**
 * Service for executing complex queries for {@link SysEmployee} entities in the database.
 * The main input is a {@link SysEmployeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysEmployeeDTO} or a {@link Page} of {@link SysEmployeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysEmployeeQueryService extends QueryService<SysEmployee> {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeQueryService.class);

    private final SysEmployeeRepository sysEmployeeRepository;

    private final SysEmployeeMapper sysEmployeeMapper;

    public SysEmployeeQueryService(SysEmployeeRepository sysEmployeeRepository, SysEmployeeMapper sysEmployeeMapper) {
        this.sysEmployeeRepository = sysEmployeeRepository;
        this.sysEmployeeMapper = sysEmployeeMapper;
    }

    /**
     * Return a {@link List} of {@link SysEmployeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysEmployeeDTO> findByCriteria(SysEmployeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysEmployee> specification = createSpecification(criteria);
        return sysEmployeeMapper.toDto(sysEmployeeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysEmployeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysEmployeeDTO> findByCriteria(SysEmployeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysEmployee> specification = createSpecification(criteria);
        return sysEmployeeRepository.findAll(specification, page)
            .map(sysEmployeeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysEmployeeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysEmployee> specification = createSpecification(criteria);
        return sysEmployeeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysEmployee> createSpecification(SysEmployeeCriteria criteria) {
        Specification<SysEmployee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysEmployee_.id));
            }
            if (criteria.getEmpCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpCode(), SysEmployee_.empCode));
            }
            if (criteria.getEmpName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpName(), SysEmployee_.empName));
            }
            if (criteria.getEmpNameEn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpNameEn(), SysEmployee_.empNameEn));
            }
            if (criteria.getSysOfficeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysOfficeId(), SysEmployee_.sysOfficeId));
            }
            if (criteria.getOfficeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficeName(), SysEmployee_.officeName));
            }
            if (criteria.getSysCompanyId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysCompanyId(), SysEmployee_.sysCompanyId));
            }
            if (criteria.getCompanyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyName(), SysEmployee_.companyName));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysEmployee_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysEmployee_.remarks));
            }
        }
        return specification;
    }
}
