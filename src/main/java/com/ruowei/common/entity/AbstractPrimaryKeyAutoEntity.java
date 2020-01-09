package com.ruowei.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * entity 基类
 * @author 刘东奇
 * @date 2020/1/7
 */
@MappedSuperclass
public abstract class AbstractPrimaryKeyAutoEntity implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
