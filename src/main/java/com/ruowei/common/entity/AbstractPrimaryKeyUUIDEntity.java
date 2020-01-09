package com.ruowei.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * entity 基类
 * @author 刘东奇
 * @date 2020/1/7
 */
@MappedSuperclass
public abstract class AbstractPrimaryKeyUUIDEntity implements Serializable {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    public String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
