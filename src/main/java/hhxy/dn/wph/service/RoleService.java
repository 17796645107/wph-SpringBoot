package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Role;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 17:11 2019/6/12
 */

public interface RoleService {

    //根据人员ID获取人员所具有的权限
    List<Role> findRolesByPeopleId(String people_id);

    //根据资源ID获取访问该资源所需要的权限
    List<Role> findRolesByResourceId(Integer resource_id);
}
