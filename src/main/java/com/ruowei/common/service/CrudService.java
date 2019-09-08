package com.ruowei.common.service;


import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataAlreadyExistException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.pojo.BaseDTO;
import com.ruowei.common.pojo.BaseView;
import com.ruowei.common.repository.BaseRepository;
import com.ruowei.common.service.api.CrudServiceApi;
import org.springframework.transaction.annotation.Transactional;

/**
 * 增删改 通用服务层
 * @param <Entity> 实体
 * @param <Long> 主键
 * @param <Criteria> 查询对象
 * @param <VO> 视图对象
 * @param <DTO> 数据传输对象
 * @param <Repository> dao层
 */
@Transactional
public abstract class CrudService<Entity extends BaseEntity,Long ,Criteria,VO extends BaseView,DTO extends BaseDTO,Repository extends BaseRepository<Entity,Long>>
    extends QueryService<Entity,Long,Criteria,VO,Repository>
    implements CrudServiceApi<Entity,Long,Criteria,VO,DTO> {

    /**
     * 删除
     * @author 刘东奇
     * @date 2019/9/6
     * @param id
     */
    @Override
    public void delete(Long id){
        this.jpaRepository.deleteById(id);
    }

    /** 插入
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity insert(Entity entity) {
        Entity exist = this.getById((Long) entity.getId());
        if(exist == null){
            //不存在，则插入
            exist = this.jpaRepository.save(entity);
        }else{
            //否则抛异常
            throw new DataAlreadyExistException(ErrorMessageUtils.getAlreadyExistMessage(exist.getEntityName(),exist.getId().toString()));
        }
        return exist;
    }

    /**
     * 更新
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity update(Entity entity) {
        Entity exist = this.getById((Long) entity.getId());
        if(exist != null){
            //已存在，则更新
            exist = this.jpaRepository.save(entity);
        }else{
            //否则抛异常
            throw new DataNotFoundException(ErrorMessageUtils.getNotFoundMessage(exist.getEntityName(),exist.getId().toString()));
        }
        return exist;
    }

    /**
     * 插入或更新
     * @author 刘东奇
     * @date 2019/9/6
     * @param entity
     */
    @Override
    public Entity save(Entity entity) {
        Entity exist = this.getById((Long) entity.getId());
        if(exist != null){
            //已存在，则更新
            exist = update(entity);
        }else{
            //否则，插入
            exist = insert(entity);
        }
        return exist;
    }

}
