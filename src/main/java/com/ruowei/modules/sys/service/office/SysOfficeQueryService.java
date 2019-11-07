package com.ruowei.modules.sys.service.office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.error.exception.DataInvalidException;
import com.ruowei.common.pojo.BaseTree;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.service.query.simple.QueryService;
import com.ruowei.modules.sys.domain.table.QSysOffice;
import com.ruowei.modules.sys.domain.table.SysOffice_;
import com.ruowei.modules.sys.pojo.SysOfficeTree;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.repository.SysOfficeRepository;
import com.ruowei.modules.sys.pojo.SysOfficeCriteria;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.mapper.SysOfficeMapper;

/**
 * Service for executing complex queries for {@link SysOffice} entities in the database.
 * The main input is a {@link SysOfficeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysOfficeDTO} or a {@link Page} of {@link SysOfficeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysOfficeQueryService
    extends QueryService<
    SysOffice,
    QSysOffice,
    SysOfficeRepository> {

    private final Logger log = LoggerFactory.getLogger(SysOfficeQueryService.class);

    private final SysOfficeMapper sysOfficeMapper;

    private final static String OFFICE_ROOT_NOT_FOUND = "没找到机构树的根节点";
    private final static String OFFICE_TREE_NOT_FOUND = "没找到机构树";

    public SysOfficeQueryService(SysOfficeMapper sysOfficeMapper) {
        this.sysOfficeMapper = sysOfficeMapper;
    }

    /**
     * Return a {@link List} of {@link SysOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysOfficeDTO> findByCriteria(SysOfficeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysOffice> specification = createSpecification(criteria);
        return sysOfficeMapper.toDto(this.jpaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysOfficeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysOfficeDTO> findByCriteria(SysOfficeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysOffice> specification = createSpecification(criteria);
        return this.jpaRepository.findAll(specification, page)
            .map(sysOfficeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysOfficeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysOffice> specification = createSpecification(criteria);
        return this.jpaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param superCriteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
//    @Override
    public Specification<SysOffice> createSpecification(Criteria superCriteria) {
        SysOfficeCriteria criteria= (SysOfficeCriteria) superCriteria;
        Specification<SysOffice> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysOffice_.id));
            }
            if (criteria.getOfficeCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficeCode(), SysOffice_.officeCode));
            }
            if (criteria.getParentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCode(), SysOffice_.parentCode));
            }
            if (criteria.getParentCodes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParentCodes(), SysOffice_.parentCodes));
            }
            if (criteria.getTreeSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSort(), SysOffice_.treeSort));
            }
            if (criteria.getTreeSorts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeSorts(), SysOffice_.treeSorts));
            }
            if (criteria.getTreeLeaf() != null) {
                specification = specification.and(buildSpecification(criteria.getTreeLeaf(), SysOffice_.treeLeaf));
            }
            if (criteria.getTreeLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTreeLevel(), SysOffice_.treeLevel));
            }
            if (criteria.getTreeNames() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTreeNames(), SysOffice_.treeNames));
            }
            if (criteria.getViewCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getViewCode(), SysOffice_.viewCode));
            }
            if (criteria.getOfficeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOfficeName(), SysOffice_.officeName));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), SysOffice_.fullName));
            }
            if (criteria.getOfficeType() != null) {
                specification = specification.and(buildSpecification(criteria.getOfficeType(), SysOffice_.officeType));
            }
            if (criteria.getLeader() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLeader(), SysOffice_.leader));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), SysOffice_.phone));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), SysOffice_.address));
            }
            if (criteria.getZipCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getZipCode(), SysOffice_.zipCode));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SysOffice_.email));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysOffice_.remarks));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysOffice_.status));
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
    public BooleanBuilder createBooleanBuilder(Criteria... criteriaArray) {
        SysOfficeCriteria sysOfficeCriteria = (SysOfficeCriteria) criteriaArray[0];
        BooleanBuilder sysOfficeBooleanBuilder = createBooleanBuilder(sysOfficeCriteria);
        return sysOfficeBooleanBuilder;
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
    public Optional<SysOfficeDTO> getDTOById(Long id) {
        QSysOffice qSysOffice = QSysOffice.sysOffice;
        JPAQuery<SysOfficeDTO> jpaQuery =  getDTOJPAQuery();
        jpaQuery.where(qSysOffice.id.eq(id));
        SysOfficeDTO sysOfficeDTO = jpaQuery.fetchFirst();
        Optional<SysOfficeDTO> result = Optional.ofNullable(sysOfficeDTO);
        return result;
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
//    @Override
    @Deprecated
    public JPAQuery<SysOffice> getEntityJPAQuery() {
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
    public JPAQuery<SysOfficeDTO> getDTOJPAQuery() {
        QSysOffice qSysOffice = QSysOffice.sysOffice;
        JPAQuery<SysOfficeDTO> jpaQuery = this.queryFactory.select(
            Projections.bean(
                SysOfficeDTO.class,
                qSysOffice.id,
                qSysOffice.officeCode,
                qSysOffice.parentCode,
                qSysOffice.parentCodes,
                qSysOffice.treeSort,
                qSysOffice.treeSorts,
                qSysOffice.treeLeaf,
                qSysOffice.treeLevel,
                qSysOffice.treeNames,
                qSysOffice.viewCode,
                qSysOffice.officeName,
                qSysOffice.fullName,
                qSysOffice.officeType,
                qSysOffice.leader,
                qSysOffice.phone,
                qSysOffice.address,
                qSysOffice.zipCode,
                qSysOffice.email,
                qSysOffice.remarks,
                qSysOffice.status
            )
        ).from(qSysOffice);
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
     * 通过递归获取机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     */
    @Deprecated
    public BaseTree getOfficeTreeByRecursiveQuery() {
        SysOffice root = this.jpaRepository.findFirstByParentCodeIsNullOrderByTreeSortAsc();
        if(root != null){
            BaseTree result = recursiveQuerySysOffice(root);
            return result;
        }else{
            throw new DataInvalidException(OFFICE_ROOT_NOT_FOUND);
        }

    }

    /**
     * 通过循环获取机构树
     * 依赖SysOffice的TreeSorts字段
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     */
    public BaseTree getOfficeTreeByOneQuery(){
        List<SysOffice> sysOfficeList = this.jpaRepository.findAllByOrderByTreeSortsAsc();
        if(sysOfficeList == null){
            throw new DataInvalidException(OFFICE_TREE_NOT_FOUND);
        }
        SysOffice root = sysOfficeList.remove(0);
        BaseTree result = sysOfficeMapper.toBaseTree(root);
        HashMap<String,List<BaseTree>> childrenMap = new HashMap<String,List<BaseTree>>();
        for(SysOffice sysOffice:sysOfficeList){
            List<BaseTree> list = childrenMap.get(sysOffice.getParentCode());
            if(list == null){
                list = new ArrayList<BaseTree>();
                childrenMap.put(sysOffice.getParentCode(),list);
            }
            list.add(sysOfficeMapper.toBaseTree(sysOffice));
        }
        recursiveTree(result,childrenMap);
        return result;
    }

    public SysOfficeTree getOfficeTreeByOneQuery(String parentCode){
        List<SysOffice> sysOfficeList = this.jpaRepository.findAllByParentCodesLikeOrderByTreeSortsAsc(parentCode);
        if(sysOfficeList == null){
            throw new DataInvalidException(OFFICE_TREE_NOT_FOUND);
        }
        SysOffice root = sysOfficeList.remove(0);
        SysOfficeTree result = sysOfficeMapper.toSysOfficeTree(root);
        HashMap<String,List<SysOfficeTree>> childrenMap = new HashMap<String,List<SysOfficeTree>>();
        for(SysOffice sysOffice:sysOfficeList){
            List<SysOfficeTree> list = childrenMap.get(sysOffice.getParentCode());
            if(list == null){
                list = new ArrayList<SysOfficeTree>();
                childrenMap.put(sysOffice.getParentCode(),list);
            }
            list.add(sysOfficeMapper.toSysOfficeTree(sysOffice));
        }
        recursiveTree(result,childrenMap);
        return result;
    }

    /**
     * 递归访问数据库，构建机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param parent
     */
    private BaseTree recursiveQuerySysOffice(SysOffice parent){
        BaseTree result = sysOfficeMapper.toBaseTree(parent);
        if(!parent.isTreeLeaf()){
            List<SysOffice> sysOfficeList = this.jpaRepository.findAllByParentCodeOrderByTreeSortAsc(parent.getOfficeCode());
            List<BaseTree> children = new ArrayList<BaseTree>();
            if(sysOfficeList!=null){
                for(SysOffice sysOffice: sysOfficeList){
                    children.add(recursiveQuerySysOffice(sysOffice));
                }
            }
            result.setChildrenList(children);
        }
        return result;
    }

    /**
     * 递归树
     * @author 刘东奇
     * @date 2019/9/30
     * @param node
     * @param childrenMap
     */
    private void recursiveTree(BaseTree node, HashMap<String,List<BaseTree>> childrenMap){
        List<BaseTree> list = childrenMap.get(node.getCode());
        node.setChildrenList(list);
        if(list == null){
            //说明本节点是叶子节点
            return;
        }else{
            for(BaseTree baseTree :list){
                recursiveTree(baseTree,childrenMap);
            }
        }
    }

    /**
     * 递归树
     * @author 刘东奇
     * @date 2019/9/30
     * @param node
     * @param childrenMap
     */
    private void recursiveTree(SysOfficeTree node, HashMap<String,List<SysOfficeTree>> childrenMap){
        List<SysOfficeTree> list = childrenMap.get(node.getCode());
        node.setChildrenList(list);
        if(list == null){
            //说明本节点是叶子节点
            return;
        }else{
            for(SysOfficeTree baseTree :list){
                recursiveTree(baseTree,childrenMap);
            }
        }
    }

}
