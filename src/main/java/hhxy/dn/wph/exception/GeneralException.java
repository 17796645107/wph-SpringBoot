package hhxy.dn.wph.exception;

import hhxy.dn.wph.enums.GeneralExceptionEnum;

/**
 * @Author: 邓宁
 * @Date: Created in 15:29 2019/5/23
 */

public class GeneralException extends RuntimeException {
    private Integer code;

    public GeneralException(GeneralExceptionEnum generalExceptionEnum){
        super(generalExceptionEnum.getMsg());
        this.code = generalExceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
