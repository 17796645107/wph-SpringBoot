package hhxy.dn.wph.entity;

/**
 * @author 邓宁
 * @date Created in 16:12 2018/11/9
 */
/*
 * 响应 最终返回信息
 */
public class Result<T>{

    //状态码
    private  Integer code;

    //提示信息
    private String msg;

    //返回数据
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
