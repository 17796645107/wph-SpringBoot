package hhxy.dn.wph.enums;

/**
 * @Author: 邓宁
 * @Date: Created in 16:56 2019/6/8
 */

public enum SellerExceptionEnum {
    accountError(5001,"账号或密码不正确");
    ;
    private Integer code;
    private String msg;

    SellerExceptionEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
