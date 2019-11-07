package com.ruowei.modules.sys.mapper;

import com.ruowei.modules.sys.domain.table.Authority;
import com.ruowei.modules.sys.domain.table.SysUser;
import com.ruowei.modules.sys.pojo.SysUserDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Mapper for the entity {@link SysUser} and its DTO {@link SysUserDTO}.
 */
@Service
public class SysUserMapperOld {



    public SysUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return sysUser;
    }

    public List<SysUserDTO> usersToUserDTOs(List<SysUser> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public SysUserDTO userToUserDTO(SysUser user) {
        return toDto(user);
    }

    public List<SysUser> userDTOsToUsers(List<SysUserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public SysUser userDTOToUser(SysUserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            SysUser user = new SysUser();
            user.setId(userDTO.getId());
            user.setLoginCode(userDTO.getLoginCode());
            user.setEmail(userDTO.getEmail());
            user.setActivated(userDTO.isActivated());
            Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
            user.setAuthorities(authorities);
            return user;
        }
    }


    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();

        if(authoritiesAsString != null){
            authorities = authoritiesAsString.stream().map(string -> {
                Authority auth = new Authority();
                auth.setName(string);
                return auth;
            }).collect(Collectors.toSet());
        }

        return authorities;
    }

    public SysUser toEntity(SysUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SysUser sysUser = new SysUser();

        sysUser.setId( dto.getId() );
        sysUser.setUserCode( dto.getUserCode() );
        sysUser.setLoginCode( dto.getLoginCode() );
        sysUser.setUserName( dto.getUserName() );
        sysUser.setPassword( dto.getPassword() );
        sysUser.setEmail( dto.getEmail() );
        sysUser.setMobile( dto.getMobile() );
        sysUser.setPhone( dto.getPhone() );
        sysUser.setSex( dto.getSex() );
        sysUser.setAvatar( dto.getAvatar() );
        sysUser.setSign( dto.getSign() );
        sysUser.setWxOpenid( dto.getWxOpenid() );
        sysUser.setMobileImei( dto.getMobileImei() );
        sysUser.setUserType( dto.getUserType() );
        sysUser.setRefCode( dto.getRefCode() );
        sysUser.setRefName( dto.getRefName() );
        sysUser.setLastLoginIp( dto.getLastLoginIp() );
        sysUser.setLastLoginDate( dto.getLastLoginDate() );
        sysUser.setFreezeDate( dto.getFreezeDate() );
        sysUser.setFreezeCause( dto.getFreezeCause() );
        sysUser.setUserSort( dto.getUserSort() );
        sysUser.setStatus( dto.getStatus() );
        sysUser.setRemarks( dto.getRemarks() );

        return sysUser;
    }

    public SysUserDTO toDto(SysUser entity) {
        if ( entity == null ) {
            return null;
        }

        SysUserDTO sysUserDTO = new SysUserDTO();

        sysUserDTO.setId( entity.getId() );
        sysUserDTO.setUserCode( entity.getUserCode() );
        sysUserDTO.setLoginCode( entity.getLoginCode() );
        sysUserDTO.setUserName( entity.getUserName() );
        sysUserDTO.setPassword( entity.getPassword() );
        sysUserDTO.setEmail( entity.getEmail() );
        sysUserDTO.setMobile( entity.getMobile() );
        sysUserDTO.setPhone( entity.getPhone() );
        sysUserDTO.setSex( entity.getSex() );
        sysUserDTO.setAvatar( entity.getAvatar() );
        sysUserDTO.setSign( entity.getSign() );
        sysUserDTO.setWxOpenid( entity.getWxOpenid() );
        sysUserDTO.setMobileImei( entity.getMobileImei() );
        sysUserDTO.setUserType( entity.getUserType() );
        sysUserDTO.setRefCode( entity.getRefCode() );
        sysUserDTO.setRefName( entity.getRefName() );
        sysUserDTO.setLastLoginIp( entity.getLastLoginIp() );
        sysUserDTO.setLastLoginDate( entity.getLastLoginDate() );
        sysUserDTO.setFreezeDate( entity.getFreezeDate() );
        sysUserDTO.setFreezeCause( entity.getFreezeCause() );
        sysUserDTO.setUserSort( entity.getUserSort() );
        sysUserDTO.setStatus( entity.getStatus() );
        sysUserDTO.setRemarks( entity.getRemarks() );

        return sysUserDTO;
    }

    public List<SysUser> toEntity(List<SysUserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SysUser> list = new ArrayList<SysUser>( dtoList.size() );
        for ( SysUserDTO sysUserDTO : dtoList ) {
            list.add( toEntity( sysUserDTO ) );
        }

        return list;
    }

    public List<SysUserDTO> toDto(List<SysUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SysUserDTO> list = new ArrayList<SysUserDTO>( entityList.size() );
        for ( SysUser sysUser : entityList ) {
            list.add( toDto( sysUser ) );
        }

        return list;
    }
}
