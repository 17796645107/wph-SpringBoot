package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.AccountValidatorUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 11:02 2019/6/12
 */

public class UserLogin implements UserDetails {

    @Pattern(regexp = AccountValidatorUtil.REGEX_MOBILE,message = "手机号码格式不正确")//验证手机号
    private String telephone;//手机号
    @Pattern(regexp = AccountValidatorUtil.REGEX_PASSWORD,message = "密码格式不正确")//验证密码
    private String pwd;//密码
    private List<Role> roles;

    //返回用户所有角色的封装，一个Role对应一个GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getNameZh())    );
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.getPwd();
    }

    @Override
    public String getUsername() {
        return this.getTelephone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
