package hhxy.dn.wph.mapper;

import hhxy.dn.wph.domain.RolePeople;
import hhxy.dn.wph.entity.Role;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import static hhxy.dn.wph.util.DBTableUtil.*;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 12:53 2019/6/12
 */

public interface RoleMapper {

    @Select("select * from "+ ROLE +
            "where role_id in("+
                            "select role_id from"+ PEOPLE_ROLE +"where people_id = #{people_id}"+
            ")")
    List<Role> findRolesByPeopleId(String people_id);

    @Select("select * from"+ ROLE +"where role_id = #{role_id}")
    Role findRoleById(Integer role_id);

    @Select("select * from "+ ROLE +
            "where role_id in("+
                                "select role_id from"+ ROLE_RESOURCE +
                                "where resource_id = #{resource_id}"+
            ")")
    List<Role> findRolesByResourceId(Integer resource_id);
}
