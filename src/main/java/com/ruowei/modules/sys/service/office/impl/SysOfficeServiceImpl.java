package com.ruowei.modules.sys.service.office.impl;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataInvalidException;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.common.pojo.TreeDTO;
import com.ruowei.common.service.CrudBaseService;
import com.ruowei.modules.sys.domain.SysOffice;

import com.ruowei.modules.sys.mapper.SysOfficeMapper;
import com.ruowei.modules.sys.mapper.SysUserRoleMapper;
import com.ruowei.modules.sys.repository.SysOfficeRepository;
import com.ruowei.modules.sys.service.office.SysOfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 刘东奇
 */
@Service
@Transactional
public class SysOfficeServiceImpl
    extends CrudBaseService<SysOffice, Long, SysOfficeRepository>
    implements SysOfficeService {

    private final Logger log = LoggerFactory.getLogger(SysOfficeServiceImpl.class);
    private final static String OFFICE_ROOT_NOT_FOUND = "机构树的根节点不能为空";

    /**
     * mapper依赖
     */
    private final SysOfficeMapper sysOfficeMapper;

    public SysOfficeServiceImpl(SysOfficeMapper sysOfficeMapper){
        this.sysOfficeMapper = sysOfficeMapper;
    }

    /**
     * 获取机构树
     *
     * @return TreeDTO
     * @author 刘东奇
     * @date 2019/9/30
     */
    @Override
    public TreeDTO getOfficeTree() {
        return getOfficeTreeByRecursiveQuery();
    }

    /**
     * 通过递归获取机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     */
    public TreeDTO getOfficeTreeByRecursiveQuery() {
        SysOffice root = this.jpaRepository.findFirstByParentCodeIsNullOrderByTreeSortAsc();
        if(root != null){
            TreeDTO result = recursiveQuerySysOffice(root);
            return result;
        }else{
            throw new DataInvalidException(OFFICE_ROOT_NOT_FOUND);
        }

    }

    /**
     * 递归访问数据库，构建机构树
     * @author 刘东奇
     * @date 2019/9/30
     * @param root
     */
    private TreeDTO recursiveQuerySysOffice(SysOffice root){
        TreeDTO result = sysOfficeMapper.toTreeDTO(root);
        if(!root.isTreeLeaf()){
            List<SysOffice> sysOfficeList = this.jpaRepository.findAllByParentCodeOrderByTreeSortAsc(root.getParentCode());
            List<TreeDTO> children = new ArrayList<TreeDTO>();
            if(sysOfficeList!=null){
                for(SysOffice sysOffice: sysOfficeList){
                    children.add(recursiveQuerySysOffice(sysOffice));
                }
            }
        }
        return result;
    }

    /**
     * 通过循环获取机构树
     * 依赖SysOffice的TreeSorts字段
     * @author 刘东奇
     * @date 2019/9/30
     * @param
     */
    public TreeDTO getOfficeTreeByOneQuery(){
        List<SysOffice> sysOfficeList = this.jpaRepository.findAllOrderByTreeSortsAsc();
        if(sysOfficeList == null){
            throw new DataInvalidException(OFFICE_ROOT_NOT_FOUND);
        }
        SysOffice root = sysOfficeList.remove(0);
        TreeDTO result = sysOfficeMapper.toTreeDTO(root);
        HashMap<String,List<TreeDTO>> childrenMap = new HashMap<String,List<TreeDTO>>();
        for(SysOffice sysOffice:sysOfficeList){
            List<TreeDTO> list = childrenMap.get(sysOffice.getParentCode());
            if(list == null){
                list = new ArrayList<TreeDTO>();
                childrenMap.put(sysOffice.getParentCode(),list);
            }
            list.add(sysOfficeMapper.toTreeDTO(sysOffice));
        }
        recursiveTree(result,childrenMap);
        return result;
    }

    /**
     * 递归树
     * @author 刘东奇
     * @date 2019/9/30
     * @param node
    * @param childrenMap
     */
    private void recursiveTree(TreeDTO node,HashMap<String,List<TreeDTO>> childrenMap){
        List<TreeDTO> list = childrenMap.get(node.getCode());
        node.setChildrenList(list);
        if(list == null){
            //说明本节点是叶子节点
            return;
        }else{
            for(TreeDTO treeDTO:list){
                recursiveTree(treeDTO,childrenMap);
            }
        }
    }
}
