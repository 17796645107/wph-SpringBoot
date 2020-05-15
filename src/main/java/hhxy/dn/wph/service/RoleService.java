package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Role;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 17:11 2019/6/12
 */

public interface RoleService {

    /**
     * 根据人员ID获取人员所具有的权限
     * @param peopleId
     * @return java.util.List<hhxy.dn.wph.entity.Role>
     */
    List<Role> listRolesByPeopleId(String peopleId);

    /**
     * 根据资源ID获取访问该资源所需要的权限
     * @param resourceId
     * @return java.util.List<hhxy.dn.wph.entity.Role>
     */
    List<Role> listRoleByResourceId(Integer resourceId);
}
