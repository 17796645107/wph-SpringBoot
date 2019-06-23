package hhxy.dn.wph.entity;

import java.io.Serializable;

/**
 * @Description 用户-角色
 * @Author: 邓宁宁
 * @Date: 2019/6/22
 */
public class UserRole implements Serializable {
    private Integer id;

    /**
     * @Description 用户名
     */
    private String username;

    /**
     * @Description 角色ID
     */
    private Integer roleId;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}