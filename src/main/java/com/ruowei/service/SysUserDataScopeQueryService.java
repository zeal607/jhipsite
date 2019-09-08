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

import com.ruowei.domain.SysUserDataScope;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysUserDataScopeRepository;
import com.ruowei.service.dto.SysUserDataScopeCriteria;
import com.ruowei.service.dto.SysUserDataScopeDTO;
import com.ruowei.service.mapper.SysUserDataScopeMapper;

/**
 * Service for executing complex queries for {@link SysUserDataScope} entities in the database.
 * The main input is a {@link SysUserDataScopeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysUserDataScopeDTO} or a {@link Page} of {@link SysUserDataScopeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysUserDataScopeQueryService extends QueryService<SysUserDataScope> {

    private final Logger log = LoggerFactory.getLogger(SysUserDataScopeQueryService.class);

    private final SysUserDataScopeRepository sysUserDataScopeRepository;

    private final SysUserDataScopeMapper sysUserDataScopeMapper;

    public SysUserDataScopeQueryService(SysUserDataScopeRepository sysUserDataScopeRepository, SysUserDataScopeMapper sysUserDataScopeMapper) {
        this.sysUserDataScopeRepository = sysUserDataScopeRepository;
        this.sysUserDataScopeMapper = sysUserDataScopeMapper;
    }

    /**
     * Return a {@link List} of {@link SysUserDataScopeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysUserDataScopeDTO> findByCriteria(SysUserDataScopeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysUserDataScope> specification = createSpecification(criteria);
        return sysUserDataScopeMapper.toDto(sysUserDataScopeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysUserDataScopeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysUserDataScopeDTO> findByCriteria(SysUserDataScopeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysUserDataScope> specification = createSpecification(criteria);
        return sysUserDataScopeRepository.findAll(specification, page)
            .map(sysUserDataScopeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysUserDataScopeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysUserDataScope> specification = createSpecification(criteria);
        return sysUserDataScopeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysUserDataScope> createSpecification(SysUserDataScopeCriteria criteria) {
        Specification<SysUserDataScope> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysUserDataScope_.id));
            }
            if (criteria.getSysUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysUserId(), SysUserDataScope_.sysUserId));
            }
            if (criteria.getCtrlType() != null) {
                specification = specification.and(buildSpecification(criteria.getCtrlType(), SysUserDataScope_.ctrlType));
            }
            if (criteria.getCtrlData() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCtrlData(), SysUserDataScope_.ctrlData));
            }
            if (criteria.getCtrlPermi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCtrlPermi(), SysUserDataScope_.ctrlPermi));
            }
        }
        return specification;
    }
}
