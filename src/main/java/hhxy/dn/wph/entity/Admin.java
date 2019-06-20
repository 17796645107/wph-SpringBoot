package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description 管理员类
 * @author 邓宁宁
 * @date 2016/10/31
 */
public class Admin implements Serializable {
    /**
     * @Description
     */
    private Integer id;
    /**
     * @Description 用户名
     */
    private String username;
    /**
     * @Description 密码
     */
    private String password;
    /**
     * @Description 创建时间
     */
    private String created;
    /**
     * @Description 更新时间
     */
    private String updated;

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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}