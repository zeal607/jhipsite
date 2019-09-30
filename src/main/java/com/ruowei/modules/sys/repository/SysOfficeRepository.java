package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.SysEmployee;
import com.ruowei.modules.sys.domain.SysOffice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysOffice entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysOfficeRepository
    extends BaseRepository<SysOffice, Long> {
    /**
     * 查询机构树根节点
     * @author 刘东奇
     * @date 2019/9/30
     * @return
     */
    SysOffice findFirstByParentCodeIsNullOrderByTreeSortAsc();

    /**
     * 根据父节点查询机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param parentCode
     */
    List<SysOffice> findAllByParentCodeOrderByTreeSortAsc(String parentCode);

    /**
     * 根据所有级别排序
     * @author 刘东奇
     * @date 2019/9/30
     */
    List<SysOffice> findAllOrderByTreeSortsAsc();
}
