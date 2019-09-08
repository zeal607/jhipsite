package com.ruowei.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.ruowei.domain.SysEmployeeOffice;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysEmployeeOfficeRepository;
import com.ruowei.service.dto.SysEmployeeOfficeCriteria;
import com.ruowei.service.dto.SysEmployeeOfficeDTO;
import com.ruowei.service.mapper.SysEmployeeOfficeMapper;

/**
 * Service for executing complex queries for {@link SysEmployeeOffice} entities in the database.
 * The main input is a {@link SysEmployeeOfficeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysEmployeeOfficeDTO} or a {@link Page} of {@link SysEmployeeOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysEmployeeOfficeQueryService extends QueryService<SysEmployeeOffice> {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeOfficeQueryService.class);

    private final SysEmployeeOfficeRepository sysEmployeeOfficeRepository;

    private final SysEmployeeOfficeMapper sysEmployeeOfficeMapper;

    public SysEmployeeOfficeQueryService(SysEmployeeOfficeRepository sysEmployeeOfficeRepository, SysEmployeeOfficeMapper sysEmployeeOfficeMapper) {
        this.sysEmployeeOfficeRepository = sysEmployeeOfficeRepository;
        this.sysEmployeeOfficeMapper = sysEmployeeOfficeMapper;
    }

    /**
     * Return a {@link List} of {@link SysEmployeeOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysEmployeeOfficeDTO> findByCriteria(SysEmployeeOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysEmployeeOffice> specification = createSpecification(criteria);
        return sysEmployeeOfficeMapper.toDto(sysEmployeeOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysEmployeeOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysEmployeeOfficeDTO> findByCriteria(SysEmployeeOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysEmployeeOffice> specification = createSpecification(criteria);
        return sysEmployeeOfficeRepository.findAll(specification, page)
            .map(sysEmployeeOfficeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysEmployeeOfficeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysEmployeeOffice> specification = createSpecification(criteria);
        return sysEmployeeOfficeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysEmployeeOffice> createSpecification(SysEmployeeOfficeCriteria criteria) {
        Specification<SysEmployeeOffice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysEmployeeOffice_.id));
            }
            if (criteria.getSysEmployeeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysEmployeeId(), SysEmployeeOffice_.sysEmployeeId));
            }
            if (criteria.getSysOfficeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysOfficeId(), SysEmployeeOffice_.sysOfficeId));
            }
            if (criteria.getSysPostId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysPostId(), SysEmployeeOffice_.sysPostId));
            }
        }
        return specification;
    }
}
