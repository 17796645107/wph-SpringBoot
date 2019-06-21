package hhxy.dn.wph.entity;

import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.util.AccountValidatorUtil;

import javax.validation.constraints.Pattern;

/**
 * @Author: 邓宁
 * @Date: Created in 23:35 2019/6/10
 * 用户注册信息
 */
public class UserRegister {

    /**
     * @Description 手机号
     * 使用SpringMVC @Valid 进行参数验证
     * regexp 正则表达式
     * message 错误提示信息
     */
    @Pattern(regexp = AccountValidatorUtil.REGEX_MOBILE,message = "手机号码格式不正确")
    private String telephone;

    /**
     * @Description 验证密码
     * 使用SpringMVC @Valid 进行参数验证
     * regexp 正则表达式
     * message 错误提示信息
     */
    @Pattern(regexp = AccountValidatorUtil.REGEX_PASSWORD,message = "密码格式不正确")
    private String password;

    /**
     * @Description 验证手机验证码
     * 使用SpringMVC @Valid 进行参数验证
     * regexp 正则表达式
     * message 错误提示信息
     */
    @Pattern(regexp = "^\\d{6}$",message = "验证码格式不正确")
    private String telephoneCode;

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
