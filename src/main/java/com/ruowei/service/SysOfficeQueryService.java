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

import com.ruowei.domain.SysOffice;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysOfficeRepository;
import com.ruowei.service.dto.SysOfficeCriteria;
import com.ruowei.service.dto.SysOfficeDTO;
import com.ruowei.service.mapper.SysOfficeMapper;

/**
 * Service for executing complex queries for {@link SysOffice} entities in the database.
 * The main input is a {@link SysOfficeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysOfficeDTO} or a {@link Page} of {@link SysOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysOfficeQueryService extends QueryService<SysOffice> {

    private final Logger log = LoggerFactory.getLogger(SysOfficeQueryService.class);

    private final SysOfficeRepository sysOfficeRepository;

    private final SysOfficeMapper sysOfficeMapper;

    public SysOfficeQueryService(SysOfficeRepository sysOfficeRepository, SysOfficeMapper sysOfficeMapper) {
        this.sysOfficeRepository = sysOfficeRepository;
        this.sysOfficeMapper = sysOfficeMapper;
    }

    /**
     * Return a {@link List} of {@link SysOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysOfficeDTO> findByCriteria(SysOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysOffice> specification = createSpecification(criteria);
        return sysOfficeMapper.toDto(sysOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysOfficeDTO> findByCriteria(SysOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysOffice> specification = createSpecification(criteria);
        return sysOfficeRepository.findAll(specification, page)
            .map(sysOfficeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysOfficeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysOffice> specification = createSpecification(criteria);
        return sysOfficeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysOffice> createSpecification(SysOfficeCriteria criteria) {
        Specification<SysOffice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysOffice_.id));
            }
            if (criteria.getOfficeCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficeCode(), SysOffice_.officeCode));
            }
            if (criteria.getParentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCode(), SysOffice_.parentCode));
            }
            if (criteria.getParentCodes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCodes(), SysOffice_.parentCodes));
            }
            if (criteria.getTreeSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSort(), SysOffice_.treeSort));
            }
            if (criteria.getTreeSorts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSorts(), SysOffice_.treeSorts));
            }
            if (criteria.getTreeLeaf() != null) {
                specification = specification.and(buildSpecification(criteria.getTreeLeaf(), SysOffice_.treeLeaf));
            }
            if (criteria.getTreeLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeLevel(), SysOffice_.treeLevel));
            }
            if (criteria.getTreeNames() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTreeNames(), SysOffice_.treeNames));
            }
            if (criteria.getViewCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewCode(), SysOffice_.viewCode));
            }
            if (criteria.getOfficeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficeName(), SysOffice_.officeName));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), SysOffice_.fullName));
            }
            if (criteria.getOfficeType() != null) {
                specification = specification.and(buildSpecification(criteria.getOfficeType(), SysOffice_.officeType));
            }
            if (criteria.getLeader() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLeader(), SysOffice_.leader));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), SysOffice_.phone));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), SysOffice_.address));
            }
            if (criteria.getZipCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZipCode(), SysOffice_.zipCode));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SysOffice_.email));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysOffice_.remarks));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysOffice_.status));
            }
        }
        return specification;
    }
}
