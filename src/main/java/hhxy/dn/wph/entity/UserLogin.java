package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.ValidatorUtil;

import javax.validation.constraints.Pattern;

/**
 * @author 邓宁
 * @date Created in 11:02 2019/6/12
 */
public class UserLogin{

    /**
     * 验证手机号
     */
    @Pattern(regexp = ValidatorUtil.REGEX_MOBILE,message = "手机号码格式不正确")
    private String telephone;
    /**
     * 验证密码
     */
    @Pattern(regexp = ValidatorUtil.REGEX_PASSWORD,message = "密码格式不正确")
    private String pwd;

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

}
