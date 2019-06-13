package hhxy.dn.wph.mapper;

import hhxy.dn.wph.domain.Resource;
import org.apache.ibatis.annotations.Select;
import static hhxy.dn.wph.util.DBTableUtil.*;
/**
 * @Author: 邓宁
 * @Date: Created in 15:17 2019/6/13
 */

public interface ResourceMapper {

    @Select("select * from"+ RESOURCE +"where url = #{url}")
    Resource findResourceByUrl(String url);
}
