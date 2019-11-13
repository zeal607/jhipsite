package com.ruowei.common.json;

import java.io.Serializable;

/**
 * @author 刘东奇
 * @date 2019/11/13
 */
public class SysOfficeSysPostJson implements Serializable {
    String sysOfficeId;
    String sysPostId;

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public String getSysPostId() {
        return sysPostId;
    }

    public void setSysPostId(String sysPostId) {
        this.sysPostId = sysPostId;
    }
}
