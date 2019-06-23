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

    @Override
    public List<Role> listRolesByPeopleId(String peopleId){
        return roleMapper.listRoleByPeopleId(peopleId);
    }

    @Override
    public List<Role> listRoleByResourceId(Integer resourceId){
        return roleMapper.listRoleByResourceId(resourceId);
    }
}
