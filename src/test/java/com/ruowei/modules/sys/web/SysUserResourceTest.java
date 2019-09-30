package com.ruowei.modules.sys.web;

import com.ruowei.JhipsiteApp;
import com.ruowei.modules.sys.domain.SysCompany;
import com.ruowei.domain.enumeration.OfficeType;
import com.ruowei.modules.sys.domain.enumeration.PostType;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.domain.enumeration.GenderType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.pojo.SysOfficeDTO;
import com.ruowei.modules.sys.pojo.SysPostDTO;
import com.ruowei.modules.sys.pojo.SysUserEmployeeDTO;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.user.SysUserService;
import com.ruowei.service.mapper.SysCompanyMapper;
import com.ruowei.web.rest.TestUtil;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ruowei.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author 刘东奇
 * @date 2019/9/10
 */
@SpringBootTest(classes = JhipsiteApp.class)
class SysUserResourceTest {

    private static final String DEFAULT_USER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_USER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final GenderType DEFAULT_SEX = GenderType.MALE;
    private static final GenderType UPDATED_SEX = GenderType.FEMALE;

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    private static final String DEFAULT_SIGN = "AAAAAAAAAA";
    private static final String UPDATED_SIGN = "BBBBBBBBBB";

    private static final String DEFAULT_WX_OPENID = "AAAAAAAAAA";
    private static final String UPDATED_WX_OPENID = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_IMEI = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_IMEI = "BBBBBBBBBB";

    private static final UserType DEFAULT_USER_TYPE = UserType.EMPLOYEE;
    private static final UserType UPDATED_USER_TYPE = UserType.MEMBER;

    private static final String DEFAULT_REF_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REF_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REF_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REF_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_LOGIN_IP = "AAAAAAAAAA";
    private static final String UPDATED_LAST_LOGIN_IP = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_LOGIN_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_LOGIN_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_LAST_LOGIN_DATE = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_FREEZE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FREEZE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_FREEZE_DATE = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_FREEZE_CAUSE = "AAAAAAAAAA";
    private static final String UPDATED_FREEZE_CAUSE = "BBBBBBBBBB";

    private static final Integer DEFAULT_USER_SORT = 1;
    private static final Integer UPDATED_USER_SORT = 2;
    private static final Integer SMALLER_USER_SORT = 1 - 1;

    private static final UserStatusType DEFAULT_STATUS = UserStatusType.NORMAL;
    private static final UserStatusType UPDATED_STATUS = UserStatusType.DELETE;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_OFFICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_OFFICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SYS_COMPANY_ID = "AAAAAAAAAA";
    private static final String UPDATED_SYS_COMPANY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POST_NAME = "BBBBBBBBBB";

    private static final PostType DEFAULT_POST_TYPE = PostType.SENIOR;
    private static final PostType UPDATED_POST_TYPE = PostType.MIDDLE;

    private static final String DEFAULT_EMP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EMP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMP_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_EMP_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CODES = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CODES = "BBBBBBBBBB";

    private static final Integer DEFAULT_TREE_SORT = 1;
    private static final Integer UPDATED_TREE_SORT = 2;
    private static final Integer SMALLER_TREE_SORT = 1 - 1;

    private static final Integer DEFAULT_TREE_SORTS = 1;
    private static final Integer UPDATED_TREE_SORTS = 2;
    private static final Integer SMALLER_TREE_SORTS = 1 - 1;

    private static final Boolean DEFAULT_TREE_LEAF = false;
    private static final Boolean UPDATED_TREE_LEAF = true;

    private static final Integer DEFAULT_TREE_LEVEL = 1;
    private static final Integer UPDATED_TREE_LEVEL = 2;
    private static final Integer SMALLER_TREE_LEVEL = 1 - 1;

    private static final String DEFAULT_TREE_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_TREE_NAMES = "BBBBBBBBBB";

    private static final String DEFAULT_VIEW_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VIEW_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final OfficeType DEFAULT_OFFICE_TYPE = OfficeType.NATIONAL;
    private static final OfficeType UPDATED_OFFICE_TYPE = OfficeType.PROVINCIAL;

    private static final String DEFAULT_LEADER = "AAAAAAAAAA";
    private static final String UPDATED_LEADER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AREA_CODE = "BBBBBBBBBB";

    @Autowired
    private SysUserService sysUserService;
    private MockMvc restSysUserMockMvc;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
    @Autowired
    private ExceptionTranslator exceptionTranslator;
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
    @Autowired
    private Validator validator;

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private SysUserRepository sysUserRepository;

    private SysUser sysUser;
    private SysCompany sysCompany;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysUserResource sysUserResource = new SysUserResource(sysUserService);
        this.restSysUserMockMvc = MockMvcBuilders.standaloneSetup(sysUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    @BeforeEach
    public void initTest() {
        sysUser = createSysUserEntity(em);
    }

    public static SysUser createSysUserEntity(EntityManager em) {
        SysUser sysUser = new SysUser()
            .userCode(DEFAULT_USER_CODE)
            .loginCode(DEFAULT_LOGIN_CODE)
            .userName(DEFAULT_USER_NAME)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .phone(DEFAULT_PHONE)
            .sex(DEFAULT_SEX)
            .avatar(DEFAULT_AVATAR)
            .sign(DEFAULT_SIGN)
            .wxOpenid(DEFAULT_WX_OPENID)
            .mobileImei(DEFAULT_MOBILE_IMEI)
            .userType(DEFAULT_USER_TYPE)
            .refCode(DEFAULT_REF_CODE)
            .refName(DEFAULT_REF_NAME)
            .lastLoginIp(DEFAULT_LAST_LOGIN_IP)
            .lastLoginDate(DEFAULT_LAST_LOGIN_DATE)
            .freezeDate(DEFAULT_FREEZE_DATE)
            .freezeCause(DEFAULT_FREEZE_CAUSE)
            .userSort(DEFAULT_USER_SORT)
            .status(DEFAULT_STATUS)
            .remarks(DEFAULT_REMARKS);
        return sysUser;
    }

    public static SysOfficeDTO createSysOfficeDTO(EntityManager em) {
        SysOfficeDTO sysOfficeDTO = new SysOfficeDTO();
        sysOfficeDTO.setOfficeCode(DEFAULT_OFFICE_CODE);
        sysOfficeDTO.setParentCode(DEFAULT_PARENT_CODE);
        sysOfficeDTO.setParentCodes(DEFAULT_PARENT_CODES);
        sysOfficeDTO.setTreeNames(DEFAULT_TREE_NAMES);
        sysOfficeDTO.setViewCode(DEFAULT_VIEW_CODE);
        sysOfficeDTO.setOfficeName(DEFAULT_OFFICE_NAME);
        sysOfficeDTO.setAddress(DEFAULT_ADDRESS);
        return sysOfficeDTO;
    }

    public static SysPostDTO createSysPostDTO(EntityManager em) {
        SysPostDTO sysPostDTO = new SysPostDTO();
        sysPostDTO.setPostCode(DEFAULT_POST_CODE);
        sysPostDTO.setPostName(DEFAULT_POST_NAME);
        sysPostDTO.setPostType(DEFAULT_POST_TYPE);
        return sysPostDTO;
    }

    public static SysUserEmployeeDTO createSysUserEmployeeDTO(EntityManager em) {
        List<SysPostDTO> sysPostDTOList = new ArrayList<SysPostDTO>();
        sysPostDTOList.add(createSysPostDTO(em));
        Map<SysOfficeDTO, SysPostDTO> sysOfficePostMap =new HashMap<SysOfficeDTO, SysPostDTO>();
        sysOfficePostMap.put(createSysOfficeDTO(em),createSysPostDTO(em));

        SysUserEmployeeDTO sysUserEmployeeDTO = new SysUserEmployeeDTO();
        sysUserEmployeeDTO.setSysOfficeId(DEFAULT_SYS_OFFICE_ID);
        sysUserEmployeeDTO.setOfficeName(DEFAULT_COMPANY_NAME);
        sysUserEmployeeDTO.setSysCompanyId(DEFAULT_SYS_COMPANY_ID);
        sysUserEmployeeDTO.setCompanyName(DEFAULT_COMPANY_NAME);
        sysUserEmployeeDTO.setLoginCode(DEFAULT_LOGIN_CODE);
        sysUserEmployeeDTO.setUserName(DEFAULT_USER_NAME);
        sysUserEmployeeDTO.setEmail(DEFAULT_EMAIL);
        sysUserEmployeeDTO.setMobile(DEFAULT_MOBILE);
        sysUserEmployeeDTO.setPhone(DEFAULT_PHONE);
        sysUserEmployeeDTO.setUserSort(DEFAULT_USER_SORT);
        sysUserEmployeeDTO.setEmpCode(DEFAULT_EMP_CODE);
        sysUserEmployeeDTO.setEmpName(DEFAULT_EMP_NAME);
        sysUserEmployeeDTO.setEmpNameEn(DEFAULT_EMP_NAME_EN);
//        sysUserEmployeeDTO.setSysOfficePostMap(sysOfficePostMap);
//        sysUserEmployeeDTO.setSysPostDTOList(sysPostDTOList);
        return sysUserEmployeeDTO;
    }

    public static SysCompany createEntity(EntityManager em) {
        SysCompany sysCompany = new SysCompany()
            .companyCode(DEFAULT_COMPANY_CODE)
            .parentCode(DEFAULT_PARENT_CODE)
            .parentCodes(DEFAULT_PARENT_CODES)
            .treeSort(DEFAULT_TREE_SORT)
            .treeSorts(DEFAULT_TREE_SORTS)
            .treeLeaf(DEFAULT_TREE_LEAF)
            .treeLevel(DEFAULT_TREE_LEVEL)
            .treeNames(DEFAULT_TREE_NAMES)
            .viewCode(DEFAULT_VIEW_CODE)
            .companyName(DEFAULT_COMPANY_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .areaCode(DEFAULT_AREA_CODE)
            .remarks(DEFAULT_REMARKS);
        return sysCompany;
    }

    @Test
    @Transactional
    public void getAllSysUserEmployees() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList
        restSysUserMockMvc.perform(get("/api/sys-user-employees?sort=id,desc&userCode.specified=true&freezeDate.equals="+DEFAULT_FREEZE_DATE.toString()+"&userSort.lessThan=10"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].userCode").value(hasItem(DEFAULT_USER_CODE.toString())))
            .andExpect(jsonPath("$.[*].loginCode").value(hasItem(DEFAULT_LOGIN_CODE.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())));
    }

    @Test
    @Transactional
    public void createSysUserEmployee() throws Exception {
        int databaseSizeBeforeCreate = sysUserRepository.findAll().size();

        // Create the SysUserEmployee
        SysUserEmployeeDTO sysUserEmployeeDTO = createSysUserEmployeeDTO(em);
        System.out.println(new String(TestUtil.convertObjectToJsonBytes(sysUserEmployeeDTO)));
        restSysUserMockMvc.perform(post("/api/sys-user-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserEmployeeDTO)))
            .andExpect(status().isCreated());

        // Validate the SysEmployee in the database
//        List<Sy> sysEmployeeList = sysUserRepository.findAll();
//        assertThat(sysEmployeeList).hasSize(databaseSizeBeforeCreate + 1);
//        SysEmployee testSysEmployee = sysEmployeeList.get(sysEmployeeList.size() - 1);
//        assertThat(testSysEmployee.getEmpCode()).isEqualTo(DEFAULT_EMP_CODE);
//        assertThat(testSysEmployee.getEmpName()).isEqualTo(DEFAULT_EMP_NAME);
//        assertThat(testSysEmployee.getEmpNameEn()).isEqualTo(DEFAULT_EMP_NAME_EN);
//        assertThat(testSysEmployee.getSysOfficeId()).isEqualTo(DEFAULT_SYS_OFFICE_ID);
//        assertThat(testSysEmployee.getOfficeName()).isEqualTo(DEFAULT_OFFICE_NAME);
//        assertThat(testSysEmployee.getSysCompanyId()).isEqualTo(DEFAULT_SYS_COMPANY_ID);
//        assertThat(testSysEmployee.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
//        assertThat(testSysEmployee.getStatus()).isEqualTo(DEFAULT_STATUS);
//        assertThat(testSysEmployee.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }
}
