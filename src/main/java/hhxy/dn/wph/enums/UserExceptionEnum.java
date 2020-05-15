package hhxy.dn.wph.enums;

/**
 * @author 邓宁
 * @date Created in 16:12 2018/11/9
 * 用户异常信息枚举
 */

public enum UserExceptionEnum {
    /**
     *
     */
    LOGIN_ERROR(1001,"登录失败，用户名或密码不正确"),
    REGISTER_ERROR(1002,"用户注册失败"),
    CODE_ERROR(1003,"手机验证码错误"),
    SENDCODE_ERROR(1004,"短信验证码发送失败"),
    UNQUALIFIED_ERROR(1005,"非法请求"),
    COLLECT_SELLER_ERROR(1006,"用户收藏商品,插入数据失败"),
    UPDATE_USER_ERROR(1007,"更新用户信息错误：操作数据库失败！"),
    SAVE_USER_ADDRESS_ERROR(1008,"添加用户收货地址错误：操作数据库失败！"),
    DELETE_ADDRESS_ERROR(1008,"删除用户收货地址错误：操作数据库失败！"),
    UPDATE_ADDRESS_ERROR(1013,"更新用户收货地址错误：操作数据库失败！"),
    NULL_FILE_ERROR(1009,"文件为空，请重新上传!"),
    UPLOAD_FILE_ERROR(1010,"文件上传异常!"),
    UPDATE_USER_HEAD_ICON_ERROR(1011,"更新用户头像错误：操作数据库失败！"),
    TELEPHONE_ERROR(1012,"手机号已经注册"),
    DELETE_SEARCH_HISTORY_ERROR(1013,"删除搜索记录失败"),
    DELETE_DEFAULT_ADDRESS__Error(1014,"无法删除默认收货地址"),
    ;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;

    UserExceptionEnum(Integer code, String msg) {
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
