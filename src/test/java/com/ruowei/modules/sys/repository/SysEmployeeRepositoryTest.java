package com.ruowei.modules.sys.repository;


import com.ruowei.modules.sys.domain.enumeration.EmployeeStatusType;
import com.ruowei.modules.sys.domain.table.SysEmployee;
import com.ruowei.modules.sys.domain.table.SysOffice;
import com.ruowei.modules.sys.domain.table.SysPost;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;


/**
 * @author 刘东奇
 * @date 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SysEmployeeRepositoryTest {

    @Autowired
    private SysEmployeeRepository sysEmployeeRepository;

    @Test
    void testSaveEmployee(){
        SysEmployee sysEmployee = new SysEmployee();
        sysEmployee.setEmpName("测试");
        sysEmployee.setStatus(EmployeeStatusType.NORMAL);
        sysEmployee.setEmpCode("asdsad");
        HashMap<SysOffice, SysPost> sysOfficeSysPostHashMap =new HashMap<SysOffice, SysPost>();
        SysOffice sysOffice = new SysOffice();
        sysOffice.setId(1l);
        SysPost sysPost = new SysPost();
        sysPost.setId(2l);
        sysOfficeSysPostHashMap.put(sysOffice,sysPost);
        sysEmployee.setSysOfficeSysPostMap(sysOfficeSysPostHashMap);
        sysEmployeeRepository.save(sysEmployee);
    }

}
