package com.ruowei.common.pojo;

import java.time.Instant;

/**
 * 数据传输基类
 * @author 刘东奇
 * @date 2019/9/2
 */
public class BaseDTO {

    /**
     * id
     */
    protected Long id;

    /**
     * 数据传输对象构建的时间
     */
    private Instant buildTime= Instant.now();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Instant buildTime) {
        this.buildTime = buildTime;
    }
}
