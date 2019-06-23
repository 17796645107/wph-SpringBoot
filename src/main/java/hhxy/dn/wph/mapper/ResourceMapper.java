package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Resource;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
/**
 * @Author: 邓宁
 * @Date: Created in 15:17 2019/6/13
 */

public interface ResourceMapper {
    final String RESOURCE_FIELD = " id,url,resource_name ";

    /**
     * 查询URL资源
     * @param url
     * @return hhxy.dn.wph.entity.Resource
     */
    @Select("select"+ RESOURCE_FIELD + "from"+ RESOURCE +"where url = #{url}")
    Resource getResourceByUrl(String url);

    /**
     * 查询所有资源
     * @param
     * @return java.util.List<hhxy.dn.wph.entity.Resource>
     */
    @Select("select"+ RESOURCE_FIELD +"from" + RESOURCE)
    List<Resource> listResource();
}
