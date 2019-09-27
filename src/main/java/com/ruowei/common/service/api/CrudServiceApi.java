package com.ruowei.common.service.api;

import com.ruowei.common.entity.BaseEntity;
import com.ruowei.common.pojo.BaseView;

import java.util.List;

/**
 * 增删改 通用接口
 * @author 刘东奇
 * @date 2019/9/2
 * @param <Entity> 实体
 * @param <Long> 主键
 * @param <VO> 视图对象
 * @param <DTO> 数据传输对象
 */
public interface CrudServiceApi<Entity extends BaseEntity,Long,VO extends BaseView,DTO>{

    /**
     * 插入
     * @param entity
     * @return
     */
    Entity insert(Entity entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    Entity update(Entity entity);

    /**
     * 插入或更新
     * @param entity
     * @return
     */
    Entity save(Entity entity);

    /**
     * 删除单条数据
     * @param id
     */
    void delete(Long id);

    /**
     * 批量插入或更新
     * @param list
     */
    List<Entity> saveAll(List<Entity> list);


}
