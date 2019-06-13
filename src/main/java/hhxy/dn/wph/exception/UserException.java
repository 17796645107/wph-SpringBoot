package hhxy.dn.wph.exception;

import hhxy.dn.wph.enums.UserExceptionEnum;

/**
 * @Author: 邓宁
 * @Date: Created in 9:04 2018/11/29
 */
//用户自定义异常类
public class UserException extends RuntimeException {

    //返回码
    private Integer code;

    //构造方法
    public UserException(UserExceptionEnum userExceptionEnum){
        super(userExceptionEnum.getMsg());
        this.code=userExceptionEnum.getCode();
    }

    public UserException(String messgae,Integer code){
        super(messgae);
        this.code = code;
    }

    public UserException(){ }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
