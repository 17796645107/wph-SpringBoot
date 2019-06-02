package hhxy.dn.wph.exception;

import hhxy.dn.wph.enums.GoodCartExceptionEnum;
import hhxy.dn.wph.enums.UserExceptionEnum;

/**
 * @Author: 邓宁
 * @Date: Created in 13:36 2019/4/12
 */

public class GoodCartException extends RuntimeException{
    //返回码
    private Integer code;

    //构造方法
    public GoodCartException(GoodCartExceptionEnum goodCartExceptionEnum){
        super(goodCartExceptionEnum.getMsg());
        this.code=goodCartExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
