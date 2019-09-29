package com.ruowei.common.service.api;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import io.github.jhipster.service.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * 查询 通用接口
 * @author 刘东奇
 * @date 2019/9/2
 * @param <Entity> 实体
 * @param <Long> 实体主键
 * @param <VM> 视图对象
 */
public interface QueryServiceApi <Entity extends BaseEntity,
    Long,
    DTO extends BaseDTO,
    VM extends BaseView>{

    /**
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     * @param criteria
     * @return
     */
    @Deprecated
    Specification<Entity> createSpecification(Criteria criteria);

    /**
     * 把前端传过来的Query转换成Querydsl能处理的Predicate
     * 可以根据实际需求有不同的实现
     * @param criteriaArray
     * @return
     */
    BooleanBuilder createBooleanBuilder(Criteria ...criteriaArray);

    /**
     * 通过CriteriaArray查询分页视图
     * @author 刘东奇
     * @param pageable
     * @param criteriaArray
     */
    QueryResults<VM> findPageVMByCriteriaArray(Pageable pageable, Criteria ...criteriaArray);

    /**
     * 根据Id查询唯一实体
     * @param id
     * @return
     */
    Entity getById(Long id);

    /**
     * 通过predicate查询唯一实体
     * @param predicate
     * @return
     */
    Entity getOne(Predicate predicate);

    /**
     * 根据Id查询唯一DTO
     * @param id
     * @return
     */
    Optional<DTO> getDTOById(Long id);

    /**
     * 查询全部实体
     * @return
     */
    List<Entity> findAllEntity();

    /**
     * 通过predicate查询全部实体
     * @param predicate
     * @return
     */
    List<Entity> findAllEntity(Predicate predicate);

    /**
     * 通过predicate查询分页实体
     * @param predicate
     * @param pageable
     * @return
     */
    Page<Entity> findPageEntity(Predicate predicate, Pageable pageable);

    /**
     * 组装Entity视图
     * @author 刘东奇
     * @date 2019/9/25
     * @param
     */
    JPAQuery<Entity> getEntityJPAQuery();

    /**
     * 组装VM视图
     * @author 刘东奇
     * @date 2019/9/25
     * @param
     */
    JPAQuery<VM> getVMJPAQuery();

    /**
     * 组装DTO视图
     * @author 刘东奇
     * @date 2019/9/25
     * @param
     */
    JPAQuery<DTO> getDTOJPAQuery();

    /**
     * 组装Tuple视图
     * @author 刘东奇
     * @date 2019/9/26
     * @param
     */
    JPAQuery<Tuple> getTupleJPAQuery();

    /**
     * 判断是否存在
     * @param id
     * @return
     */
    Boolean existsById(Long id);

    /**
     * 判断是否存在
     * @param predicate
     * @return
     */
    Boolean exists(Predicate predicate);
}
