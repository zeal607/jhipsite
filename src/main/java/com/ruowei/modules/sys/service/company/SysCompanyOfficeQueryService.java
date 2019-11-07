package com.ruowei.modules.sys.service.company;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.query.simple.QueryService;
import com.ruowei.modules.sys.domain.table.QSysCompanyOffice;
import com.ruowei.modules.sys.domain.table.SysCompanyOffice;
import com.ruowei.modules.sys.domain.table.SysCompanyOffice_;
import com.ruowei.modules.sys.repository.SysCompanyOfficeRepository;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.service.dto.SysCompanyOfficeCriteria;
import com.ruowei.service.dto.SysCompanyOfficeDTO;
import com.ruowei.service.mapper.SysCompanyOfficeMapper;

/**
 * Service for executing complex queries for {@link SysCompanyOffice} entities in the database.
 * The main input is a {@link SysCompanyOfficeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysCompanyOfficeDTO} or a {@link Page} of {@link SysCompanyOfficeDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysCompanyOfficeQueryService
    extends QueryService<
        SysCompanyOffice,
        QSysCompanyOffice,
        SysCompanyOfficeRepository> {

    private final Logger log = LoggerFactory.getLogger(SysCompanyOfficeQueryService.class);

    private final SysCompanyOfficeRepository sysCompanyOfficeRepository;

    private final SysCompanyOfficeMapper sysCompanyOfficeMapper;

    public SysCompanyOfficeQueryService(SysCompanyOfficeRepository sysCompanyOfficeRepository, SysCompanyOfficeMapper sysCompanyOfficeMapper) {
        this.sysCompanyOfficeRepository = sysCompanyOfficeRepository;
        this.sysCompanyOfficeMapper = sysCompanyOfficeMapper;
    }

    /**
     * Return a {@link List} of {@link SysCompanyOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysCompanyOfficeDTO> findByCriteria(SysCompanyOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysCompanyOffice> specification = createSpecification(criteria);
        return sysCompanyOfficeMapper.toDto(sysCompanyOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysCompanyOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysCompanyOfficeDTO> findByCriteria(SysCompanyOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysCompanyOffice> specification = createSpecification(criteria);
        return sysCompanyOfficeRepository.findAll(specification, page)
            .map(sysCompanyOfficeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysCompanyOfficeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysCompanyOffice> specification = createSpecification(criteria);
        return sysCompanyOfficeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<SysCompanyOffice> createSpecification(SysCompanyOfficeCriteria criteria) {
        Specification<SysCompanyOffice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysCompanyOffice_.id));
            }
            if (criteria.getSysCompanyId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysCompanyId(), SysCompanyOffice_.sysCompanyId));
            }
            if (criteria.getSysOfficeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysOfficeId(), SysCompanyOffice_.sysOfficeId));
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
    public Specification<SysCompanyOffice> createSpecification(Criteria criteria) {
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
    public Optional<SysCompanyOfficeDTO> getDTOById(Long id) {
        return Optional.empty();
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    public JPAQuery<SysCompanyOffice> getEntityJPAQuery() {
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
    public JPAQuery<SysCompanyOfficeDTO> getDTOJPAQuery() {
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
