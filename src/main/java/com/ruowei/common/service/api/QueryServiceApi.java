package com.ruowei.common.service.api;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.pojo.BaseView;
import io.github.jhipster.service.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 查询 通用接口
 * @author 刘东奇
 * @date 2019/9/2
 * @param <Entity> 实体
 * @param <Long> 实体主键
 * @param <Criteria> 查询对象
 * @param <VO> 视图对象
 */
public interface QueryServiceApi <Entity extends BaseEntity,Long,Criteria,VO extends BaseView>{

    /**
     * 把前端传过来的Query转换成JPA能处理的Specification
     * 可以根据实际需求有不同的实现
     * @param criteria
     * @return
     */
    Specification<Entity> createSpecification(Criteria criteria);

    /**
     * 把前端传过来的Query转换成Querydsl能处理的Predicate
     * 可以根据实际需求有不同的实现
     * @param criteria
     * @return
     */
    BooleanBuilder createBooleanBuilder(Criteria criteria);

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

//    /**
//     * 通过predicate查询唯一视图
//     * @param predicate
//     * @return
//     */
//    VO getVO(Predicate predicate);

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

//    /**
//     * 通过predicate查询全部视图
//     * @param predicate
//     * @return
//     */
//    List<VO> findAllVO(Predicate predicate);

    /**
     * 通过predicate查询分页实体
     * @param predicate
     * @param pageable
     * @return
     */
    Page<Entity> findPageEntity(Predicate predicate, Pageable pageable);

//    /**
//     * 通过predicate查询分页视图
//     * @param predicate
//     * @param pageable
//     * @return
//     */
//    Page<VO> findPageVO(Predicate predicate, Pageable pageable);

}
