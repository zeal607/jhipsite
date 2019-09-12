package com.ruowei.modules.sys.web;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.enumeration.PostStatusType;
import com.ruowei.domain.enumeration.PostType;
import com.ruowei.modules.sys.domain.SysUser;
import com.ruowei.modules.sys.domain.enumeration.GenderType;
import com.ruowei.modules.sys.domain.enumeration.UserStatusType;
import com.ruowei.modules.sys.domain.enumeration.UserType;
import com.ruowei.modules.sys.repository.SysUserRepository;
import com.ruowei.modules.sys.service.SysUserQueryService;
import com.ruowei.web.rest.SysPostResource;
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

import static com.ruowei.web.rest.TestUtil.createFormattingConversionService;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private SysUserQueryService sysUserQueryService;
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
    private SysUserRepository sysUserRepository;

    private SysUser sysUser;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysUserResource sysUserResource = new SysUserResource(sysUserQueryService);
        this.restSysUserMockMvc = MockMvcBuilders.standaloneSetup(sysUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    @BeforeEach
    public void initTest() {
        sysUser = createEntity(em);
    }

    public static SysUser createEntity(EntityManager em) {
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

    @Test
    @Transactional
    public void getAllSysUsers() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList
        restSysUserMockMvc.perform(get("/api/sys-users?sort=id,desc&userCode.specified=true&freezeDate.equals="+DEFAULT_FREEZE_DATE.toString()+"&userSort.lessThan=10&status.equals="+DEFAULT_STATUS.toString()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUser.getId().longValue())))
            .andExpect(jsonPath("$.[*].userCode").value(hasItem(DEFAULT_USER_CODE.toString())))
            .andExpect(jsonPath("$.[*].loginCode").value(hasItem(DEFAULT_LOGIN_CODE.toString())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())))
            .andExpect(jsonPath("$.[*].sign").value(hasItem(DEFAULT_SIGN.toString())))
            .andExpect(jsonPath("$.[*].wxOpenid").value(hasItem(DEFAULT_WX_OPENID.toString())))
            .andExpect(jsonPath("$.[*].mobileImei").value(hasItem(DEFAULT_MOBILE_IMEI.toString())))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].refCode").value(hasItem(DEFAULT_REF_CODE.toString())))
            .andExpect(jsonPath("$.[*].refName").value(hasItem(DEFAULT_REF_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastLoginIp").value(hasItem(DEFAULT_LAST_LOGIN_IP.toString())))
            .andExpect(jsonPath("$.[*].lastLoginDate").value(hasItem(DEFAULT_LAST_LOGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].freezeDate").value(hasItem(DEFAULT_FREEZE_DATE.toString())))
            .andExpect(jsonPath("$.[*].freezeCause").value(hasItem(DEFAULT_FREEZE_CAUSE.toString())))
            .andExpect(jsonPath("$.[*].userSort").value(hasItem(DEFAULT_USER_SORT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }
}
