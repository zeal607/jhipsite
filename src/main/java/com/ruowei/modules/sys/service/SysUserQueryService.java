package com.ruowei.modules.sys.service;

import java.util.List;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.ruowei.common.service.QueryBaseService;
import com.ruowei.domain.QSysEmployee;
import com.ruowei.modules.sys.domain.QSysUser;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.domain.SysUser_;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.pojo.SysEmployeeCriteria;
import com.ruowei.modules.sys.pojo.SysUserEmployeeVM;
import com.ruowei.modules.sys.repository.SysUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.ruowei.modules.sys.pojo.SysUserCriteria;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.mapper.SysUserMapper;

/**
 * Service for executing complex queries for {@link SysUser} entities in the database.
 * The main input is a {@link SysUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SysUserDTO} or a {@link Page} of {@link SysUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SysUserQueryService extends QueryBaseService<SysUser,Long,SysUserCriteria, SysUserEmployeeVM,SysUserRepository> {

    private final Logger log = LoggerFactory.getLogger(SysUserQueryService.class);

    private final SysUserMapper sysUserMapper;

    public SysUserQueryService(SysUserMapper sysUserMapper) {
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
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    protected Specification<SysUser> createSpecification(SysUserCriteria criteria) {
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
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     *
     * @param userCriteria
     * @return
     */
    @Override
    public Specification<SysUser> toJPASpecification(SysUserCriteria userCriteria) {
        return createSpecification(userCriteria);
    }

    /**
     * 把前端传过来的Query转换成Querydsl能处理的Predicate
     * 可以根据实际需求有不同的实现
     *
     * @param userCriteria
     * @return
     */
    @Override
    public Predicate toQuerydslPredicate(SysUserCriteria userCriteria) {
        return null;
    }

    public Page<SysUserEmployeeVM> findSysUserEmployeeVMPageByCriteria(SysUserCriteria userCriteria, SysEmployeeCriteria employeeCriteria, Pageable page) {
        QSysUser qSysUser =  QSysUser.sysUser;
        QSysEmployee qSysEmployee = QSysEmployee.sysEmployee;
        this.queryFactory.select(
            Projections.bean(
                SysUserEmployeeVM.class,
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
                qSysUser.status.as("userStatus"),
                qSysEmployee.status.as("empStatus")
            )
        ).from(qSysUser)
            .leftJoin(qSysEmployee).on(qSysUser.userType.eq(UserType.EMPLOYEE).and(qSysUser.refCode.eq(qSysEmployee.empCode)))
            .where();
        BooleanBuilder builder = new BooleanBuilder();
        if(userCriteria!=null){
            if(userCriteria.getAvatar()!=null){

            }
        }
        return null;
    }
}
