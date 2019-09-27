package com.ruowei.common.service;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataAlreadyExistException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.log.LogMessageUtils;
import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.repository.BaseRepository;
import com.ruowei.common.service.api.CrudServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * 增删改 通用服务层
 * @author 刘东奇
 * @param <Entity> 实体
 * @param <Long> 主键
 * @param <Criteria> 查询对象
 * @param <VO> 视图对象
 * @param <DTO> 数据传输对象
 * @param <Repository> dao层
 */
@Transactional
public abstract class CrudBaseService<Entity extends BaseEntity,Long ,VO extends BaseView,DTO extends BaseDTO,Repository extends BaseRepository<Entity,Long>>
    implements CrudServiceApi<Entity,Long,VO,DTO> {

    private final Logger log = LoggerFactory.getLogger(CrudBaseService.class);

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
     * 删除
     * @author 刘东奇
     * @date 2019/9/6
     * @param id
     */
    @Override
    public void delete(Long id){
        log.debug(LogMessageUtils.getEnterMessage("delete",id));
        jpaRepository.deleteById(id);
        log.debug(LogMessageUtils.getLeaveMessage("delete",null));
    }

    /**
     * 插入
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity insert(Entity entity) {
        log.debug(LogMessageUtils.getEnterMessage("insert",entity));
        if(entity.getId() == null){
            entity = jpaRepository.save(entity);
        }else{
            Optional<Entity> exist =  jpaRepository.findById((Long) entity.getId());
            if(exist.isPresent()){
                //如果存在，抛异常
                throw new DataAlreadyExistException(ErrorMessageUtils.getAlreadyExistMessage(entity.getEntityName(),entity.getId().toString()));
            }else{
                //不存在，则插入
                entity = jpaRepository.save(entity);
            }
        }
        log.debug(LogMessageUtils.getLeaveMessage("insert",entity));
        return entity;
    }

    /**
     * 更新
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity update(Entity entity) {
        log.debug(LogMessageUtils.getEnterMessage("update",entity));
        if(entity.getId() == null){
            throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage(entity.getEntityName(),entity.getId().toString()));
        }else{
            Optional<Entity> exist =  jpaRepository.findById((Long) entity.getId());
            if(exist.isPresent()){
                //如果存在，则更新
                entity = jpaRepository.save(entity);

            }else{
                //不存在，抛异常
                throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage(entity.getEntityName(),entity.getId().toString()));
            }
        }
        log.debug(LogMessageUtils.getLeaveMessage("update",entity));
        return entity;
    }

    /**
     * 插入或更新
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity save(Entity entity) {
        log.debug(LogMessageUtils.getEnterMessage("save",entity));
        entity = jpaRepository.save(entity);
        log.debug(LogMessageUtils.getLeaveMessage("save",entity));
        return entity;
    }

    /**
     * 批量插入或更新
     * @author 刘东奇
     * @date 2019/9/23
     * @param list
     */
    @Override
    public List<Entity> saveAll(List<Entity> list) {
        log.debug(LogMessageUtils.getEnterMessage("saveAll",list));
        List<Entity> result= jpaRepository.saveAll(list);
        log.debug(LogMessageUtils.getLeaveMessage("saveAll",list));
        return result;
    }
}
