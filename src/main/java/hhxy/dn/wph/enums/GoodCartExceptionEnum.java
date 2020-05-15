package hhxy.dn.wph.enums;

/**
 * @author 邓宁
 * @date Created in 13:43 2019/4/12
 * 购物车模块异常枚举信息
 */

public enum GoodCartExceptionEnum{
    //异常枚举
    GOODCART_ADD_EXCEPTION(3001,"购物车添加失败"),
    GOODCART_UPDATE_EXCEPTION(3002,"购物车更新失败"),
    DELETE_CART_ERROR(3003,"删除购物车记录失败"),
    ;
    /**
     *  返回码
     */
    private Integer code;
    /**
     *  返回信息
     */
    private String msg;

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
