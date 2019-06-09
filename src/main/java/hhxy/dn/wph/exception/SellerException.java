package hhxy.dn.wph.exception;

import hhxy.dn.wph.enums.SellerExceptionEnum;

/**
 * @Author: 邓宁
 * @Date: Created in 16:53 2019/6/8
 */

public class SellerException extends RuntimeException{
    private Integer code;

    public SellerException(SellerExceptionEnum sellerExceptionEnum){
        super(sellerExceptionEnum.getMsg());
        this.code = sellerExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
