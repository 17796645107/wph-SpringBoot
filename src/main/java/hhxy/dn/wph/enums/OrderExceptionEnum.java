package hhxy.dn.wph.enums;

/**
 * @author 邓宁
 * @date Created in 20:16 2019/5/10
 */

public enum OrderExceptionEnum {
    //
    CREATE_ORDER_ERROR(2001,"创建订单失败"),
    ORDER_EXCEPTION_ENUM(2002,"创建订单商品详情失败"),
    ORDER_ID_ERROR(2003,"订单编号错误"),
    ORDER_VALID_ERROR(2003,"支付验证错误"),
    ORDER_UPDATE_STATE_ERROR(2003,"更新订单状态错误"),
    ;

    private Integer code;
    private String msg;

    OrderExceptionEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
