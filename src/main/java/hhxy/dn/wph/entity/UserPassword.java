package hhxy.dn.wph.entity;

import java.io.Serializable;

public class UserPassword implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer user_id;
    private String password;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}