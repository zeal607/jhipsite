package com.ruowei.modules.sys.repository;

import com.ruowei.common.repository.BaseRepository;
import com.ruowei.modules.sys.domain.table.QSysOffice;
import com.ruowei.modules.sys.domain.table.SysOffice;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Size;
import java.util.List;


/**
 * Spring Data  repository for the SysOffice entity.
 * @author 刘东奇
 */
@SuppressWarnings("unused")
@Repository
public interface SysOfficeRepository
    extends BaseRepository<Long, SysOffice, QSysOffice> {
    /**
     * 查询机构树根节点
     * @author 刘东奇
     * @date 2019/9/30
     * @return SysOffice
     */
    SysOffice findFirstByParentCodeIsNullOrderByTreeSortAsc();

    /**
     * 根据父节点查询下一级别所有子节点
     * @author 刘东奇
     * @date 2019/9/30
     * @param parentCode
     * @return
     */
    List<SysOffice> findAllByParentCodeOrderByTreeSortAsc(String parentCode);

    /**
     * 查所有，按所有级别排序
     * @author 刘东奇
     * @date 2019/9/30
     * @return
     */
    List<SysOffice> findAllByOrderByTreeSortsAsc();

    /**
     * 根据父节点编码，查询所有级别子节点，按所有级别排序
     * @author 刘东奇
     * @date 2019/9/30
     * @param parentCodes 父节点编码
     * @return
     */
    List<SysOffice> findAllByParentCodesLikeOrderByTreeSortsAsc(@Size(max = 1000) String parentCodes);
}
