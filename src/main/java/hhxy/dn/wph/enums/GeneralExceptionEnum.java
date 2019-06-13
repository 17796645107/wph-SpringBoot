package hhxy.dn.wph.enums;

/**
 * @Author: 邓宁
 * @Date: Created in 15:32 2019/5/23
 */

public enum GeneralExceptionEnum {
    notFound(0002,"没有查询到信息"),
    paramError(0003,"参数不合法"),
    ;
    private Integer code;
    private String msg;

    GeneralExceptionEnum(Integer code,String msg){
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
