package com.ruowei.modules.sys.api;

import com.ruowei.common.pojo.TreeDTO;

/**
 * 机构
 * @author 刘东奇
 * @date 2019/9/30
 */
public interface SysOfficeApi {

    /**
     * 获取机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     * @return TreeDTO
     */
    TreeDTO getOfficeTree();
}
