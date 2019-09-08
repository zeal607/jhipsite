package com.ruowei.web.rest;

import com.ruowei.JhipsiteApp;
import com.ruowei.domain.SysUser;
import com.ruowei.repository.SysUserRepository;
import com.ruowei.service.SysUserService;
import com.ruowei.service.dto.SysUserDTO;
import com.ruowei.service.mapper.SysUserMapper;
import com.ruowei.web.rest.errors.ExceptionTranslator;
import com.ruowei.service.dto.SysUserCriteria;
import com.ruowei.service.SysUserQueryService;

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
import java.util.List;

import static com.ruowei.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ruowei.domain.enumeration.GenderType;
import com.ruowei.domain.enumeration.UserType;
import com.ruowei.domain.enumeration.UserStatusType;
/**
 * Integration tests for the {@link SysUserResource} REST controller.
 */
@SpringBootTest(classes = JhipsiteApp.class)
public class SysUserResourceIT {

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
    private SysUserRepository sysUserRepository;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserQueryService sysUserQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSysUserMockMvc;

    private SysUser sysUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SysUserResource sysUserResource = new SysUserResource(sysUserService, sysUserQueryService);
        this.restSysUserMockMvc = MockMvcBuilders.standaloneSetup(sysUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
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
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SysUser createUpdatedEntity(EntityManager em) {
        SysUser sysUser = new SysUser()
            .userCode(UPDATED_USER_CODE)
            .loginCode(UPDATED_LOGIN_CODE)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .phone(UPDATED_PHONE)
            .sex(UPDATED_SEX)
            .avatar(UPDATED_AVATAR)
            .sign(UPDATED_SIGN)
            .wxOpenid(UPDATED_WX_OPENID)
            .mobileImei(UPDATED_MOBILE_IMEI)
            .userType(UPDATED_USER_TYPE)
            .refCode(UPDATED_REF_CODE)
            .refName(UPDATED_REF_NAME)
            .lastLoginIp(UPDATED_LAST_LOGIN_IP)
            .lastLoginDate(UPDATED_LAST_LOGIN_DATE)
            .freezeDate(UPDATED_FREEZE_DATE)
            .freezeCause(UPDATED_FREEZE_CAUSE)
            .userSort(UPDATED_USER_SORT)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        return sysUser;
    }

    @BeforeEach
    public void initTest() {
        sysUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysUser() throws Exception {
        int databaseSizeBeforeCreate = sysUserRepository.findAll().size();

        // Create the SysUser
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);
        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isCreated());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeCreate + 1);
        SysUser testSysUser = sysUserList.get(sysUserList.size() - 1);
        assertThat(testSysUser.getUserCode()).isEqualTo(DEFAULT_USER_CODE);
        assertThat(testSysUser.getLoginCode()).isEqualTo(DEFAULT_LOGIN_CODE);
        assertThat(testSysUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testSysUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testSysUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSysUser.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testSysUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSysUser.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testSysUser.getAvatar()).isEqualTo(DEFAULT_AVATAR);
        assertThat(testSysUser.getSign()).isEqualTo(DEFAULT_SIGN);
        assertThat(testSysUser.getWxOpenid()).isEqualTo(DEFAULT_WX_OPENID);
        assertThat(testSysUser.getMobileImei()).isEqualTo(DEFAULT_MOBILE_IMEI);
        assertThat(testSysUser.getUserType()).isEqualTo(DEFAULT_USER_TYPE);
        assertThat(testSysUser.getRefCode()).isEqualTo(DEFAULT_REF_CODE);
        assertThat(testSysUser.getRefName()).isEqualTo(DEFAULT_REF_NAME);
        assertThat(testSysUser.getLastLoginIp()).isEqualTo(DEFAULT_LAST_LOGIN_IP);
        assertThat(testSysUser.getLastLoginDate()).isEqualTo(DEFAULT_LAST_LOGIN_DATE);
        assertThat(testSysUser.getFreezeDate()).isEqualTo(DEFAULT_FREEZE_DATE);
        assertThat(testSysUser.getFreezeCause()).isEqualTo(DEFAULT_FREEZE_CAUSE);
        assertThat(testSysUser.getUserSort()).isEqualTo(DEFAULT_USER_SORT);
        assertThat(testSysUser.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSysUser.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createSysUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysUserRepository.findAll().size();

        // Create the SysUser with an existing ID
        sysUser.setId(1L);
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkUserCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserRepository.findAll().size();
        // set the field null
        sysUser.setUserCode(null);

        // Create the SysUser, which fails.
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);

        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isBadRequest());

        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoginCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserRepository.findAll().size();
        // set the field null
        sysUser.setLoginCode(null);

        // Create the SysUser, which fails.
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);

        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isBadRequest());

        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserRepository.findAll().size();
        // set the field null
        sysUser.setPassword(null);

        // Create the SysUser, which fails.
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);

        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isBadRequest());

        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysUserRepository.findAll().size();
        // set the field null
        sysUser.setStatus(null);

        // Create the SysUser, which fails.
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);

        restSysUserMockMvc.perform(post("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isBadRequest());

        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysUsers() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList
        restSysUserMockMvc.perform(get("/api/sys-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUser.getId().intValue())))
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
    
    @Test
    @Transactional
    public void getSysUser() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get the sysUser
        restSysUserMockMvc.perform(get("/api/sys-users/{id}", sysUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysUser.getId().intValue()))
            .andExpect(jsonPath("$.userCode").value(DEFAULT_USER_CODE.toString()))
            .andExpect(jsonPath("$.loginCode").value(DEFAULT_LOGIN_CODE.toString()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()))
            .andExpect(jsonPath("$.sign").value(DEFAULT_SIGN.toString()))
            .andExpect(jsonPath("$.wxOpenid").value(DEFAULT_WX_OPENID.toString()))
            .andExpect(jsonPath("$.mobileImei").value(DEFAULT_MOBILE_IMEI.toString()))
            .andExpect(jsonPath("$.userType").value(DEFAULT_USER_TYPE.toString()))
            .andExpect(jsonPath("$.refCode").value(DEFAULT_REF_CODE.toString()))
            .andExpect(jsonPath("$.refName").value(DEFAULT_REF_NAME.toString()))
            .andExpect(jsonPath("$.lastLoginIp").value(DEFAULT_LAST_LOGIN_IP.toString()))
            .andExpect(jsonPath("$.lastLoginDate").value(DEFAULT_LAST_LOGIN_DATE.toString()))
            .andExpect(jsonPath("$.freezeDate").value(DEFAULT_FREEZE_DATE.toString()))
            .andExpect(jsonPath("$.freezeCause").value(DEFAULT_FREEZE_CAUSE.toString()))
            .andExpect(jsonPath("$.userSort").value(DEFAULT_USER_SORT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userCode equals to DEFAULT_USER_CODE
        defaultSysUserShouldBeFound("userCode.equals=" + DEFAULT_USER_CODE);

        // Get all the sysUserList where userCode equals to UPDATED_USER_CODE
        defaultSysUserShouldNotBeFound("userCode.equals=" + UPDATED_USER_CODE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userCode in DEFAULT_USER_CODE or UPDATED_USER_CODE
        defaultSysUserShouldBeFound("userCode.in=" + DEFAULT_USER_CODE + "," + UPDATED_USER_CODE);

        // Get all the sysUserList where userCode equals to UPDATED_USER_CODE
        defaultSysUserShouldNotBeFound("userCode.in=" + UPDATED_USER_CODE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userCode is not null
        defaultSysUserShouldBeFound("userCode.specified=true");

        // Get all the sysUserList where userCode is null
        defaultSysUserShouldNotBeFound("userCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByLoginCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where loginCode equals to DEFAULT_LOGIN_CODE
        defaultSysUserShouldBeFound("loginCode.equals=" + DEFAULT_LOGIN_CODE);

        // Get all the sysUserList where loginCode equals to UPDATED_LOGIN_CODE
        defaultSysUserShouldNotBeFound("loginCode.equals=" + UPDATED_LOGIN_CODE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByLoginCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where loginCode in DEFAULT_LOGIN_CODE or UPDATED_LOGIN_CODE
        defaultSysUserShouldBeFound("loginCode.in=" + DEFAULT_LOGIN_CODE + "," + UPDATED_LOGIN_CODE);

        // Get all the sysUserList where loginCode equals to UPDATED_LOGIN_CODE
        defaultSysUserShouldNotBeFound("loginCode.in=" + UPDATED_LOGIN_CODE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByLoginCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where loginCode is not null
        defaultSysUserShouldBeFound("loginCode.specified=true");

        // Get all the sysUserList where loginCode is null
        defaultSysUserShouldNotBeFound("loginCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userName equals to DEFAULT_USER_NAME
        defaultSysUserShouldBeFound("userName.equals=" + DEFAULT_USER_NAME);

        // Get all the sysUserList where userName equals to UPDATED_USER_NAME
        defaultSysUserShouldNotBeFound("userName.equals=" + UPDATED_USER_NAME);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userName in DEFAULT_USER_NAME or UPDATED_USER_NAME
        defaultSysUserShouldBeFound("userName.in=" + DEFAULT_USER_NAME + "," + UPDATED_USER_NAME);

        // Get all the sysUserList where userName equals to UPDATED_USER_NAME
        defaultSysUserShouldNotBeFound("userName.in=" + UPDATED_USER_NAME);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userName is not null
        defaultSysUserShouldBeFound("userName.specified=true");

        // Get all the sysUserList where userName is null
        defaultSysUserShouldNotBeFound("userName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByPasswordIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where password equals to DEFAULT_PASSWORD
        defaultSysUserShouldBeFound("password.equals=" + DEFAULT_PASSWORD);

        // Get all the sysUserList where password equals to UPDATED_PASSWORD
        defaultSysUserShouldNotBeFound("password.equals=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllSysUsersByPasswordIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where password in DEFAULT_PASSWORD or UPDATED_PASSWORD
        defaultSysUserShouldBeFound("password.in=" + DEFAULT_PASSWORD + "," + UPDATED_PASSWORD);

        // Get all the sysUserList where password equals to UPDATED_PASSWORD
        defaultSysUserShouldNotBeFound("password.in=" + UPDATED_PASSWORD);
    }

    @Test
    @Transactional
    public void getAllSysUsersByPasswordIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where password is not null
        defaultSysUserShouldBeFound("password.specified=true");

        // Get all the sysUserList where password is null
        defaultSysUserShouldNotBeFound("password.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where email equals to DEFAULT_EMAIL
        defaultSysUserShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the sysUserList where email equals to UPDATED_EMAIL
        defaultSysUserShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSysUsersByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultSysUserShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the sysUserList where email equals to UPDATED_EMAIL
        defaultSysUserShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSysUsersByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where email is not null
        defaultSysUserShouldBeFound("email.specified=true");

        // Get all the sysUserList where email is null
        defaultSysUserShouldNotBeFound("email.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByMobileIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where mobile equals to DEFAULT_MOBILE
        defaultSysUserShouldBeFound("mobile.equals=" + DEFAULT_MOBILE);

        // Get all the sysUserList where mobile equals to UPDATED_MOBILE
        defaultSysUserShouldNotBeFound("mobile.equals=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByMobileIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where mobile in DEFAULT_MOBILE or UPDATED_MOBILE
        defaultSysUserShouldBeFound("mobile.in=" + DEFAULT_MOBILE + "," + UPDATED_MOBILE);

        // Get all the sysUserList where mobile equals to UPDATED_MOBILE
        defaultSysUserShouldNotBeFound("mobile.in=" + UPDATED_MOBILE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByMobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where mobile is not null
        defaultSysUserShouldBeFound("mobile.specified=true");

        // Get all the sysUserList where mobile is null
        defaultSysUserShouldNotBeFound("mobile.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByPhoneIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where phone equals to DEFAULT_PHONE
        defaultSysUserShouldBeFound("phone.equals=" + DEFAULT_PHONE);

        // Get all the sysUserList where phone equals to UPDATED_PHONE
        defaultSysUserShouldNotBeFound("phone.equals=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByPhoneIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where phone in DEFAULT_PHONE or UPDATED_PHONE
        defaultSysUserShouldBeFound("phone.in=" + DEFAULT_PHONE + "," + UPDATED_PHONE);

        // Get all the sysUserList where phone equals to UPDATED_PHONE
        defaultSysUserShouldNotBeFound("phone.in=" + UPDATED_PHONE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByPhoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where phone is not null
        defaultSysUserShouldBeFound("phone.specified=true");

        // Get all the sysUserList where phone is null
        defaultSysUserShouldNotBeFound("phone.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersBySexIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where sex equals to DEFAULT_SEX
        defaultSysUserShouldBeFound("sex.equals=" + DEFAULT_SEX);

        // Get all the sysUserList where sex equals to UPDATED_SEX
        defaultSysUserShouldNotBeFound("sex.equals=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllSysUsersBySexIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where sex in DEFAULT_SEX or UPDATED_SEX
        defaultSysUserShouldBeFound("sex.in=" + DEFAULT_SEX + "," + UPDATED_SEX);

        // Get all the sysUserList where sex equals to UPDATED_SEX
        defaultSysUserShouldNotBeFound("sex.in=" + UPDATED_SEX);
    }

    @Test
    @Transactional
    public void getAllSysUsersBySexIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where sex is not null
        defaultSysUserShouldBeFound("sex.specified=true");

        // Get all the sysUserList where sex is null
        defaultSysUserShouldNotBeFound("sex.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByAvatarIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where avatar equals to DEFAULT_AVATAR
        defaultSysUserShouldBeFound("avatar.equals=" + DEFAULT_AVATAR);

        // Get all the sysUserList where avatar equals to UPDATED_AVATAR
        defaultSysUserShouldNotBeFound("avatar.equals=" + UPDATED_AVATAR);
    }

    @Test
    @Transactional
    public void getAllSysUsersByAvatarIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where avatar in DEFAULT_AVATAR or UPDATED_AVATAR
        defaultSysUserShouldBeFound("avatar.in=" + DEFAULT_AVATAR + "," + UPDATED_AVATAR);

        // Get all the sysUserList where avatar equals to UPDATED_AVATAR
        defaultSysUserShouldNotBeFound("avatar.in=" + UPDATED_AVATAR);
    }

    @Test
    @Transactional
    public void getAllSysUsersByAvatarIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where avatar is not null
        defaultSysUserShouldBeFound("avatar.specified=true");

        // Get all the sysUserList where avatar is null
        defaultSysUserShouldNotBeFound("avatar.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersBySignIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where sign equals to DEFAULT_SIGN
        defaultSysUserShouldBeFound("sign.equals=" + DEFAULT_SIGN);

        // Get all the sysUserList where sign equals to UPDATED_SIGN
        defaultSysUserShouldNotBeFound("sign.equals=" + UPDATED_SIGN);
    }

    @Test
    @Transactional
    public void getAllSysUsersBySignIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where sign in DEFAULT_SIGN or UPDATED_SIGN
        defaultSysUserShouldBeFound("sign.in=" + DEFAULT_SIGN + "," + UPDATED_SIGN);

        // Get all the sysUserList where sign equals to UPDATED_SIGN
        defaultSysUserShouldNotBeFound("sign.in=" + UPDATED_SIGN);
    }

    @Test
    @Transactional
    public void getAllSysUsersBySignIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where sign is not null
        defaultSysUserShouldBeFound("sign.specified=true");

        // Get all the sysUserList where sign is null
        defaultSysUserShouldNotBeFound("sign.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByWxOpenidIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where wxOpenid equals to DEFAULT_WX_OPENID
        defaultSysUserShouldBeFound("wxOpenid.equals=" + DEFAULT_WX_OPENID);

        // Get all the sysUserList where wxOpenid equals to UPDATED_WX_OPENID
        defaultSysUserShouldNotBeFound("wxOpenid.equals=" + UPDATED_WX_OPENID);
    }

    @Test
    @Transactional
    public void getAllSysUsersByWxOpenidIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where wxOpenid in DEFAULT_WX_OPENID or UPDATED_WX_OPENID
        defaultSysUserShouldBeFound("wxOpenid.in=" + DEFAULT_WX_OPENID + "," + UPDATED_WX_OPENID);

        // Get all the sysUserList where wxOpenid equals to UPDATED_WX_OPENID
        defaultSysUserShouldNotBeFound("wxOpenid.in=" + UPDATED_WX_OPENID);
    }

    @Test
    @Transactional
    public void getAllSysUsersByWxOpenidIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where wxOpenid is not null
        defaultSysUserShouldBeFound("wxOpenid.specified=true");

        // Get all the sysUserList where wxOpenid is null
        defaultSysUserShouldNotBeFound("wxOpenid.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByMobileImeiIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where mobileImei equals to DEFAULT_MOBILE_IMEI
        defaultSysUserShouldBeFound("mobileImei.equals=" + DEFAULT_MOBILE_IMEI);

        // Get all the sysUserList where mobileImei equals to UPDATED_MOBILE_IMEI
        defaultSysUserShouldNotBeFound("mobileImei.equals=" + UPDATED_MOBILE_IMEI);
    }

    @Test
    @Transactional
    public void getAllSysUsersByMobileImeiIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where mobileImei in DEFAULT_MOBILE_IMEI or UPDATED_MOBILE_IMEI
        defaultSysUserShouldBeFound("mobileImei.in=" + DEFAULT_MOBILE_IMEI + "," + UPDATED_MOBILE_IMEI);

        // Get all the sysUserList where mobileImei equals to UPDATED_MOBILE_IMEI
        defaultSysUserShouldNotBeFound("mobileImei.in=" + UPDATED_MOBILE_IMEI);
    }

    @Test
    @Transactional
    public void getAllSysUsersByMobileImeiIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where mobileImei is not null
        defaultSysUserShouldBeFound("mobileImei.specified=true");

        // Get all the sysUserList where mobileImei is null
        defaultSysUserShouldNotBeFound("mobileImei.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userType equals to DEFAULT_USER_TYPE
        defaultSysUserShouldBeFound("userType.equals=" + DEFAULT_USER_TYPE);

        // Get all the sysUserList where userType equals to UPDATED_USER_TYPE
        defaultSysUserShouldNotBeFound("userType.equals=" + UPDATED_USER_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserTypeIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userType in DEFAULT_USER_TYPE or UPDATED_USER_TYPE
        defaultSysUserShouldBeFound("userType.in=" + DEFAULT_USER_TYPE + "," + UPDATED_USER_TYPE);

        // Get all the sysUserList where userType equals to UPDATED_USER_TYPE
        defaultSysUserShouldNotBeFound("userType.in=" + UPDATED_USER_TYPE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userType is not null
        defaultSysUserShouldBeFound("userType.specified=true");

        // Get all the sysUserList where userType is null
        defaultSysUserShouldNotBeFound("userType.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByRefCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where refCode equals to DEFAULT_REF_CODE
        defaultSysUserShouldBeFound("refCode.equals=" + DEFAULT_REF_CODE);

        // Get all the sysUserList where refCode equals to UPDATED_REF_CODE
        defaultSysUserShouldNotBeFound("refCode.equals=" + UPDATED_REF_CODE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByRefCodeIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where refCode in DEFAULT_REF_CODE or UPDATED_REF_CODE
        defaultSysUserShouldBeFound("refCode.in=" + DEFAULT_REF_CODE + "," + UPDATED_REF_CODE);

        // Get all the sysUserList where refCode equals to UPDATED_REF_CODE
        defaultSysUserShouldNotBeFound("refCode.in=" + UPDATED_REF_CODE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByRefCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where refCode is not null
        defaultSysUserShouldBeFound("refCode.specified=true");

        // Get all the sysUserList where refCode is null
        defaultSysUserShouldNotBeFound("refCode.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByRefNameIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where refName equals to DEFAULT_REF_NAME
        defaultSysUserShouldBeFound("refName.equals=" + DEFAULT_REF_NAME);

        // Get all the sysUserList where refName equals to UPDATED_REF_NAME
        defaultSysUserShouldNotBeFound("refName.equals=" + UPDATED_REF_NAME);
    }

    @Test
    @Transactional
    public void getAllSysUsersByRefNameIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where refName in DEFAULT_REF_NAME or UPDATED_REF_NAME
        defaultSysUserShouldBeFound("refName.in=" + DEFAULT_REF_NAME + "," + UPDATED_REF_NAME);

        // Get all the sysUserList where refName equals to UPDATED_REF_NAME
        defaultSysUserShouldNotBeFound("refName.in=" + UPDATED_REF_NAME);
    }

    @Test
    @Transactional
    public void getAllSysUsersByRefNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where refName is not null
        defaultSysUserShouldBeFound("refName.specified=true");

        // Get all the sysUserList where refName is null
        defaultSysUserShouldNotBeFound("refName.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByLastLoginIpIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where lastLoginIp equals to DEFAULT_LAST_LOGIN_IP
        defaultSysUserShouldBeFound("lastLoginIp.equals=" + DEFAULT_LAST_LOGIN_IP);

        // Get all the sysUserList where lastLoginIp equals to UPDATED_LAST_LOGIN_IP
        defaultSysUserShouldNotBeFound("lastLoginIp.equals=" + UPDATED_LAST_LOGIN_IP);
    }

    @Test
    @Transactional
    public void getAllSysUsersByLastLoginIpIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where lastLoginIp in DEFAULT_LAST_LOGIN_IP or UPDATED_LAST_LOGIN_IP
        defaultSysUserShouldBeFound("lastLoginIp.in=" + DEFAULT_LAST_LOGIN_IP + "," + UPDATED_LAST_LOGIN_IP);

        // Get all the sysUserList where lastLoginIp equals to UPDATED_LAST_LOGIN_IP
        defaultSysUserShouldNotBeFound("lastLoginIp.in=" + UPDATED_LAST_LOGIN_IP);
    }

    @Test
    @Transactional
    public void getAllSysUsersByLastLoginIpIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where lastLoginIp is not null
        defaultSysUserShouldBeFound("lastLoginIp.specified=true");

        // Get all the sysUserList where lastLoginIp is null
        defaultSysUserShouldNotBeFound("lastLoginIp.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByLastLoginDateIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where lastLoginDate equals to DEFAULT_LAST_LOGIN_DATE
        defaultSysUserShouldBeFound("lastLoginDate.equals=" + DEFAULT_LAST_LOGIN_DATE);

        // Get all the sysUserList where lastLoginDate equals to UPDATED_LAST_LOGIN_DATE
        defaultSysUserShouldNotBeFound("lastLoginDate.equals=" + UPDATED_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByLastLoginDateIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where lastLoginDate in DEFAULT_LAST_LOGIN_DATE or UPDATED_LAST_LOGIN_DATE
        defaultSysUserShouldBeFound("lastLoginDate.in=" + DEFAULT_LAST_LOGIN_DATE + "," + UPDATED_LAST_LOGIN_DATE);

        // Get all the sysUserList where lastLoginDate equals to UPDATED_LAST_LOGIN_DATE
        defaultSysUserShouldNotBeFound("lastLoginDate.in=" + UPDATED_LAST_LOGIN_DATE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByLastLoginDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where lastLoginDate is not null
        defaultSysUserShouldBeFound("lastLoginDate.specified=true");

        // Get all the sysUserList where lastLoginDate is null
        defaultSysUserShouldNotBeFound("lastLoginDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByFreezeDateIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where freezeDate equals to DEFAULT_FREEZE_DATE
        defaultSysUserShouldBeFound("freezeDate.equals=" + DEFAULT_FREEZE_DATE);

        // Get all the sysUserList where freezeDate equals to UPDATED_FREEZE_DATE
        defaultSysUserShouldNotBeFound("freezeDate.equals=" + UPDATED_FREEZE_DATE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByFreezeDateIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where freezeDate in DEFAULT_FREEZE_DATE or UPDATED_FREEZE_DATE
        defaultSysUserShouldBeFound("freezeDate.in=" + DEFAULT_FREEZE_DATE + "," + UPDATED_FREEZE_DATE);

        // Get all the sysUserList where freezeDate equals to UPDATED_FREEZE_DATE
        defaultSysUserShouldNotBeFound("freezeDate.in=" + UPDATED_FREEZE_DATE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByFreezeDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where freezeDate is not null
        defaultSysUserShouldBeFound("freezeDate.specified=true");

        // Get all the sysUserList where freezeDate is null
        defaultSysUserShouldNotBeFound("freezeDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByFreezeCauseIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where freezeCause equals to DEFAULT_FREEZE_CAUSE
        defaultSysUserShouldBeFound("freezeCause.equals=" + DEFAULT_FREEZE_CAUSE);

        // Get all the sysUserList where freezeCause equals to UPDATED_FREEZE_CAUSE
        defaultSysUserShouldNotBeFound("freezeCause.equals=" + UPDATED_FREEZE_CAUSE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByFreezeCauseIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where freezeCause in DEFAULT_FREEZE_CAUSE or UPDATED_FREEZE_CAUSE
        defaultSysUserShouldBeFound("freezeCause.in=" + DEFAULT_FREEZE_CAUSE + "," + UPDATED_FREEZE_CAUSE);

        // Get all the sysUserList where freezeCause equals to UPDATED_FREEZE_CAUSE
        defaultSysUserShouldNotBeFound("freezeCause.in=" + UPDATED_FREEZE_CAUSE);
    }

    @Test
    @Transactional
    public void getAllSysUsersByFreezeCauseIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where freezeCause is not null
        defaultSysUserShouldBeFound("freezeCause.specified=true");

        // Get all the sysUserList where freezeCause is null
        defaultSysUserShouldNotBeFound("freezeCause.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort equals to DEFAULT_USER_SORT
        defaultSysUserShouldBeFound("userSort.equals=" + DEFAULT_USER_SORT);

        // Get all the sysUserList where userSort equals to UPDATED_USER_SORT
        defaultSysUserShouldNotBeFound("userSort.equals=" + UPDATED_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort in DEFAULT_USER_SORT or UPDATED_USER_SORT
        defaultSysUserShouldBeFound("userSort.in=" + DEFAULT_USER_SORT + "," + UPDATED_USER_SORT);

        // Get all the sysUserList where userSort equals to UPDATED_USER_SORT
        defaultSysUserShouldNotBeFound("userSort.in=" + UPDATED_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort is not null
        defaultSysUserShouldBeFound("userSort.specified=true");

        // Get all the sysUserList where userSort is null
        defaultSysUserShouldNotBeFound("userSort.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort is greater than or equal to DEFAULT_USER_SORT
        defaultSysUserShouldBeFound("userSort.greaterThanOrEqual=" + DEFAULT_USER_SORT);

        // Get all the sysUserList where userSort is greater than or equal to UPDATED_USER_SORT
        defaultSysUserShouldNotBeFound("userSort.greaterThanOrEqual=" + UPDATED_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort is less than or equal to DEFAULT_USER_SORT
        defaultSysUserShouldBeFound("userSort.lessThanOrEqual=" + DEFAULT_USER_SORT);

        // Get all the sysUserList where userSort is less than or equal to SMALLER_USER_SORT
        defaultSysUserShouldNotBeFound("userSort.lessThanOrEqual=" + SMALLER_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsLessThanSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort is less than DEFAULT_USER_SORT
        defaultSysUserShouldNotBeFound("userSort.lessThan=" + DEFAULT_USER_SORT);

        // Get all the sysUserList where userSort is less than UPDATED_USER_SORT
        defaultSysUserShouldBeFound("userSort.lessThan=" + UPDATED_USER_SORT);
    }

    @Test
    @Transactional
    public void getAllSysUsersByUserSortIsGreaterThanSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where userSort is greater than DEFAULT_USER_SORT
        defaultSysUserShouldNotBeFound("userSort.greaterThan=" + DEFAULT_USER_SORT);

        // Get all the sysUserList where userSort is greater than SMALLER_USER_SORT
        defaultSysUserShouldBeFound("userSort.greaterThan=" + SMALLER_USER_SORT);
    }


    @Test
    @Transactional
    public void getAllSysUsersByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where status equals to DEFAULT_STATUS
        defaultSysUserShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the sysUserList where status equals to UPDATED_STATUS
        defaultSysUserShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysUsersByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultSysUserShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the sysUserList where status equals to UPDATED_STATUS
        defaultSysUserShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void getAllSysUsersByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where status is not null
        defaultSysUserShouldBeFound("status.specified=true");

        // Get all the sysUserList where status is null
        defaultSysUserShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    public void getAllSysUsersByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where remarks equals to DEFAULT_REMARKS
        defaultSysUserShouldBeFound("remarks.equals=" + DEFAULT_REMARKS);

        // Get all the sysUserList where remarks equals to UPDATED_REMARKS
        defaultSysUserShouldNotBeFound("remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysUsersByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where remarks in DEFAULT_REMARKS or UPDATED_REMARKS
        defaultSysUserShouldBeFound("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS);

        // Get all the sysUserList where remarks equals to UPDATED_REMARKS
        defaultSysUserShouldNotBeFound("remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void getAllSysUsersByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        // Get all the sysUserList where remarks is not null
        defaultSysUserShouldBeFound("remarks.specified=true");

        // Get all the sysUserList where remarks is null
        defaultSysUserShouldNotBeFound("remarks.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSysUserShouldBeFound(String filter) throws Exception {
        restSysUserMockMvc.perform(get("/api/sys-users?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userCode").value(hasItem(DEFAULT_USER_CODE)))
            .andExpect(jsonPath("$.[*].loginCode").value(hasItem(DEFAULT_LOGIN_CODE)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR)))
            .andExpect(jsonPath("$.[*].sign").value(hasItem(DEFAULT_SIGN)))
            .andExpect(jsonPath("$.[*].wxOpenid").value(hasItem(DEFAULT_WX_OPENID)))
            .andExpect(jsonPath("$.[*].mobileImei").value(hasItem(DEFAULT_MOBILE_IMEI)))
            .andExpect(jsonPath("$.[*].userType").value(hasItem(DEFAULT_USER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].refCode").value(hasItem(DEFAULT_REF_CODE)))
            .andExpect(jsonPath("$.[*].refName").value(hasItem(DEFAULT_REF_NAME)))
            .andExpect(jsonPath("$.[*].lastLoginIp").value(hasItem(DEFAULT_LAST_LOGIN_IP)))
            .andExpect(jsonPath("$.[*].lastLoginDate").value(hasItem(DEFAULT_LAST_LOGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].freezeDate").value(hasItem(DEFAULT_FREEZE_DATE.toString())))
            .andExpect(jsonPath("$.[*].freezeCause").value(hasItem(DEFAULT_FREEZE_CAUSE)))
            .andExpect(jsonPath("$.[*].userSort").value(hasItem(DEFAULT_USER_SORT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));

        // Check, that the count call also returns 1
        restSysUserMockMvc.perform(get("/api/sys-users/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSysUserShouldNotBeFound(String filter) throws Exception {
        restSysUserMockMvc.perform(get("/api/sys-users?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSysUserMockMvc.perform(get("/api/sys-users/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSysUser() throws Exception {
        // Get the sysUser
        restSysUserMockMvc.perform(get("/api/sys-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysUser() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        int databaseSizeBeforeUpdate = sysUserRepository.findAll().size();

        // Update the sysUser
        SysUser updatedSysUser = sysUserRepository.findById(sysUser.getId()).get();
        // Disconnect from session so that the updates on updatedSysUser are not directly saved in db
        em.detach(updatedSysUser);
        updatedSysUser
            .userCode(UPDATED_USER_CODE)
            .loginCode(UPDATED_LOGIN_CODE)
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .phone(UPDATED_PHONE)
            .sex(UPDATED_SEX)
            .avatar(UPDATED_AVATAR)
            .sign(UPDATED_SIGN)
            .wxOpenid(UPDATED_WX_OPENID)
            .mobileImei(UPDATED_MOBILE_IMEI)
            .userType(UPDATED_USER_TYPE)
            .refCode(UPDATED_REF_CODE)
            .refName(UPDATED_REF_NAME)
            .lastLoginIp(UPDATED_LAST_LOGIN_IP)
            .lastLoginDate(UPDATED_LAST_LOGIN_DATE)
            .freezeDate(UPDATED_FREEZE_DATE)
            .freezeCause(UPDATED_FREEZE_CAUSE)
            .userSort(UPDATED_USER_SORT)
            .status(UPDATED_STATUS)
            .remarks(UPDATED_REMARKS);
        SysUserDTO sysUserDTO = sysUserMapper.toDto(updatedSysUser);

        restSysUserMockMvc.perform(put("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isOk());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeUpdate);
        SysUser testSysUser = sysUserList.get(sysUserList.size() - 1);
        assertThat(testSysUser.getUserCode()).isEqualTo(UPDATED_USER_CODE);
        assertThat(testSysUser.getLoginCode()).isEqualTo(UPDATED_LOGIN_CODE);
        assertThat(testSysUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testSysUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testSysUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSysUser.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testSysUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSysUser.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testSysUser.getAvatar()).isEqualTo(UPDATED_AVATAR);
        assertThat(testSysUser.getSign()).isEqualTo(UPDATED_SIGN);
        assertThat(testSysUser.getWxOpenid()).isEqualTo(UPDATED_WX_OPENID);
        assertThat(testSysUser.getMobileImei()).isEqualTo(UPDATED_MOBILE_IMEI);
        assertThat(testSysUser.getUserType()).isEqualTo(UPDATED_USER_TYPE);
        assertThat(testSysUser.getRefCode()).isEqualTo(UPDATED_REF_CODE);
        assertThat(testSysUser.getRefName()).isEqualTo(UPDATED_REF_NAME);
        assertThat(testSysUser.getLastLoginIp()).isEqualTo(UPDATED_LAST_LOGIN_IP);
        assertThat(testSysUser.getLastLoginDate()).isEqualTo(UPDATED_LAST_LOGIN_DATE);
        assertThat(testSysUser.getFreezeDate()).isEqualTo(UPDATED_FREEZE_DATE);
        assertThat(testSysUser.getFreezeCause()).isEqualTo(UPDATED_FREEZE_CAUSE);
        assertThat(testSysUser.getUserSort()).isEqualTo(UPDATED_USER_SORT);
        assertThat(testSysUser.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSysUser.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingSysUser() throws Exception {
        int databaseSizeBeforeUpdate = sysUserRepository.findAll().size();

        // Create the SysUser
        SysUserDTO sysUserDTO = sysUserMapper.toDto(sysUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSysUserMockMvc.perform(put("/api/sys-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SysUser in the database
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSysUser() throws Exception {
        // Initialize the database
        sysUserRepository.saveAndFlush(sysUser);

        int databaseSizeBeforeDelete = sysUserRepository.findAll().size();

        // Delete the sysUser
        restSysUserMockMvc.perform(delete("/api/sys-users/{id}", sysUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SysUser> sysUserList = sysUserRepository.findAll();
        assertThat(sysUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUser.class);
        SysUser sysUser1 = new SysUser();
        sysUser1.setId(1L);
        SysUser sysUser2 = new SysUser();
        sysUser2.setId(sysUser1.getId());
        assertThat(sysUser1).isEqualTo(sysUser2);
        sysUser2.setId(2L);
        assertThat(sysUser1).isNotEqualTo(sysUser2);
        sysUser1.setId(null);
        assertThat(sysUser1).isNotEqualTo(sysUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysUserDTO.class);
        SysUserDTO sysUserDTO1 = new SysUserDTO();
        sysUserDTO1.setId(1L);
        SysUserDTO sysUserDTO2 = new SysUserDTO();
        assertThat(sysUserDTO1).isNotEqualTo(sysUserDTO2);
        sysUserDTO2.setId(sysUserDTO1.getId());
        assertThat(sysUserDTO1).isEqualTo(sysUserDTO2);
        sysUserDTO2.setId(2L);
        assertThat(sysUserDTO1).isNotEqualTo(sysUserDTO2);
        sysUserDTO1.setId(null);
        assertThat(sysUserDTO1).isNotEqualTo(sysUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sysUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sysUserMapper.fromId(null)).isNull();
    }
}
