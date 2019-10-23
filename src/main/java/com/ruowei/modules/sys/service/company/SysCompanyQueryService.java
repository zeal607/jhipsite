package com.ruowei.modules.sys.service.company;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.QueryBaseService;
import com.ruowei.modules.sys.domain.SysCompany_;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.domain.SysCompany;
import com.ruowei.modules.sys.repository.SysCompanyRepository;
import com.ruowei.modules.sys.pojo.SysCompanyCriteria;
import com.ruowei.modules.sys.pojo.SysCompanyDTO;
import com.ruowei.service.mapper.SysCompanyMapper;

/**
 * Service for executing complex queries for {@link SysCompany} entities in the database.
 * The main input is a {@link SysCompanyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysCompanyDTO} or a {@link Page} of {@link SysCompanyDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysCompanyQueryService
    extends QueryBaseService<SysCompany,Long,SysCompanyDTO, BaseView,SysCompanyRepository> {

    private final Logger log = LoggerFactory.getLogger(SysCompanyQueryService.class);

    private final SysCompanyMapper sysCompanyMapper;

    public SysCompanyQueryService(SysCompanyMapper sysCompanyMapper) {
        this.sysCompanyMapper = sysCompanyMapper;
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param superCriteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    @Override
    public Specification<SysCompany> createSpecification(Criteria superCriteria) {
        SysCompanyCriteria criteria= (SysCompanyCriteria) superCriteria;
        Specification<SysCompany> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysCompany_.id));
            }
            if (criteria.getCompanyCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyCode(), SysCompany_.companyCode));
            }
            if (criteria.getParentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCode(), SysCompany_.parentCode));
            }
            if (criteria.getParentCodes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCodes(), SysCompany_.parentCodes));
            }
            if (criteria.getTreeSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSort(), SysCompany_.treeSort));
            }
            if (criteria.getTreeSorts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSorts(), SysCompany_.treeSorts));
            }
            if (criteria.getTreeLeaf() != null) {
                specification = specification.and(buildSpecification(criteria.getTreeLeaf(), SysCompany_.treeLeaf));
            }
            if (criteria.getTreeLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeLevel(), SysCompany_.treeLevel));
            }
            if (criteria.getTreeNames() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTreeNames(), SysCompany_.treeNames));
            }
            if (criteria.getViewCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewCode(), SysCompany_.viewCode));
            }
            if (criteria.getCompanyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyName(), SysCompany_.companyName));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), SysCompany_.fullName));
            }
            if (criteria.getAreaCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAreaCode(), SysCompany_.areaCode));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysCompany_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysCompany_.remarks));
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
    public Optional<SysCompanyDTO> getDTOById(Long id) {
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
    public JPAQuery<SysCompany> getEntityJPAQuery() {
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
    public JPAQuery<SysCompanyDTO> getDTOJPAQuery() {
        return null;
    }

    /**
     * 组装Tuple视图
     *
     * @author 刘东奇
     * @date 2019/9/26
     */
    @Override
    @Deprecated
    public JPAQuery<Tuple> getTupleJPAQuery() {
        return null;
    }
}
