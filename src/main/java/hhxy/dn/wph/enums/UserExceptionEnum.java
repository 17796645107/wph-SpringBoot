package hhxy.dn.wph.enums;

/**
 * @Author: 邓宁
 * @Date: Created in 16:12 2018/11/9
 */

//用户异常信息枚举
public enum UserExceptionEnum {
    LOGIN_ERROR(1001,"登录失败，用户名或密码不正确"),
    REGISTER_ERROR(1002,"用户注册失败"),
    CODE_ERROR(1003,"手机验证码错误"),
    SENDCODE_ERROR(1004,"短信验证码发送失败"),
    Unqualified(1005,"非法请求"),
    collectSellerError(1006,"用户收藏商品,插入数据失败"),
    updateUserError(1007,"更新用户信息错误：操作数据库失败！"),
    saveUserAddressError(1008,"添加用户收货地址错误：操作数据库失败！"),
    deleteAddressError(1008,"删除用户收货地址错误：操作数据库失败！"),
    NullPointerError(1009,"文件为空，请重新上传!"),
    UploadFileError(1010,"文件上传异常!"),
    UpdateUserHeadIconError(1011,"更新用户头像错误：操作数据库失败！")
    ;

    private Integer code;//返回码
    private String msg;//返回信息

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
