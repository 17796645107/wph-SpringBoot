package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 12:53 2019/6/12
 */

public interface RoleMapper {
    final String ROLE_FIELD = " id,name,name_zh ";

    /**
     * 根据人员ID获取人员所具有的权限
     * @param peopleNo
     * @return java.util.List<hhxy.dn.wph.entity.Role>
     */
    @Select("select"+ ROLE_FIELD +"from "+ ROLE +
            "where id in("+
                        "select role_id from"+ PEOPLE_ROLE +"where people_no = #{peopleNo}"+
            ")")
    @Results(id = "releResultMap",value = {
            @Result(column = "name_zh",property = "nameZh")
    })
    List<Role> listRoleByPeopleId(String peopleNo);

    /**
     * 获取角色
     * @param roleId 角色ID
     * @return hhxy.dn.wph.entity.Role
     */
    @Select("select"+ ROLE_FIELD +"from"+ ROLE +"where role_id = #{role_id}")
    @ResultMap(value = "releResultMap")
    Role getRoleById(Integer roleId);

    /**
     * 根据资源ID获取人员所具有的权限
     * @param resourceId
     * @return java.util.List<hhxy.dn.wph.entity.Role>
     */
    @Select("select"+ ROLE_FIELD +"from "+ ROLE +
            "where id in("+
                        "select role_id from"+ ROLE_RESOURCE +
                        "where resource_id = #{resource_id}"+
            ")")
    @ResultMap(value = "releResultMap")
    List<Role> listRoleByResourceId(Integer resourceId);
}
