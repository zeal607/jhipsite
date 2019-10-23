package com.ruowei.modules.sys.domain;

import com.ruowei.common.entity.BaseEntity;
import com.ruowei.modules.sys.domain.enumeration.ControlType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户数据权限表
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user_data_scope")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysUserDataScope extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 控制用户ID
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "sys_user_id", length = 100, nullable = false)
    private String sysUserId;

    /**
     * 控制类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ctrl_type", nullable = false)
    private ControlType ctrlType;

    /**
     * 控制数据，如控制类型为公司，那么这里就是公司编号
     */
    @Size(max = 100)
    @Column(name = "ctrl_data", length = 100)
    private String ctrlData;

    /**
     * 控制权限 ???????????????????
     */
    @Size(max = 100)
    @Column(name = "ctrl_permi", length = 100)
    private String ctrlPermi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public String getSysUserId() {
        return sysUserId;
    }

    public SysUserDataScope sysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
        return this;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public ControlType getCtrlType() {
        return ctrlType;
    }

    public SysUserDataScope ctrlType(ControlType ctrlType) {
        this.ctrlType = ctrlType;
        return this;
    }

    public void setCtrlType(ControlType ctrlType) {
        this.ctrlType = ctrlType;
    }

    public String getCtrlData() {
        return ctrlData;
    }

    public SysUserDataScope ctrlData(String ctrlData) {
        this.ctrlData = ctrlData;
        return this;
    }

    public void setCtrlData(String ctrlData) {
        this.ctrlData = ctrlData;
    }

    public String getCtrlPermi() {
        return ctrlPermi;
    }

    public SysUserDataScope ctrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
        return this;
    }

    public void setCtrlPermi(String ctrlPermi) {
        this.ctrlPermi = ctrlPermi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysUserDataScope)) {
            return false;
        }
        return id != null && id.equals(((SysUserDataScope) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysUserDataScope{" +
            "id=" + getId() +
            ", sysUserId='" + getSysUserId() + "'" +
            ", ctrlType='" + getCtrlType() + "'" +
            ", ctrlData='" + getCtrlData() + "'" +
            ", ctrlPermi='" + getCtrlPermi() + "'" +
            "}";
    }
}
