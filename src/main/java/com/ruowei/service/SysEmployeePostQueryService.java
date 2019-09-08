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

import com.ruowei.domain.SysEmployeePost;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.repository.SysEmployeePostRepository;
import com.ruowei.service.dto.SysEmployeePostCriteria;
import com.ruowei.service.dto.SysEmployeePostDTO;
import com.ruowei.service.mapper.SysEmployeePostMapper;

/**
 * Service for executing complex queries for {@link SysEmployeePost} entities in the database.
 * The main input is a {@link SysEmployeePostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysEmployeePostDTO} or a {@link Page} of {@link SysEmployeePostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysEmployeePostQueryService extends QueryService<SysEmployeePost> {

    private final Logger log = LoggerFactory.getLogger(SysEmployeePostQueryService.class);

    private final SysEmployeePostRepository sysEmployeePostRepository;

    private final SysEmployeePostMapper sysEmployeePostMapper;

    public SysEmployeePostQueryService(SysEmployeePostRepository sysEmployeePostRepository, SysEmployeePostMapper sysEmployeePostMapper) {
        this.sysEmployeePostRepository = sysEmployeePostRepository;
        this.sysEmployeePostMapper = sysEmployeePostMapper;
    }

    /**
     * Return a {@link List} of {@link SysEmployeePostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysEmployeePostDTO> findByCriteria(SysEmployeePostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysEmployeePost> specification = createSpecification(criteria);
        return sysEmployeePostMapper.toDto(sysEmployeePostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysEmployeePostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysEmployeePostDTO> findByCriteria(SysEmployeePostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysEmployeePost> specification = createSpecification(criteria);
        return sysEmployeePostRepository.findAll(specification, page)
            .map(sysEmployeePostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysEmployeePostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysEmployeePost> specification = createSpecification(criteria);
        return sysEmployeePostRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysEmployeePost> createSpecification(SysEmployeePostCriteria criteria) {
        Specification<SysEmployeePost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysEmployeePost_.id));
            }
            if (criteria.getSysEmployeeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysEmployeeId(), SysEmployeePost_.sysEmployeeId));
            }
            if (criteria.getSysPostId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysPostId(), SysEmployeePost_.sysPostId));
            }
        }
        return specification;
    }
}
