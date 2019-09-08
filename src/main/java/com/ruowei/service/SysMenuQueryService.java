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

import com.ruowei.domain.SysMenu;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysMenuRepository;
import com.ruowei.service.dto.SysMenuCriteria;
import com.ruowei.service.dto.SysMenuDTO;
import com.ruowei.service.mapper.SysMenuMapper;

/**
 * Service for executing complex queries for {@link SysMenu} entities in the database.
 * The main input is a {@link SysMenuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysMenuDTO} or a {@link Page} of {@link SysMenuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysMenuQueryService extends QueryService<SysMenu> {

    private final Logger log = LoggerFactory.getLogger(SysMenuQueryService.class);

    private final SysMenuRepository sysMenuRepository;

    private final SysMenuMapper sysMenuMapper;

    public SysMenuQueryService(SysMenuRepository sysMenuRepository, SysMenuMapper sysMenuMapper) {
        this.sysMenuRepository = sysMenuRepository;
        this.sysMenuMapper = sysMenuMapper;
    }

    /**
     * Return a {@link List} of {@link SysMenuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysMenuDTO> findByCriteria(SysMenuCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysMenu> specification = createSpecification(criteria);
        return sysMenuMapper.toDto(sysMenuRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysMenuDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysMenuDTO> findByCriteria(SysMenuCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysMenu> specification = createSpecification(criteria);
        return sysMenuRepository.findAll(specification, page)
            .map(sysMenuMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysMenuCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysMenu> specification = createSpecification(criteria);
        return sysMenuRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysMenu> createSpecification(SysMenuCriteria criteria) {
        Specification<SysMenu> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysMenu_.id));
            }
            if (criteria.getMenuCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenuCode(), SysMenu_.menuCode));
            }
            if (criteria.getParentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCode(), SysMenu_.parentCode));
            }
            if (criteria.getParentCodes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCodes(), SysMenu_.parentCodes));
            }
            if (criteria.getTreeSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSort(), SysMenu_.treeSort));
            }
            if (criteria.getTreeSorts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSorts(), SysMenu_.treeSorts));
            }
            if (criteria.getTreeLeaf() != null) {
                specification = specification.and(buildSpecification(criteria.getTreeLeaf(), SysMenu_.treeLeaf));
            }
            if (criteria.getTreeLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeLevel(), SysMenu_.treeLevel));
            }
            if (criteria.getTreeNames() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTreeNames(), SysMenu_.treeNames));
            }
            if (criteria.getMenuName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenuName(), SysMenu_.menuName));
            }
            if (criteria.getMenuType() != null) {
                specification = specification.and(buildSpecification(criteria.getMenuType(), SysMenu_.menuType));
            }
            if (criteria.getMenuHref() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenuHref(), SysMenu_.menuHref));
            }
            if (criteria.getMenuIcon() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenuIcon(), SysMenu_.menuIcon));
            }
            if (criteria.getMenuTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenuTitle(), SysMenu_.menuTitle));
            }
            if (criteria.getPermission() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPermission(), SysMenu_.permission));
            }
            if (criteria.getMenuSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMenuSort(), SysMenu_.menuSort));
            }
            if (criteria.getIsShow() != null) {
                specification = specification.and(buildSpecification(criteria.getIsShow(), SysMenu_.isShow));
            }
            if (criteria.getSysCode() != null) {
                specification = specification.and(buildSpecification(criteria.getSysCode(), SysMenu_.sysCode));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysMenu_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysMenu_.remarks));
            }
        }
        return specification;
    }
}
