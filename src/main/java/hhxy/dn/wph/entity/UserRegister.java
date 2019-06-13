package hhxy.dn.wph.entity;

import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.util.AccountValidatorUtil;

import javax.validation.constraints.Pattern;

/**
 * @Author: 邓宁
 * @Date: Created in 23:35 2019/6/10
 */
//用户注册信息
public class UserRegister {

    @Pattern(regexp = AccountValidatorUtil.REGEX_MOBILE,message = "手机号码格式不正确")//验证手机号
    private String telephone;//手机号
    @Pattern(regexp = AccountValidatorUtil.REGEX_PASSWORD,message = "密码格式不正确")//验证密码
    private String password;//密码
    @Pattern(regexp = "^\\d{6}$",message = "验证码格式不正确")//验证验证码
    private String telephoneCode;//手机验证码

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
}
