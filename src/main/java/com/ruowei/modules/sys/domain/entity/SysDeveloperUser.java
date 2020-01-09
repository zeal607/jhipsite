package com.ruowei.modules.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruowei.common.entity.AbstractAuditingUUIDEntity;
import com.ruowei.modules.sys.domain.table.Authority;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 开发者用户
 * @author 刘东奇
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysDeveloperUser extends AbstractAuditingUUIDEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 登录账号
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "login_code", length = 100, nullable = false, unique = true)
    private String login;

    /**
     * 登录密码
     */
    @JsonIgnore
    @NotNull
    @Size(max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    /**
     * 用户昵称
     */
    @Size(max = 100)
    @Column(name = "user_name", length = 100)
    private String userName;

    /**
     * 电子邮箱
     */
    @Email
    @Size(max = 300)
    @Column(name = "email", length = 300, unique = true)
    private String email;

    /**
     * 是否激活
     */
    @NotNull
    @Column(nullable = true)
    private Boolean activated = false;

    /**
     * 头像相对路径
     */
    @Size(max = 1000)
    @Column(name = "avatar", length = 1000)
    private String imageUrl;

    /**
     * 激活秘钥
     */
    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    /**
     * 重置秘钥
     */
    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    /**
     * 重置时间
     */
    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @JsonIgnore
    private Instant createdDate = Instant.now();

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDeveloperUser)) {
            return false;
        }
        return id != null && id.equals(((SysDeveloperUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysDeveloperUser{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", imageUrl='" + imageUrl + '\'' +
            ", activationKey='" + activationKey + '\'' +
            ", resetKey='" + resetKey + '\'' +
            ", resetDate=" + resetDate +
            ", authorities=" + authorities +
            '}';
    }
}
