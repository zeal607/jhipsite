package com.ruowei.modules.sys.service.employee;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.QueryBaseService;
import com.ruowei.modules.sys.domain.*;
import com.ruowei.modules.sys.mapper.SysOfficeMapper;
import com.ruowei.modules.sys.mapper.SysPostMapper;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysPostDTO;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.repository.SysEmployeeOfficeRepository;
import com.ruowei.modules.sys.pojo.SysEmployeeOfficeCriteria;
import com.ruowei.modules.sys.pojo.SysEmployeeOfficeDTO;
import com.ruowei.modules.sys.mapper.SysEmployeeOfficeMapper;

/**
 * Service for executing complex queries for {@link SysEmployeeOffice} entities in the database.
 * The main input is a {@link SysEmployeeOfficeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysEmployeeOfficeDTO} or a {@link Page} of {@link SysEmployeeOfficeDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysEmployeeOfficeQueryService
    extends QueryBaseService<SysEmployeeOffice,Long, SysEmployeeOfficeDTO, BaseView, SysEmployeeOfficeRepository> {

    private final Logger log = LoggerFactory.getLogger(SysEmployeeOfficeQueryService.class);

    private final SysEmployeeOfficeRepository sysEmployeeOfficeRepository;

    private final SysEmployeeOfficeMapper sysEmployeeOfficeMapper;
    private final SysOfficeMapper sysOfficeMapper;
    private final SysPostMapper sysPostMapper;

    public SysEmployeeOfficeQueryService(SysEmployeeOfficeRepository sysEmployeeOfficeRepository,
                                         SysEmployeeOfficeMapper sysEmployeeOfficeMapper,
                                         SysOfficeMapper sysOfficeMapper,
                                         SysPostMapper sysPostMapper) {
        this.sysEmployeeOfficeRepository = sysEmployeeOfficeRepository;

        this.sysEmployeeOfficeMapper = sysEmployeeOfficeMapper;
        this.sysOfficeMapper = sysOfficeMapper;
        this.sysPostMapper = sysPostMapper;
    }

    /**
     * Return a {@link List} of {@link SysEmployeeOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysEmployeeOfficeDTO> findByCriteria(SysEmployeeOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysEmployeeOffice> specification = createSpecification(criteria);
        return sysEmployeeOfficeMapper.toDto(sysEmployeeOfficeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysEmployeeOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysEmployeeOfficeDTO> findByCriteria(SysEmployeeOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysEmployeeOffice> specification = createSpecification(criteria);
        return sysEmployeeOfficeRepository.findAll(specification, page)
            .map(sysEmployeeOfficeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysEmployeeOfficeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysEmployeeOffice> specification = createSpecification(criteria);
        return sysEmployeeOfficeRepository.count(specification);
    }

    /**
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     *
     * @param superCriteria
     * @return
     */
    @Override
    public Specification<SysEmployeeOffice> createSpecification(Criteria superCriteria) {
        SysEmployeeOfficeCriteria criteria= (SysEmployeeOfficeCriteria) superCriteria;
        Specification<SysEmployeeOffice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysEmployeeOffice_.id));
            }
            if (criteria.getSysEmployeeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysEmployeeId(), SysEmployeeOffice_.sysEmployeeId));
            }
            if (criteria.getSysOfficeId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysOfficeId(), SysEmployeeOffice_.sysOfficeId));
            }
            if (criteria.getSysPostId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSysPostId(), SysEmployeeOffice_.sysPostId));
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
    public Optional<SysEmployeeOfficeDTO> getDTOById(Long id) {
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
    public JPAQuery<SysEmployeeOffice> getEntityJPAQuery() {
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
    public JPAQuery<SysEmployeeOfficeDTO> getDTOJPAQuery() {
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
        QSysEmployeeOffice qSysEmployeeOffice = QSysEmployeeOffice.sysEmployeeOffice;
        QSysOffice qSysOffice = QSysOffice.sysOffice;
        QSysPost qSysPost=QSysPost.sysPost;
        JPAQuery<Tuple> jpaQuery = this.queryFactory.select(
            qSysEmployeeOffice,qSysOffice,qSysPost
        ).from(qSysEmployeeOffice)
            .leftJoin(qSysOffice).on(qSysEmployeeOffice.sysOfficeId.eq(qSysOffice.officeCode))
            .leftJoin(qSysPost).on(qSysEmployeeOffice.sysPostId.eq(qSysPost.postCode));
        return jpaQuery;
    }

    /**
     * 通过用户id获取员工附属机构及岗位信息
     * @author 刘东奇
     * @date 2019/9/27
     * @param sysUserId
     */
    public List<SysEmployeeOfficeDTO> getSysEmployeeOfficeDTOListBySysUserId(Long sysUserId){
        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        QSysPost qSysPost = QSysPost.sysPost;
        QSysUser qSysUser =  QSysUser.sysUser;
        QSysEmployeeOffice qSysEmployeeOffice=  QSysEmployeeOffice.sysEmployeeOffice;
        QSysOffice qSysOffice = QSysOffice.sysOffice;

        JPAQuery<Tuple> tupleJPAQuery = getTupleJPAQuery();
        tupleJPAQuery
            .leftJoin(qSysEmployee).on(qSysEmployeeOffice.sysEmployeeId.eq(qSysEmployee.empCode))
            .leftJoin(qSysUser).on(qSysEmployee.empCode.eq(qSysUser.refCode));
        List<SysEmployeeOfficeDTO> sysEmployeeOfficeDTOList=tupleJPAQuery.where(qSysUser.id.eq(sysUserId)).fetch()
            .stream().map(
                tuple -> {
                    SysEmployeeOffice sysEmployeeOffice = tuple.get(qSysEmployeeOffice);
                    SysOffice sysOffice = tuple.get(qSysOffice);
                    SysPost sysPost = tuple.get(qSysPost);

                    SysEmployeeOfficeDTO sysEmployeeOfficeDTO = sysEmployeeOfficeMapper.toDto(sysEmployeeOffice);
                    SysOfficeDTO sysOfficeDTO = sysOfficeMapper.toDto(sysOffice);
                    SysPostDTO sysPostDTO = sysPostMapper.toDto(sysPost);
                    sysEmployeeOfficeDTO.setSysOfficeDTO(sysOfficeDTO);
                    sysEmployeeOfficeDTO.setSysPostDTO(sysPostDTO);

                    return sysEmployeeOfficeDTO;
                }
            ).collect(Collectors.toList());
        return sysEmployeeOfficeDTOList;
    }
}
