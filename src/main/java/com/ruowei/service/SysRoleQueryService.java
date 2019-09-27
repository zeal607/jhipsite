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

import com.ruowei.domain.SysRole;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysRoleRepository;
import com.ruowei.service.dto.SysRoleCriteria;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.modules.sys.mapper.SysRoleMapper;

/**
 * Service for executing complex queries for {@link SysRole} entities in the database.
 * The main input is a {@link SysRoleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysRoleDTO} or a {@link Page} of {@link SysRoleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysRoleQueryService extends QueryService<SysRole> {

    private final Logger log = LoggerFactory.getLogger(SysRoleQueryService.class);

    private final SysRoleRepository sysRoleRepository;

    private final SysRoleMapper sysRoleMapper;

    public SysRoleQueryService(SysRoleRepository sysRoleRepository, SysRoleMapper sysRoleMapper) {
        this.sysRoleRepository = sysRoleRepository;
        this.sysRoleMapper = sysRoleMapper;
    }

    /**
     * Return a {@link List} of {@link SysRoleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysRoleDTO> findByCriteria(SysRoleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysRole> specification = createSpecification(criteria);
        return sysRoleMapper.toDto(sysRoleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysRoleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysRoleDTO> findByCriteria(SysRoleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysRole> specification = createSpecification(criteria);
        return sysRoleRepository.findAll(specification, page)
            .map(sysRoleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysRoleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysRole> specification = createSpecification(criteria);
        return sysRoleRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysRole> createSpecification(SysRoleCriteria criteria) {
        Specification<SysRole> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysRole_.id));
            }
            if (criteria.getRoleCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoleCode(), SysRole_.roleCode));
            }
            if (criteria.getRoleName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoleName(), SysRole_.roleName));
            }
            if (criteria.getRoleType() != null) {
                specification = specification.and(buildSpecification(criteria.getRoleType(), SysRole_.roleType));
            }
            if (criteria.getRoleSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoleSort(), SysRole_.roleSort));
            }
            if (criteria.getIsSys() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSys(), SysRole_.isSys));
            }
            if (criteria.getDataScope() != null) {
                specification = specification.and(buildSpecification(criteria.getDataScope(), SysRole_.dataScope));
            }
            if (criteria.getBizScope() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBizScope(), SysRole_.bizScope));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysRole_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysRole_.remarks));
            }
        }
        return specification;
    }
}
