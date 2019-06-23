package hhxy.dn.wph.entity;

import hhxy.dn.wph.entity.Role;

import java.io.Serializable;
import java.util.List;
/**
 * @Description 人员-角色类
 * @Date: 2019/6/22
 * @Author: 邓宁
 */
public class RolePeople implements Serializable {
    private Integer id;
    /**
     * @Description 人员ID(用户,商户,管理员)
     */
    private Integer peopleNo;
    /**
     * @Description 角色ID
     */
    private Integer roleId;
    /**
     * @Description 角色类
     */
    private Role role;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeopleNo() {
        return peopleNo;
    }

    public void setPeopleNo(Integer peopleNo) {
        this.peopleNo = peopleNo;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}