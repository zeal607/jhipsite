package com.ruowei.common.service;

import com.querydsl.core.types.Predicate;
import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.repository.BaseRepository;
import com.ruowei.common.service.api.QueryServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

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
}
