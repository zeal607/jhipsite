package com.ruowei.modules.sys.service.employee;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.QueryBaseService;
import com.ruowei.modules.sys.domain.SysEmployeePost_;
import com.ruowei.modules.sys.mapper.SysEmployeePostMapper;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.domain.SysEmployeePost;
import com.ruowei.modules.sys.repository.SysEmployeePostRepository;
import com.ruowei.modules.sys.pojo.SysEmployeePostCriteria;
import com.ruowei.modules.sys.pojo.SysEmployeePostDTO;

/**
 * Service for executing complex queries for {@link SysEmployeePost} entities in the database.
 * The main input is a {@link SysEmployeePostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysEmployeePostDTO} or a {@link Page} of {@link SysEmployeePostDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysEmployeePostQueryService
    extends QueryBaseService<SysEmployeePost,Long,SysEmployeePostDTO, BaseView,SysEmployeePostRepository> {

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

    /**
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     *
     * @param criteria
     * @return
     */
    @Override
    public Specification<SysEmployeePost> createSpecification(Criteria criteria) {
        return null;
    }

    /**
     * 把前端传过来的Query转换成Querydsl能处理的Predicate
     * 可以根据实际需求有不同的实现
     *
     * @param criteriaArray
     * @return
     */
    @Override
    public BooleanBuilder createBooleanBuilder(Criteria... criteriaArray) {
        return null;
    }

    /**
     * 通过CriteriaArray查询分页视图
     *
     * @param pageable
     * @param criteriaArray
     * @author 刘东奇
     */
    @Override
    public QueryResults<BaseView> findPageVMByCriteriaArray(Pageable pageable, Criteria... criteriaArray) {
        return null;
    }

    /**
     * 根据Id查询唯一DTO
     *
     * @param id
     * @return
     */
    @Override
    public Optional<SysEmployeePostDTO> getDTOById(Long id) {
        return Optional.empty();
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    public JPAQuery<SysEmployeePost> getEntityJPAQuery() {
        return null;
    }

    /**
     * 组装VM视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    public JPAQuery<BaseView> getVMJPAQuery() {
        return null;
    }

    /**
     * 组装DTO视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    public JPAQuery<SysEmployeePostDTO> getDTOJPAQuery() {
        return null;
    }

    /**
     * 组装Tuple视图
     *
     * @author 刘东奇
     * @date 2019/9/26
     */
    @Override
    public JPAQuery<Tuple> getTupleJPAQuery() {
        return null;
    }

}
