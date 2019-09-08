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

import com.ruowei.domain.SysUserRole;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysUserRoleRepository;
import com.ruowei.service.dto.SysUserRoleCriteria;
import com.ruowei.service.dto.SysUserRoleDTO;
import com.ruowei.service.mapper.SysUserRoleMapper;

/**
 * Service for executing complex queries for {@link SysUserRole} entities in the database.
 * The main input is a {@link SysUserRoleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysUserRoleDTO} or a {@link Page} of {@link SysUserRoleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysUserRoleQueryService extends QueryService<SysUserRole> {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleQueryService.class);

    private final SysUserRoleRepository sysUserRoleRepository;

    private final SysUserRoleMapper sysUserRoleMapper;

    public SysUserRoleQueryService(SysUserRoleRepository sysUserRoleRepository, SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * Return a {@link List} of {@link SysUserRoleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysUserRoleDTO> findByCriteria(SysUserRoleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysUserRole> specification = createSpecification(criteria);
        return sysUserRoleMapper.toDto(sysUserRoleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysUserRoleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysUserRoleDTO> findByCriteria(SysUserRoleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysUserRole> specification = createSpecification(criteria);
        return sysUserRoleRepository.findAll(specification, page)
            .map(sysUserRoleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysUserRoleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysUserRole> specification = createSpecification(criteria);
        return sysUserRoleRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysUserRole> createSpecification(SysUserRoleCriteria criteria) {
        Specification<SysUserRole> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysUserRole_.id));
            }
            if (criteria.getSysUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysUserId(), SysUserRole_.sysUserId));
            }
            if (criteria.getSysRoleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysRoleId(), SysUserRole_.sysRoleId));
            }
        }
        return specification;
    }
}
