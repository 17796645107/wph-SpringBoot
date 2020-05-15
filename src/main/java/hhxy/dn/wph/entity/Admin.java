package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
/**
 *  管理员类
 * @author 邓宁宁
 * @date 2016/10/31
 */
public class Admin implements Serializable {
    /**
     *
     */
    private Integer id;
    /**
     *  用户名
     */
    private String username;
    /**
     *  密码
     */
    private String password;
    /**
     *  创建时间
     */
    private String created;
    /**
     *  更新时间
     */


    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }



    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", created='" + created + '\'' +

                '}';
    }
}