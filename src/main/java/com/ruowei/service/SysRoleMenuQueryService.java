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

import com.ruowei.domain.SysRoleMenu;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysRoleMenuRepository;
import com.ruowei.service.dto.SysRoleMenuCriteria;
import com.ruowei.service.dto.SysRoleMenuDTO;
import com.ruowei.service.mapper.SysRoleMenuMapper;

/**
 * Service for executing complex queries for {@link SysRoleMenu} entities in the database.
 * The main input is a {@link SysRoleMenuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysRoleMenuDTO} or a {@link Page} of {@link SysRoleMenuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysRoleMenuQueryService extends QueryService<SysRoleMenu> {

    private final Logger log = LoggerFactory.getLogger(SysRoleMenuQueryService.class);

    private final SysRoleMenuRepository sysRoleMenuRepository;

    private final SysRoleMenuMapper sysRoleMenuMapper;

    public SysRoleMenuQueryService(SysRoleMenuRepository sysRoleMenuRepository, SysRoleMenuMapper sysRoleMenuMapper) {
        this.sysRoleMenuRepository = sysRoleMenuRepository;
        this.sysRoleMenuMapper = sysRoleMenuMapper;
    }

    /**
     * Return a {@link List} of {@link SysRoleMenuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysRoleMenuDTO> findByCriteria(SysRoleMenuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysRoleMenu> specification = createSpecification(criteria);
        return sysRoleMenuMapper.toDto(sysRoleMenuRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysRoleMenuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysRoleMenuDTO> findByCriteria(SysRoleMenuCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysRoleMenu> specification = createSpecification(criteria);
        return sysRoleMenuRepository.findAll(specification, page)
            .map(sysRoleMenuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysRoleMenuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysRoleMenu> specification = createSpecification(criteria);
        return sysRoleMenuRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysRoleMenu> createSpecification(SysRoleMenuCriteria criteria) {
        Specification<SysRoleMenu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysRoleMenu_.id));
            }
            if (criteria.getSysRoleId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysRoleId(), SysRoleMenu_.sysRoleId));
            }
            if (criteria.getSysMenuId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysMenuId(), SysRoleMenu_.sysMenuId));
            }
        }
        return specification;
    }
}
