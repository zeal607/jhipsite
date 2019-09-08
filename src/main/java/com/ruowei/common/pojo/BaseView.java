package com.ruowei.common.pojo;

import java.time.Instant;

/**
 * 视图基类
 * @author 刘东奇
 * @date 2019/9/2
 */
public class BaseView {

    /**
     * id
     */
    protected Long id;

    /**
     *  视图对象构建的时间
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
