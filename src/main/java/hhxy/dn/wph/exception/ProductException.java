package hhxy.dn.wph.exception;

import hhxy.dn.wph.enums.ProductExceptionEnum;
import hhxy.dn.wph.enums.UserExceptionEnum;

/**
 * @Author: 邓宁
 * @Date: Created in 9:04 2018/11/29
 */
//商品自定义异常类
public class ProductException extends RuntimeException {

    //返回码
    private Integer code;

    //构造方法
    public ProductException(ProductExceptionEnum productExceptionEnum){
        super(productExceptionEnum.getMsg());
        this.code=productExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
