package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.Role;
import hhxy.dn.wph.mapper.RoleMapper;
import hhxy.dn.wph.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 17:12 2019/6/12
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findRolesByPeopleId(String people_id){
        return roleMapper.findRolesByPeopleId(people_id);
    }

    public List<Role> findRolesByResourceId(Integer resource_id){
        return roleMapper.findRolesByResourceId(resource_id);
    }


}
