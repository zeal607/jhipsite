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

import com.ruowei.domain.SysCompanyOffice;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysCompanyOfficeRepository;
import com.ruowei.service.dto.SysCompanyOfficeCriteria;
import com.ruowei.service.dto.SysCompanyOfficeDTO;
import com.ruowei.service.mapper.SysCompanyOfficeMapper;

/**
 * Service for executing complex queries for {@link SysCompanyOffice} entities in the database.
 * The main input is a {@link SysCompanyOfficeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysCompanyOfficeDTO} or a {@link Page} of {@link SysCompanyOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysCompanyOfficeQueryService extends QueryService<SysCompanyOffice> {

    private final Logger log = LoggerFactory.getLogger(SysCompanyOfficeQueryService.class);

    private final SysCompanyOfficeRepository sysCompanyOfficeRepository;

    private final SysCompanyOfficeMapper sysCompanyOfficeMapper;

    public SysCompanyOfficeQueryService(SysCompanyOfficeRepository sysCompanyOfficeRepository, SysCompanyOfficeMapper sysCompanyOfficeMapper) {
        this.sysCompanyOfficeRepository = sysCompanyOfficeRepository;
        this.sysCompanyOfficeMapper = sysCompanyOfficeMapper;
    }

    /**
     * Return a {@link List} of {@link SysCompanyOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysCompanyOfficeDTO> findByCriteria(SysCompanyOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysCompanyOffice> specification = createSpecification(criteria);
        return sysCompanyOfficeMapper.toDto(sysCompanyOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysCompanyOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysCompanyOfficeDTO> findByCriteria(SysCompanyOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysCompanyOffice> specification = createSpecification(criteria);
        return sysCompanyOfficeRepository.findAll(specification, page)
            .map(sysCompanyOfficeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysCompanyOfficeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysCompanyOffice> specification = createSpecification(criteria);
        return sysCompanyOfficeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysCompanyOffice> createSpecification(SysCompanyOfficeCriteria criteria) {
        Specification<SysCompanyOffice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysCompanyOffice_.id));
            }
            if (criteria.getSysCompanyId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysCompanyId(), SysCompanyOffice_.sysCompanyId));
            }
            if (criteria.getSysOfficeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysOfficeId(), SysCompanyOffice_.sysOfficeId));
            }
        }
        return specification;
    }
}
