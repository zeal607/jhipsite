package com.ruowei.common.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.repository.BaseRepository;
import com.ruowei.common.service.api.QueryServiceApi;
import io.github.jhipster.service.filter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 查询 通用服务层
 * @author 刘东奇
 * @param <Entity> 实体
 * @param <Long> 主键
 * @param <Criteria> 查询对象
 * @param <VO> 视图对象
 * @param <Repository> dao层
 */
@Transactional(readOnly = true)
public abstract class QueryBaseService <Entity extends BaseEntity,Long,Criteria,VO extends BaseView,Repository extends BaseRepository<Entity,Long>>
    extends io.github.jhipster.service.QueryService
    implements QueryServiceApi<Entity,Long,Criteria,VO> {

    @Autowired
    protected Repository jpaRepository;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 根据ID获取实体
     * @author 刘东奇
     * @date 2019/9/3
     * @param id
     */
    @Override
    public Entity getById(Long id){
        Entity one= jpaRepository.getOne(id);
        return one;
    }

    /**
     * 根据条件获取实体
     * @author 刘东奇
     * @date 2019/9/3
     * @param predicate
     */
    @Override
    public Entity getOne(Predicate predicate){
        Entity one= (Entity) jpaRepository.findOne(predicate).orElse(null);
        return one;
    }


    /**
     * 查询所有实体
     * @author 刘东奇
     * @date 2019/9/3
     * @param
     */
    @Override
    public List<Entity> findAllEntity(){
        List<Entity> list= jpaRepository.findAll();
        return list;
    }

    /**
     * 根据条件查询实体
     * @author 刘东奇
     * @date 2019/9/3
     * @param predicate
     */
    @Override
    public List<Entity> findAllEntity(Predicate predicate) {
        if(predicate==null){
            return jpaRepository.findAll();
        }
        return (List<Entity>) jpaRepository.findAll(predicate);
    }

    /**
     * 通过predicate查询分页实体
     * @param predicate
     * @param pageable
     * @return
     */
    @Override
    public Page<Entity> findPageEntity(Predicate predicate, Pageable pageable){
        if(predicate==null){
            return (Page<Entity>) jpaRepository.findAll(pageable);
        }
        return (Page<Entity>) jpaRepository.findAll(predicate,pageable);
    }


    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:Long
     * @author 刘东奇
     * @date 2019/9/11
     * @param longFilter
     * @param longNumberPath
     */
    public BooleanBuilder createBooleanBuilder(LongFilter longFilter, NumberPath<java.lang.Long> longNumberPath){
        return createAllBooleanBuilder(createEqualsSpecifiedInBooleanBuilder(longFilter,longNumberPath), createCompareBooleanBuilder(longFilter,longNumberPath));
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:String
     * @author 刘东奇
     * @date 2019/9/11
     * @param stringFilter
     * @param stringPath
     */
    public BooleanBuilder createBooleanBuilder(StringFilter stringFilter, StringPath stringPath){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder equalsSpecifiedInBuilder = createEqualsSpecifiedInBooleanBuilder(stringFilter,stringPath);
        if(equalsSpecifiedInBuilder.hasValue()){
            builder.and(equalsSpecifiedInBuilder);
        }
        if(stringFilter.getContains() != null){
            builder.and(stringPath.likeIgnoreCase(stringFilter.getContains()));
        }
        return builder;
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:枚举
     * @author 刘东奇
     * @date 2019/9/11
     * @param enumFilter
     * @param enumPath
     */
    public BooleanBuilder createBooleanBuilder(Filter enumFilter,  EnumPath enumPath){
        BooleanBuilder builder =  createEqualsSpecifiedInBooleanBuilder(enumFilter,enumPath);
        return builder;
    }

    /**
     * 拼接某一字段的查询条件
     * 字段数据类型:Instant
     * @author 刘东奇
     * @date 2019/9/11
     * @param instantFilter
     * @param dateTimePath
     */
    public BooleanBuilder createBooleanBuilder(InstantFilter instantFilter, DateTimePath dateTimePath){
        return createAllBooleanBuilder(createEqualsSpecifiedInBooleanBuilder(instantFilter, dateTimePath), createCompareBooleanBuilder(instantFilter, dateTimePath));
    }

    /**
     * 构建所有
     * @author 刘东奇
     * @date 2019/9/11
     * @param equalsSpecifiedInBooleanBuilder
    * @param compareBooleanBuilder2
     */
    private BooleanBuilder createAllBooleanBuilder(BooleanBuilder equalsSpecifiedInBooleanBuilder, BooleanBuilder compareBooleanBuilder2) {
        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder equalsSpecifiedInBuilder = equalsSpecifiedInBooleanBuilder;
        if(equalsSpecifiedInBuilder.hasValue()){
            builder.and(equalsSpecifiedInBuilder);
        }else {
            BooleanBuilder compareBooleanBuilder = compareBooleanBuilder2;
            if(compareBooleanBuilder.hasValue()){
                builder.and(compareBooleanBuilder);
            }
        }
        return builder;
    }

    /**
     * 拼接某一字段的等值、判空、集合查询条件
     * @author 刘东奇
     * @date 2019/9/11
     * @param filter
     * @param simpleExpression
     */
    private BooleanBuilder createEqualsSpecifiedInBooleanBuilder(Filter filter, SimpleExpression simpleExpression){
        BooleanBuilder builder = new BooleanBuilder();
        if(filter.getEquals() != null){
            builder.and(simpleExpression.eq(filter.getEquals()));
        }else if(filter.getSpecified() != null){
            if(filter.getSpecified() == true){
                builder.and(simpleExpression.isNotNull());
            }else{
                builder.and(simpleExpression.isNull());
            }
        } else if(filter.getIn() !=null){
            builder.and(simpleExpression.in(filter.getIn()));
        }
        return builder;
    }

    /**
     * 拼接某一字段的比较查询条件
     * @author 刘东奇
     * @date 2019/9/11
     * @param rangeFilter
     * @param comparableExpression
     */
    private BooleanBuilder createCompareBooleanBuilder(RangeFilter rangeFilter, ComparableExpression comparableExpression){
        BooleanBuilder builder = new BooleanBuilder();
        if(rangeFilter.getGreaterThan() != null){
            builder.and(comparableExpression.gt(rangeFilter.getGreaterThan()));
        }
        if(rangeFilter.getGreaterThanOrEqual()!=null){
            builder.and(comparableExpression.goe(rangeFilter.getGreaterThanOrEqual()));
        }
        if(rangeFilter.getLessThan()!=null){
            builder.and(comparableExpression.lt(rangeFilter.getLessThan()));
        }
        if(rangeFilter.getLessThanOrEqual()!=null){
            builder.and(comparableExpression.loe(rangeFilter.getLessThanOrEqual()));
        }
        return builder;
    }

    /**
     * 拼接某一字段的比较查询条件
     * @author 刘东奇
     * @date 2019/9/11
     * @param rangeFilter
     * @param numberExpression
     */
    private BooleanBuilder createCompareBooleanBuilder(RangeFilter rangeFilter, NumberExpression numberExpression){
        BooleanBuilder builder = new BooleanBuilder();
        if(rangeFilter.getGreaterThan() != null){
            builder.and(numberExpression.gt((Number) rangeFilter.getGreaterThan()));
        }
        if(rangeFilter.getGreaterThanOrEqual()!=null){
            builder.and(numberExpression.goe((Number)  rangeFilter.getGreaterThanOrEqual()));
        }
        if(rangeFilter.getLessThan()!=null){
            builder.and(numberExpression.lt((Number)  rangeFilter.getLessThan()));
        }
        if(rangeFilter.getLessThanOrEqual()!=null){
            builder.and(numberExpression.loe((Number)  rangeFilter.getLessThanOrEqual()));
        }
        return builder;
    }
}
