package com.ruowei.modules.sys.service.role.impl;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.query.simple.QueryService;
import com.ruowei.modules.sys.domain.table.QSysRoleMenu;
import com.ruowei.modules.sys.domain.table.SysRoleMenu;
import com.ruowei.modules.sys.domain.table.SysRoleMenu_;
import com.ruowei.modules.sys.repository.SysRoleMenuRepository;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruowei.service.dto.SysRoleMenuCriteria;
import com.ruowei.modules.sys.pojo.SysRoleMenuDTO;
import com.ruowei.service.mapper.SysRoleMenuMapper;

/**
 * Service for executing complex queries for {@link SysRoleMenu} entities in the database.
 * The main input is a {@link SysRoleMenuCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysRoleMenuDTO} or a {@link Page} of {@link SysRoleMenuDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysRoleMenuQueryService
    extends QueryService<
    SysRoleMenu,
    QSysRoleMenu,
    SysRoleMenuRepository> {

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

    /**
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     *
     * @param criteria
     * @return
     */
//    @Override
    public Specification<SysRoleMenu> createSpecification(Criteria criteria) {
        return null;
    }

    /**
     * 把前端传过来的Query转换成Querydsl能处理的Predicate
     * 可以根据实际需求有不同的实现
     *
     * @param criteriaArray
     * @return
     */
//    @Override
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
    public Optional<SysRoleMenuDTO> getDTOById(Long id) {
        return Optional.empty();
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    public JPAQuery<SysRoleMenu> getEntityJPAQuery() {
        return null;
    }

    /**
     * 组装VM视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
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
    public JPAQuery<SysRoleMenuDTO> getDTOJPAQuery() {
        return null;
    }

    /**
     * 组装Tuple视图
     *
     * @author 刘东奇
     * @date 2019/9/26
     */
//    @Override
    public JPAQuery<Tuple> getTupleJPAQuery() {
        return null;
    }
}
