package com.ruowei.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * entity 基类
 * @author 刘东奇
 * @date 2019/9/8
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GenericGenerator(name="idGenerator", strategy = "com.ruowei.common.idgen.IdGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGenerator")
    private Long id;

    @Transient
    protected String entityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
