package com.ruowei.modules.sys.repository;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author 刘东奇
 * @date 2019/11/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SysEmployeeTableTableRepositoryTest {

    @Autowired
    private SysEmployeeTableRepository sysEmployeeTableRepository;

    @Test
    void testSaveEmployee(){
    }

}
