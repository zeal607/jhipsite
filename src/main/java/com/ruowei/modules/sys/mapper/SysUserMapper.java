package com.ruowei.modules.sys.mapper;

import com.ruowei.common.mapper.EntityMapper;
import com.ruowei.modules.sys.domain.table.Authority;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import com.ruowei.modules.sys.pojo.user.SysUserRegisterDTO;
import org.mapstruct.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link SysUser} and its DTO {@link SysUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysUserMapper extends EntityMapper<SysUserDTO, SysUser> {

    default SysUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysUser sysPost = new SysUser();
        sysPost.setId(id);
        return sysPost;
    }

    /**
     * 组装SysUser
     * @param sysUserRegisterDTO
     * @return
     */
    @Mappings({
        @Mapping(target = "id" ,ignore=true),
        @Mapping(source = "login", target = "loginCode")
    })
    SysUser assembleEntity(SysUserRegisterDTO sysUserRegisterDTO);

    default Set<Authority> projectIntoAuthority(Set<String> value){
        Set<Authority> authorities = new HashSet<>();
        if(value != null){
            authorities = value.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }
        return authorities;
     }

    default Set<String> projectIntoString(Set<Authority> value){
        Set<String> authorities = new HashSet<>();
        if(value != null){
            authorities = value.stream().map(authority -> {
                return authority.getName();
            }).collect(Collectors.toSet());
        }
        return authorities;
    }

}
