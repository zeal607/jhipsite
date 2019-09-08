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

import com.ruowei.domain.SysRoleDataScope;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysRoleDataScopeRepository;
import com.ruowei.service.dto.SysRoleDataScopeCriteria;
import com.ruowei.service.dto.SysRoleDataScopeDTO;
import com.ruowei.service.mapper.SysRoleDataScopeMapper;

/**
 * Service for executing complex queries for {@link SysRoleDataScope} entities in the database.
 * The main input is a {@link SysRoleDataScopeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysRoleDataScopeDTO} or a {@link Page} of {@link SysRoleDataScopeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysRoleDataScopeQueryService extends QueryService<SysRoleDataScope> {

    private final Logger log = LoggerFactory.getLogger(SysRoleDataScopeQueryService.class);

    private final SysRoleDataScopeRepository sysRoleDataScopeRepository;

    private final SysRoleDataScopeMapper sysRoleDataScopeMapper;

    public SysRoleDataScopeQueryService(SysRoleDataScopeRepository sysRoleDataScopeRepository, SysRoleDataScopeMapper sysRoleDataScopeMapper) {
        this.sysRoleDataScopeRepository = sysRoleDataScopeRepository;
        this.sysRoleDataScopeMapper = sysRoleDataScopeMapper;
    }

    /**
     * Return a {@link List} of {@link SysRoleDataScopeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysRoleDataScopeDTO> findByCriteria(SysRoleDataScopeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysRoleDataScope> specification = createSpecification(criteria);
        return sysRoleDataScopeMapper.toDto(sysRoleDataScopeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysRoleDataScopeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysRoleDataScopeDTO> findByCriteria(SysRoleDataScopeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysRoleDataScope> specification = createSpecification(criteria);
        return sysRoleDataScopeRepository.findAll(specification, page)
            .map(sysRoleDataScopeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysRoleDataScopeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysRoleDataScope> specification = createSpecification(criteria);
        return sysRoleDataScopeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysRoleDataScope> createSpecification(SysRoleDataScopeCriteria criteria) {
        Specification<SysRoleDataScope> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysRoleDataScope_.id));
            }
            if (criteria.getSysRoleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysRoleId(), SysRoleDataScope_.sysRoleId));
            }
            if (criteria.getCtrlType() != null) {
                specification = specification.and(buildSpecification(criteria.getCtrlType(), SysRoleDataScope_.ctrlType));
            }
            if (criteria.getCtrlData() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCtrlData(), SysRoleDataScope_.ctrlData));
            }
            if (criteria.getCtrlPermi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCtrlPermi(), SysRoleDataScope_.ctrlPermi));
            }
        }
        return specification;
    }
}
