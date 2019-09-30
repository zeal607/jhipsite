package com.ruowei.common.pojo;

import java.util.List;

/**
 * 树形数据结构
 * @author 刘东奇
 * @date 2019/9/30
 */
public class TreeDTO {
    /**
     * 自身ID
     */
    private String id;
    /**
     * 自身编码
     */
    private String code;
    /**
     * 父ID
     */
    private String pid;
    /**
     * 父编码
     */
    private String parentCode;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 子节点
     */
    private List<TreeDTO> childrenList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeDTO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<TreeDTO> childrenList) {
        this.childrenList = childrenList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String toString() {
        return "TreeDTO{" +
            "id='" + id + '\'' +
            ", code='" + code + '\'' +
            ", pid='" + pid + '\'' +
            ", parentCode='" + parentCode + '\'' +
            ", name='" + name + '\'' +
            ", childrenList=" + childrenList +
            '}';
    }
}
