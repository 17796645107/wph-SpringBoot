package hhxy.dn.wph.entity;

import hhxy.dn.wph.entity.Role;

import java.io.Serializable;
import java.util.List;
//人员-角色表
public class RolePeople implements Serializable {

    private Integer peopleId;//人员ID
    private Integer roleId;//角色ID
    private Role role;//角色

    private static final long serialVersionUID = 1L;

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}