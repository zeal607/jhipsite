package com.ruowei.modules.sys.service.alpha;

import com.ruowei.common.error.ErrorMessageUtils;
import com.ruowei.common.error.exception.DataNotFoundException;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.repository.SysOfficeRepository;
import com.ruowei.modules.sys.service.api.SysOfficeApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author 刘东奇
 * @date 2019/11/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysOfficeService implements SysOfficeApi {

    private final SysOfficeRepository sysOfficeRepository;

    public SysOfficeService(SysOfficeRepository sysOfficeRepository) {
        this.sysOfficeRepository = sysOfficeRepository;
    }

    /**
     * 判断是否存在机构
     * 如果存在，返回机构编码；如果不存在，抛异常
     *
     * @param id
     * @return officeCode
     * @author 刘东奇
     * @date 2019/11/2
     */
    @Override
    public SysOffice checkOfficeExistsById(Long id) {
        Assert.notNull(id,"机构ID不能为空");
        Optional<SysOffice> sysOffice = sysOfficeRepository.findOne(id);
        Assert.isTrue(sysOffice.isPresent(),"机构不存在");
        return sysOffice.get();
    }
}
