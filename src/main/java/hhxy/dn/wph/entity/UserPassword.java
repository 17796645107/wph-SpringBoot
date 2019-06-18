package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.MD5Util;

import java.io.Serializable;
//用户密码表
public class UserPassword implements Serializable {
    private Integer id;
    private Integer userId;
    private String password;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    //MD5加密
    public void setPassword(String password) {
        this.password = MD5Util.getMD5(password);
    }
}