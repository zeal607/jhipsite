package com.ruowei.modules.sys.service.user.impl;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.querydsl.OrderByUtils;
import com.ruowei.common.service.QueryBaseService;
import com.ruowei.modules.sys.domain.*;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.pojo.*;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.employee.impl.SysEmployeeOfficeQueryService;
import com.ruowei.modules.sys.service.post.SysPostQueryService;
import com.ruowei.modules.sys.service.role.impl.SysRoleQueryService;
import io.github.jhipster.service.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruowei.modules.sys.mapper.SysUserMapper;

/**
 * Service for executing complex queries for {@link SysUser} entities in the database.
 * The main input is a {@link SysUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysUserDTO} or a {@link Page} of {@link SysUserDTO} which fulfills the criteria.
 * @author 刘东奇
 */
@Service
@Transactional(readOnly = true)
public class SysUserQueryService
    extends QueryBaseService<SysUser,Long,SysUserEmployeeDTO,SysUserEmployeeVM,SysUserRepository> {

    private final Logger log = LoggerFactory.getLogger(SysUserQueryService.class);

    /**
     * service依赖
     */
    private final SysPostQueryService sysPostQueryService;
    private final SysEmployeeOfficeQueryService sysEmployeeOfficeQueryService;
    private final SysRoleQueryService sysRoleQueryService;
    /**
     * mapper依赖
     */
    private final SysUserMapper sysUserMapper;

    public SysUserQueryService(SysPostQueryService sysPostQueryService,
                               SysEmployeeOfficeQueryService sysEmployeeOfficeQueryService,
                               SysRoleQueryService sysRoleQueryService,
                               SysUserMapper sysUserMapper) {
        this.sysPostQueryService = sysPostQueryService;
        this.sysEmployeeOfficeQueryService = sysEmployeeOfficeQueryService;
        this.sysRoleQueryService = sysRoleQueryService;

        this.sysUserMapper = sysUserMapper;
    }

    /**
     * Return a {@link List} of {@link SysUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SysUserDTO> findByCriteria(SysUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SysUser> specification = createSpecification(criteria);
        return sysUserMapper.toDto(this.jpaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SysUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SysUserDTO> findByCriteria(SysUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SysUser> specification = createSpecification(criteria);
        return jpaRepository.findAll(specification, page)
            .map(sysUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SysUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SysUser> specification = createSpecification(criteria);
        return jpaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param superCriteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    @Override
    public Specification<SysUser> createSpecification(Criteria superCriteria) {
        SysUserCriteria criteria= (SysUserCriteria) superCriteria;
        Specification<SysUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SysUser_.id));
            }
            if (criteria.getUserCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserCode(), SysUser_.userCode));
            }
            if (criteria.getLoginCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLoginCode(), SysUser_.loginCode));
            }
            if (criteria.getUserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), SysUser_.userName));
            }
            if (criteria.getPassword() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassword(), SysUser_.password));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), SysUser_.email));
            }
            if (criteria.getMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobile(), SysUser_.mobile));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), SysUser_.phone));
            }
            if (criteria.getSex() != null) {
                specification = specification.and(buildSpecification(criteria.getSex(), SysUser_.sex));
            }
            if (criteria.getAvatar() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAvatar(), SysUser_.avatar));
            }
            if (criteria.getSign() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSign(), SysUser_.sign));
            }
            if (criteria.getWxOpenid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWxOpenid(), SysUser_.wxOpenid));
            }
            if (criteria.getMobileImei() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileImei(), SysUser_.mobileImei));
            }
            if (criteria.getUserType() != null) {
                specification = specification.and(buildSpecification(criteria.getUserType(), SysUser_.userType));
            }
            if (criteria.getRefCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRefCode(), SysUser_.refCode));
            }
            if (criteria.getRefName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRefName(), SysUser_.refName));
            }
            if (criteria.getLastLoginIp() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastLoginIp(), SysUser_.lastLoginIp));
            }
            if (criteria.getLastLoginDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastLoginDate(), SysUser_.lastLoginDate));
            }
            if (criteria.getFreezeDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFreezeDate(), SysUser_.freezeDate));
            }
            if (criteria.getFreezeCause() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFreezeCause(), SysUser_.freezeCause));
            }
            if (criteria.getUserSort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUserSort(), SysUser_.userSort));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), SysUser_.status));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), SysUser_.remarks));
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
        SysUserCriteria userCriteria = (SysUserCriteria) criteriaArray[0];
        BooleanBuilder userBooleanBuilder = createBooleanBuilder(userCriteria);
        SysEmployeeCriteria employeeCriteria = (SysEmployeeCriteria) criteriaArray[1];
        BooleanBuilder empBooleanBuilder = createBooleanBuilder(employeeCriteria);
        return userBooleanBuilder.and(empBooleanBuilder);
    }

    /**
     * 通过CriteriaArray查询分页视图
     *
     * @param pageable
     * @param criteriaArray
     * @author 刘东奇
     */
    @Override
    public QueryResults<SysUserEmployeeVM> findPageVMByCriteriaArray(Pageable pageable, Criteria... criteriaArray) {

        QSysUser qSysUser =  QSysUser.sysUser;
        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        BooleanBuilder booleanBuilder = createBooleanBuilder(criteriaArray);

        JPAQuery<SysUserEmployeeVM> jpaQuery = getVMJPAQuery();

        jpaQuery=jpaQuery.where(booleanBuilder);

        QueryResults<SysUserEmployeeVM> results = jpaQuery
            .orderBy(OrderByUtils.createOrderSpecifierBy(pageable.getSort(),qSysUser,qSysEmployee))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize()).fetchResults();

        return results;
    }

    /**
     * 根据Id查询唯一DTO
     *
     * @param id
     * @return
     */
    @Override
    public Optional<SysUserEmployeeDTO> getDTOById(Long id) {
        QSysUser qSysUser =  QSysUser.sysUser;

        //获取员工基本信息
        JPAQuery<SysUserEmployeeDTO> jpaQuery =  getDTOJPAQuery();
        jpaQuery.where(qSysUser.id.eq(id));
        SysUserEmployeeDTO sysUserEmployeeDTO = jpaQuery.fetchFirst();

        if(sysUserEmployeeDTO != null){
            // 获取员工岗位信息
            List<SysPostDTO> postDTOList = sysPostQueryService.getSysPostDTOListBySysUserId(id);
            sysUserEmployeeDTO.setSysPostDTOList(postDTOList);

            // 获取员工附属机构及岗位信息
            List<SysEmployeeOfficeDTO> sysEmployeeOfficeDTOList = sysEmployeeOfficeQueryService.getSysEmployeeOfficeDTOListBySysUserId(id);
            sysUserEmployeeDTO.setSysEmployeeOfficeDTOList(sysEmployeeOfficeDTOList);

            // 获取用户角色信息
            List<SysRoleDTO> sysRoleDTOList = sysRoleQueryService.getSysRoleDTOListBySysUserId(id);
            sysUserEmployeeDTO.setSysRoleDTOList(sysRoleDTOList);
        }

        Optional<SysUserEmployeeDTO> result = Optional.ofNullable(sysUserEmployeeDTO);
        return result;
    }

    /**
     * 组装Entity视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    @Deprecated
    public JPAQuery<SysUser> getEntityJPAQuery() {
        return null;
    }

    /**
     * 组装VM视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    public JPAQuery<SysUserEmployeeVM> getVMJPAQuery() {
        QSysUser qSysUser =  QSysUser.sysUser;
        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        JPAQuery<SysUserEmployeeVM> jpaQuery = this.queryFactory.select(
            Projections.bean(
                SysUserEmployeeVM.class,
                qSysUser.id,
                qSysUser.userCode,
                qSysUser.loginCode,
                qSysUser.userName,
                qSysEmployee.empName,
                qSysEmployee.empNameEn,
                qSysEmployee.sysOfficeId,
                qSysEmployee.officeName,
                qSysEmployee.sysCompanyId,
                qSysEmployee.companyName,
                qSysUser.email,
                qSysUser.phone,
                qSysUser.mobile,
                qSysUser.status.as("userStatus"),
                qSysEmployee.status.as("empStatus")
            )
        ).from(qSysUser)
            .leftJoin(qSysEmployee).on(qSysUser.userType.eq(UserType.EMPLOYEE).and(qSysUser.refCode.eq(qSysEmployee.empCode)));
        return jpaQuery;
    }

    /**
     * 组装DTO视图
     *
     * @author 刘东奇
     * @date 2019/9/25
     */
    @Override
    public JPAQuery<SysUserEmployeeDTO> getDTOJPAQuery() {
        QSysUser qSysUser =  QSysUser.sysUser;
        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        JPAQuery<SysUserEmployeeDTO> jpaQuery = this.queryFactory.select(
            Projections.bean(
                SysUserEmployeeDTO.class,
                qSysUser.id,
                qSysEmployee.sysOfficeId,
                qSysEmployee.officeName,
                qSysEmployee.sysCompanyId,
                qSysEmployee.companyName,
                qSysUser.loginCode,
                qSysUser.userName,
                qSysUser.email,
                qSysUser.mobile,
                qSysUser.phone,
                qSysUser.userSort,
                qSysEmployee.empCode,
                qSysEmployee.empName,
                qSysEmployee.empNameEn,
                qSysEmployee.remarks
            )
        ).from(qSysUser)
            .leftJoin(qSysEmployee).on(qSysUser.userType.eq(UserType.EMPLOYEE).and(qSysUser.refCode.eq(qSysEmployee.empCode)));
        return jpaQuery;
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

    /**
     * 组装用户查询条件
     * @author 刘东奇
     * @date 2019/9/25
     * @param userCriteria
     */
    private BooleanBuilder createBooleanBuilder(SysUserCriteria userCriteria) {
        QSysUser qSysUser =  QSysUser.sysUser;
        BooleanBuilder builder = new BooleanBuilder();
        if(userCriteria!=null){
            if(userCriteria.getId()!=null){
                builder.and(createBooleanBuilder(userCriteria.getId(),qSysUser.id));
            }
            if(userCriteria.getUserCode()!=null){
                builder.and(createBooleanBuilder(userCriteria.getUserCode(),qSysUser.userCode));
            }
            if(userCriteria.getLoginCode()!=null){
                builder.and(createBooleanBuilder(userCriteria.getLoginCode(),qSysUser.loginCode));
            }
            if(userCriteria.getUserName()!=null){
                builder.and(createBooleanBuilder(userCriteria.getUserName(),qSysUser.userName));
            }
//            if(userCriteria.getPassword()!=null){
//                builder.and(createBooleanBuilder(userCriteria.getPassword(),qSysUser.password));
//            }
            if(userCriteria.getEmail()!=null){
                builder.and(createBooleanBuilder(userCriteria.getEmail(),qSysUser.email));
            }
            if(userCriteria.getMobile()!=null){
                builder.and(createBooleanBuilder(userCriteria.getMobile(),qSysUser.mobile));
            }
            if(userCriteria.getPhone()!=null){
                builder.and(createBooleanBuilder(userCriteria.getPhone(),qSysUser.phone));
            }
            if(userCriteria.getSex()!=null){
                builder.and(createBooleanBuilder(userCriteria.getSex(),qSysUser.sex));
            }
            if(userCriteria.getAvatar()!=null){
                builder.and(createBooleanBuilder(userCriteria.getAvatar(),qSysUser.avatar));
            }
            if(userCriteria.getSign()!=null){
                builder.and(createBooleanBuilder(userCriteria.getSign(),qSysUser.sign));
            }
            if(userCriteria.getWxOpenid()!=null){
                builder.and(createBooleanBuilder(userCriteria.getWxOpenid(),qSysUser.wxOpenid));
            }
            if(userCriteria.getMobileImei()!=null){
                builder.and(createBooleanBuilder(userCriteria.getMobileImei(),qSysUser.mobileImei));
            }
            if(userCriteria.getUserType()!=null){
                builder.and(createBooleanBuilder(userCriteria.getUserType(),qSysUser.userType));
            }
            if(userCriteria.getRefCode()!=null){
                builder.and(createBooleanBuilder(userCriteria.getRefCode(),qSysUser.refCode));
            }
            if(userCriteria.getRefName()!=null){
                builder.and(createBooleanBuilder(userCriteria.getRefName(),qSysUser.refName));
            }
            if(userCriteria.getLastLoginIp()!=null){
                builder.and(createBooleanBuilder(userCriteria.getLastLoginIp(),qSysUser.lastLoginIp));
            }
            if(userCriteria.getLastLoginDate()!=null){
                builder.and(createBooleanBuilder(userCriteria.getLastLoginDate(),qSysUser.lastLoginDate));
            }
            if(userCriteria.getFreezeCause()!=null){
                builder.and(createBooleanBuilder(userCriteria.getFreezeCause(),qSysUser.freezeCause));
            }
        }
        return builder;
    }

    /**
     * 组装员工查询条件
     * @author 刘东奇
     * @date 2019/9/25
     * @param employeeCriteria
     */
    private BooleanBuilder createBooleanBuilder(SysEmployeeCriteria employeeCriteria) {
        QSysEmployee qSysEmployee =  QSysEmployee.sysEmployee;
        BooleanBuilder builder = new BooleanBuilder();
        if(employeeCriteria != null){
            if(employeeCriteria.getId()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getId(),qSysEmployee.id));
            }
            if(employeeCriteria.getEmpCode()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getEmpCode(),qSysEmployee.empCode));
            }
            if(employeeCriteria.getEmpName()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getEmpName(),qSysEmployee.empName));
            }
            if(employeeCriteria.getEmpNameEn()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getEmpNameEn(),qSysEmployee.empNameEn));
            }
            if(employeeCriteria.getSysOfficeId()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getSysOfficeId(),qSysEmployee.sysOfficeId));
            }
            if(employeeCriteria.getOfficeName()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getOfficeName(),qSysEmployee.officeName));
            }
            if(employeeCriteria.getSysCompanyId()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getSysCompanyId(),qSysEmployee.sysCompanyId));
            }
            if(employeeCriteria.getCompanyName()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getCompanyName(),qSysEmployee.companyName));
            }
            if(employeeCriteria.getStatus()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getStatus(),qSysEmployee.status));
            }
            if(employeeCriteria.getRemarks()!=null){
                builder.and(createBooleanBuilder(employeeCriteria.getRemarks(),qSysEmployee.remarks));
            }
        }
        return builder;
    }

}
