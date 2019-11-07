package com.ruowei.modules.sys.service.user;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.query.simple.QueryService;
import com.ruowei.modules.sys.domain.table.QSysUserDataScope;
import com.ruowei.modules.sys.domain.table.SysUserDataScope;
import com.ruowei.modules.sys.domain.table.SysUserDataScope_;
import com.ruowei.modules.sys.repository.SysUserDataScopeRepository;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruowei.modules.sys.pojo.SysUserDataScopeCriteria;
import com.ruowei.modules.sys.pojo.SysUserDataScopeDTO;
import com.ruowei.service.mapper.SysUserDataScopeMapper;

/**
 * Service for executing complex queries for {@link SysUserDataScope} entities in the database.
 * The main input is a {@link SysUserDataScopeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysUserDataScopeDTO} or a {@link Page} of {@link SysUserDataScopeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysUserDataScopeQueryService
    extends QueryService<
    SysUserDataScope,
    QSysUserDataScope,
    SysUserDataScopeRepository> {

    private final Logger log = LoggerFactory.getLogger(SysUserDataScopeQueryService.class);

    private final SysUserDataScopeRepository sysUserDataScopeRepository;

    private final SysUserDataScopeMapper sysUserDataScopeMapper;

    public SysUserDataScopeQueryService(SysUserDataScopeRepository sysUserDataScopeRepository, SysUserDataScopeMapper sysUserDataScopeMapper) {
        this.sysUserDataScopeRepository = sysUserDataScopeRepository;
        this.sysUserDataScopeMapper = sysUserDataScopeMapper;
    }

    /**
     * Return a {@link List} of {@link SysUserDataScopeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysUserDataScopeDTO> findByCriteria(SysUserDataScopeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysUserDataScope> specification = createSpecification(criteria);
        return sysUserDataScopeMapper.toDto(sysUserDataScopeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysUserDataScopeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysUserDataScopeDTO> findByCriteria(SysUserDataScopeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysUserDataScope> specification = createSpecification(criteria);
        return sysUserDataScopeRepository.findAll(specification, page)
            .map(sysUserDataScopeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysUserDataScopeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysUserDataScope> specification = createSpecification(criteria);
        return sysUserDataScopeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SysUserDataScope> createSpecification(SysUserDataScopeCriteria criteria) {
        Specification<SysUserDataScope> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysUserDataScope_.id));
            }
            if (criteria.getSysUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysUserId(), SysUserDataScope_.sysUserId));
            }
            if (criteria.getCtrlType() != null) {
                specification = specification.and(buildSpecification(criteria.getCtrlType(), SysUserDataScope_.ctrlType));
            }
            if (criteria.getCtrlData() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCtrlData(), SysUserDataScope_.ctrlData));
            }
            if (criteria.getCtrlPermi() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCtrlPermi(), SysUserDataScope_.ctrlPermi));
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
    public Specification<SysUserDataScope> createSpecification(Criteria criteria) {
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
    public Optional<SysUserDataScopeDTO> getDTOById(Long id) {
        return Optional.empty();
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    public JPAQuery<SysUserDataScope> getEntityJPAQuery() {
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
    public JPAQuery<SysUserDataScopeDTO> getDTOJPAQuery() {
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
