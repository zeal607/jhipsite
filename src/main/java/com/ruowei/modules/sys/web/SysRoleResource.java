package com.ruowei.modules.sys.web;

import com.ruowei.modules.sys.domain.SysRole;
import com.ruowei.modules.sys.service.role.SysRoleService;
import com.ruowei.web.rest.errors.BadRequestAlertException;
import com.ruowei.modules.sys.pojo.SysRoleDTO;
import com.ruowei.service.dto.SysRoleCriteria;
import com.ruowei.modules.sys.service.role.impl.SysRoleQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link SysRole}.
 */
@RestController
@RequestMapping("/api")
public class SysRoleResource {
}
