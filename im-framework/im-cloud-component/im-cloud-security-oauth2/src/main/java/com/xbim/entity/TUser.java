package com.xbim.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaobin
 * @since 2020-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "TUser对象", description = "")
public class TUser implements UserDetails {


    @ApiModelProperty(value = "用户唯一ID（snowflake算法）")
    private Long userId;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像信息")
    private String avatar;

    @ApiModelProperty(value = "邮箱信息")
    private String email;

    @ApiModelProperty(value = "出生年月")
    private LocalDate birthYear;

    @ApiModelProperty(value = "性别（0：男，1：女）")
    private Boolean gender;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "账户是否过期（1：过期，0：未过期）")
    private Boolean isExpired;

    @ApiModelProperty(value = "账户是否锁定（1：锁定，0：未锁定）")
    private Boolean isLocked;

    @ApiModelProperty(value = "凭证是否过期（1：过期，0：未过期）")
    private Boolean isCredentialsExpired;

    @ApiModelProperty(value = "账户是否启用（1：未启用，0：启用）")
    private Boolean isEnabled;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "添加人ID")
    private Long createUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateUser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> ga = new ArrayList<SimpleGrantedAuthority>();
        return ga;
    }

    @Override
    public String getUsername() {
        return this.account;
    }


    @Override
    public boolean isAccountNonExpired() {
        return this.isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
