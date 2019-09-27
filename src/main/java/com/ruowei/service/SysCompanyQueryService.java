package com.ruowei.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.ruowei.domain.SysCompany;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysCompanyRepository;
import com.ruowei.modules.sys.pojo.SysCompanyCriteria;
import com.ruowei.service.dto.SysCompanyDTO;
import com.ruowei.service.mapper.SysCompanyMapper;

/**
 * Service for executing complex queries for {@link SysCompany} entities in the database.
 * The main input is a {@link SysCompanyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysCompanyDTO} or a {@link Page} of {@link SysCompanyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysCompanyQueryService extends QueryService<SysCompany> {

    private final Logger log = LoggerFactory.getLogger(SysCompanyQueryService.class);

    private final SysCompanyRepository sysCompanyRepository;

    private final SysCompanyMapper sysCompanyMapper;

    public SysCompanyQueryService(SysCompanyRepository sysCompanyRepository, SysCompanyMapper sysCompanyMapper) {
        this.sysCompanyRepository = sysCompanyRepository;
        this.sysCompanyMapper = sysCompanyMapper;
    }

    /**
     * Return a {@link List} of {@link SysCompanyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysCompanyDTO> findByCriteria(SysCompanyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysCompany> specification = createSpecification(criteria);
        return sysCompanyMapper.toDto(sysCompanyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysCompanyDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysCompanyDTO> findByCriteria(SysCompanyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysCompany> specification = createSpecification(criteria);
        return sysCompanyRepository.findAll(specification, page)
            .map(sysCompanyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysCompanyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysCompany> specification = createSpecification(criteria);
        return sysCompanyRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysCompany> createSpecification(SysCompanyCriteria criteria) {
        Specification<SysCompany> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysCompany_.id));
            }
            if (criteria.getCompanyCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyCode(), SysCompany_.companyCode));
            }
            if (criteria.getParentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCode(), SysCompany_.parentCode));
            }
            if (criteria.getParentCodes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCodes(), SysCompany_.parentCodes));
            }
            if (criteria.getTreeSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSort(), SysCompany_.treeSort));
            }
            if (criteria.getTreeSorts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSorts(), SysCompany_.treeSorts));
            }
            if (criteria.getTreeLeaf() != null) {
                specification = specification.and(buildSpecification(criteria.getTreeLeaf(), SysCompany_.treeLeaf));
            }
            if (criteria.getTreeLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeLevel(), SysCompany_.treeLevel));
            }
            if (criteria.getTreeNames() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTreeNames(), SysCompany_.treeNames));
            }
            if (criteria.getViewCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewCode(), SysCompany_.viewCode));
            }
            if (criteria.getCompanyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyName(), SysCompany_.companyName));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), SysCompany_.fullName));
            }
            if (criteria.getAreaCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAreaCode(), SysCompany_.areaCode));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysCompany_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysCompany_.remarks));
            }
        }
        return specification;
    }
}
