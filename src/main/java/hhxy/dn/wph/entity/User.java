package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.ValidatorUtil;

import javax.validation.constraints.Pattern;

/**
 * 用户表
 *
 * @author 邓宁
 * @date Created in 13:38 2018/11/12
 */
public class User {
    /**
     * 自动编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     * 使用SpringMVC @Valid 进行参数验证
     * regexp 正则表达式
     * message 错误提示信息
     */
    @Pattern(regexp = ValidatorUtil.REGEX_MOBILE,message = "手机号码格式不正确")
    private String telephone;

    /**
     * 密码
     * 使用SpringMVC @Valid 进行参数验证
     * regexp 正则表达式
     * message 错误提示信息
     */
    @Pattern(regexp = ValidatorUtil.REGEX_PASSWORD,message = "密码格式不正确")
    private String password;

    /**
     * 手机验证码
     * 使用SpringMVC @Valid 进行参数验证
     * regexp 正则表达式
     * message 错误提示信息
     */
    @Pattern(regexp = "^\\d{6}$",message = "手机验证码格式不正确")
    private String telephoneCode;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 会员等级
     */
    private String vip;
    /**
     * 头像-图片路径
     */
    private String headImage;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(String telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userNo='" + userNo + '\'' +
                ", nickname='" + nickname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                ", telephoneCode='" + telephoneCode + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", vip='" + vip + '\'' +
                ", headImage='" + headImage + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                '}';
    }
}
