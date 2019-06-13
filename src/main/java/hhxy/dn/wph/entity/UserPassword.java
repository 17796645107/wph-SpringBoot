package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.MD5Util;

import java.io.Serializable;
//用户密码表
public class UserPassword implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer user_no;
    private String password;

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public String getPassword() {
        return password;
    }

    //MD5加密
    public void setPassword(String password) {
        this.password = MD5Util.getMD5(password);
    }
}