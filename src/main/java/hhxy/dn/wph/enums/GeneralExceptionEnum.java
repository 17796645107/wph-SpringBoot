package hhxy.dn.wph.enums;

/**
 * @Author: 邓宁
 * @Date: Created in 15:32 2019/5/23
 */

public enum GeneralExceptionEnum {
    /**
     * @Description
     */
    NOT_FOUND_ERROR(0002,"没有查询到信息"),
    PARAM_ERROR(0003,"参数不合法"),
    SAVE_DATABASE_ERROR(0004,"数据库添加数据失败"),
    UPDATE_DATABASE_ERROR(0005,"数据库更新数据失败"),
    DELETE_DATABASE_ERROR(0006,"数据库删除数据失败"),
    FIND_DATABASE_ERROR(0007,"数据库查询数据失败"),
    ;


    /**
     * @Description
     */
    private Integer code;
    /**
     * @Description
     */
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
