package com.ruowei.common.repository;

import com.ruowei.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * repository扩展接口类
 * 继承了JPA的CrudRepository、JpaRepository
 * 继承了querydsl的QuerydslPredicateExecutor
 * @param <Entity>
 * @param <Long>
 */
@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity,Long>
    extends CrudRepository<Entity, Long>, JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity>, QuerydslPredicateExecutor<Entity>{

}
