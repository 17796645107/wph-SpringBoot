package hhxy.dn.wph.domain;

import hhxy.dn.wph.entity.Role;

import java.io.Serializable;
import java.util.List;

public class RolePeople implements Serializable {

    private Integer peopleId;
    private Integer roleId;
    private Role role;

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