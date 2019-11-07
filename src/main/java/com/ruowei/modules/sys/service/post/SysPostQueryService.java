package com.ruowei.modules.sys.service.post;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.query.simple.QueryService;
import com.ruowei.modules.sys.domain.table.*;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.repository.SysPostRepository;
import com.ruowei.modules.sys.pojo.SysPostCriteria;
import com.ruowei.modules.sys.pojo.SysPostDTO;
import com.ruowei.modules.sys.mapper.SysPostMapper;

/**
 * Service for executing complex queries for {@link SysPost} entities in the database.
 * The main input is a {@link SysPostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysPostDTO} or a {@link Page} of {@link SysPostDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysPostQueryService
    extends QueryService<
    SysPost,
    QSysPost,
    SysPostRepository> {

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
     * @param superCriteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
//    @Override
    public Specification<SysPost> createSpecification(Criteria superCriteria) {
        SysPostCriteria criteria= (SysPostCriteria) superCriteria;
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

    /**
     * 把前端传过来的Query转换成Querydsl能处理的Predicate
     * 可以根据实际需求有不同的实现
     *
     * @param criteriaArray
     * @return
     */
//    @Override
    @Deprecated
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
//    @Override
    @Deprecated
    public QueryResults<BaseView> findPageVMByCriteriaArray(Pageable pageable, Criteria... criteriaArray) {
        return null;
    }

    /**
     * 根据Id查询唯一DTO
     *
     * @param id
     * @return
     */
//    @Override
    @Deprecated
    public Optional<SysPostDTO> getDTOById(Long id) {
        return Optional.empty();
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    @Deprecated
    public JPAQuery<SysPost> getEntityJPAQuery() {
        return null;
    }

    /**
     * 组装VM视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    @Deprecated
    public JPAQuery<BaseView> getVMJPAQuery() {
        return null;
    }

    /**
     * 组装DTO视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    public JPAQuery<SysPostDTO> getDTOJPAQuery() {
        QSysPost qSysPost = QSysPost.sysPost;
        JPAQuery<SysPostDTO> jpaQuery = this.queryFactory.select(
            Projections.bean(
                SysPostDTO.class,
                qSysPost.id,
                qSysPost.postCode,
                qSysPost.postName,
                qSysPost.postType,
                qSysPost.remarks,
                qSysPost.status
            )
        ).from(qSysPost);
        return jpaQuery;
    }

    /**
     * 组装Tuple视图
     *
     * @author 刘东奇
     * @date 2019/9/26
     */
//    @Override
    @Deprecated
    public JPAQuery<Tuple> getTupleJPAQuery() {
        return null;
    }

    /**
     * 通过用户id获取员工岗位信息
     * @author 刘东奇
     * @date 2019/9/27
     * @param sysUserId
     */
    public List<SysPostDTO> getSysPostDTOListBySysUserId(Long sysUserId){
        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        QSysPost qSysPost = QSysPost.sysPost;
        QSysEmployeePost qSysEmployeePost = QSysEmployeePost.sysEmployeePost;
        QSysUser qSysUser =  QSysUser.sysUser;

        JPAQuery<SysPostDTO> sysPostDTOJPAQuery = getDTOJPAQuery();
        sysPostDTOJPAQuery.leftJoin(qSysEmployeePost).on(qSysPost.postCode.eq(qSysEmployeePost.sysPostId))
            .leftJoin(qSysEmployee).on(qSysEmployeePost.sysEmployeeId.eq(qSysEmployee.empCode))
            .leftJoin(qSysUser).on(qSysEmployee.empCode.eq(qSysUser.refCode));
        List<SysPostDTO> postDTOList=sysPostDTOJPAQuery.where(qSysUser.id.eq(sysUserId)).fetch();
        return postDTOList;
    }

}
