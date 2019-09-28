package com.ruowei.modules.sys.service.user.impl;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.QueryBaseService;
import com.ruowei.modules.sys.domain.QSysUserRole;
import com.ruowei.modules.sys.domain.SysUserRole_;
import com.ruowei.modules.sys.mapper.SysRoleMapper;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.domain.SysUserRole;
import com.ruowei.domain.*; // for static metamodels
import com.ruowei.modules.sys.repository.SysUserRoleRepository;
import com.ruowei.modules.sys.pojo.SysUserRoleCriteria;
import com.ruowei.modules.sys.pojo.SysUserRoleDTO;
import com.ruowei.modules.sys.mapper.SysUserRoleMapper;

/**
 * Service for executing complex queries for {@link SysUserRole} entities in the database.
 * The main input is a {@link SysUserRoleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysUserRoleDTO} or a {@link Page} of {@link SysUserRoleDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysUserRoleQueryService
    extends QueryBaseService<SysUserRole,Long,SysUserRoleDTO, BaseView,SysUserRoleRepository> {

    private final Logger log = LoggerFactory.getLogger(SysUserRoleQueryService.class);

    private final SysUserRoleRepository sysUserRoleRepository;

    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMapper sysRoleMapper;

    public SysUserRoleQueryService(SysUserRoleRepository sysUserRoleRepository,
                                   SysUserRoleMapper sysUserRoleMapper,
                                   SysRoleMapper sysRoleMapper) {
        this.sysUserRoleRepository = sysUserRoleRepository;
        this.sysUserRoleMapper = sysUserRoleMapper;
        this.sysRoleMapper = sysRoleMapper;
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
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     *
     * @param superCriteria
     * @return
     */
    @Override
    public Specification<SysUserRole> createSpecification(Criteria superCriteria) {
        SysUserRoleCriteria criteria= (SysUserRoleCriteria) superCriteria;
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
    @Override
    @Deprecated
    public Optional<SysUserRoleDTO> getDTOById(Long id) {
        return Optional.empty();
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    @Deprecated
    public JPAQuery<SysUserRole> getEntityJPAQuery() {
        return null;
    }

    /**
     * 组装VM视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
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
    @Override
    @Deprecated
    public JPAQuery<SysUserRoleDTO> getDTOJPAQuery() {
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
        QSysUserRole qSysUserRole =  QSysUserRole.sysUserRole;
        QSysRole qSysRole =  QSysRole.sysRole;
        JPAQuery<Tuple> jpaQuery = this.queryFactory.select(
            qSysUserRole,qSysRole
        ).from(qSysUserRole)
            .leftJoin(qSysRole).on(qSysUserRole.sysRoleId.eq(qSysRole.roleCode));
        return jpaQuery;
    }

}
