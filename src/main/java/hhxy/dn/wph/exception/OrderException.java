package hhxy.dn.wph.exception;

import hhxy.dn.wph.enums.OrderExceptionEnum;

/**
 * @Author: 邓宁
 * @Date: Created in 19:53 2019/5/10
 */

public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(OrderExceptionEnum orderExceptionEnum){
        super(orderExceptionEnum.getMsg());
        this.code = orderExceptionEnum.getCode();
    }

    public OrderException(){ }

    public Integer getCode() {
        return code;
    }
}
