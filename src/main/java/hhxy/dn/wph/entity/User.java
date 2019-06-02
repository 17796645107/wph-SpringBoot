package hhxy.dn.wph.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 13:38 2018/11/12
 */
//用户表
public class User implements UserDetails {
    private Integer user_id;//用户ID
    private String nickname;//昵称ID
    @NotNull
    private String telephone;//手机号
    @NotNull
    private String name;//真实姓名
    private String sex;//性别
    private String birthday;//生日
    private String email;//邮箱
    private String vip;//会员等级
    private String headImage;//头像-图片路径
    private Integer status;//状态
    private List<Role> roles;//用户角色
    private String created;//创建时间
    private String updated;//更新时间

    //重写UserDetails接口的方法
    @Override
    //获取当前用户所具有的角色
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : roles) {
            //构造SimpleGrantedAuthority
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorityList;*/
        return null;
    }

    @Override
    //获取用户名
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    //getter,setter方法

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
