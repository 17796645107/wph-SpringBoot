package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;
import hhxy.dn.wph.util.IDUtil;
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
public class User{
    private Integer user_no;//自动编号
    private String user_id;//用户ID
    private String nickname;//昵称
    private String telephone;//手机号
    private String name;//真实姓名
    private String sex;//性别
    private String birthday;//生日
    private String email;//邮箱
    private String vip;//会员等级
    private String headImage;//头像-图片路径
    private Integer status;//状态
    private String created;//创建时间
    private String updated;//更新时间
    private List<Role> roles;//用户角色


    public User(){
        //默认值
        this.user_id = IDUtil.createPeopleID("U");
        this.nickname = "唯品会会员";
        this.status = 1;
        this.vip = "青铜";
        this.headImage = "avatar_89373029_1496285287409.jpg";
        this.created = DateUtil.getDate();
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
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

    @Override
    public String toString() {
        return "User{" +
                "user_no=" + user_no +
                ", user_id='" + user_id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", vip='" + vip + '\'' +
                ", headImage='" + headImage + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", roles=" + roles +
                '}';
    }
}
