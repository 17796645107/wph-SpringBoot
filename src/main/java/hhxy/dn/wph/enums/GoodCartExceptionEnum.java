package hhxy.dn.wph.enums;

/**
 * @Author: 邓宁
 * @Date: Created in 13:43 2019/4/12
 */

public enum GoodCartExceptionEnum{
    //异常枚举
    GOODCART_ADD_EXCEPTION(3001,"购物车添加失败"),
    GOODCART_UPDATE_EXCEPTION(3002,"购物车更新失败"),
    deleteCartById_error(3003,"删除购物车记录失败"),
    ;
    private Integer code;//返回码
    private String msg;//返回信息

    //构造方法
    GoodCartExceptionEnum(Integer code, String msg) {
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
