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

import com.ruowei.domain.SysPost;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysPostRepository;
import com.ruowei.service.dto.SysPostCriteria;
import com.ruowei.service.dto.SysPostDTO;
import com.ruowei.service.mapper.SysPostMapper;

/**
 * Service for executing complex queries for {@link SysPost} entities in the database.
 * The main input is a {@link SysPostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysPostDTO} or a {@link Page} of {@link SysPostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysPostQueryService extends QueryService<SysPost> {

    private final Logger log = LoggerFactory.getLogger(SysPostQueryService.class);

    private final SysPostRepository sysPostRepository;

    private final SysPostMapper sysPostMapper;

    public SysPostQueryService(SysPostRepository sysPostRepository, SysPostMapper sysPostMapper) {
        this.sysPostRepository = sysPostRepository;
        this.sysPostMapper = sysPostMapper;
    }

    /**
     * Return a {@link List} of {@link SysPostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysPostDTO> findByCriteria(SysPostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysPost> specification = createSpecification(criteria);
        return sysPostMapper.toDto(sysPostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysPostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysPostDTO> findByCriteria(SysPostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysPost> specification = createSpecification(criteria);
        return sysPostRepository.findAll(specification, page)
            .map(sysPostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysPostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysPost> specification = createSpecification(criteria);
        return sysPostRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysPost> createSpecification(SysPostCriteria criteria) {
        Specification<SysPost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysPost_.id));
            }
            if (criteria.getPostCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostCode(), SysPost_.postCode));
            }
            if (criteria.getPostName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostName(), SysPost_.postName));
            }
            if (criteria.getPostType() != null) {
                specification = specification.and(buildSpecification(criteria.getPostType(), SysPost_.postType));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysPost_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysPost_.remarks));
            }
        }
        return specification;
    }
}
