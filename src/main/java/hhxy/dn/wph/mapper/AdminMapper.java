/*
  @deprecated asd
 */
package hhxy.dn.wph.mapper;
import hhxy.dn.wph.mapper.provider.AdminProvider;
import org.apache.ibatis.annotations.InsertProvider;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.mapper.provider.AdminProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

/**
 * @author 邓宁
 */

public interface AdminMapper {

    /**
     *
     * @param category 分类
     * @return int
     */
    @InsertProvider(type = AdminProvider.class,method = "saveCategory")
    int saveCategory(Category category);

    @InsertProvider(type = AdminProvider.class,method = "saveAttribute")
    @Options(keyProperty = "attrId",useGeneratedKeys = true)
    int saveAttribute(ProductAttribute attribute);

    @Select("select attr_id from" + PRODUCT_ATTRIBUTE + "where attr_name = #{attributeName}")
    Integer getAttributeId(String attributeName);

    @Select("select value_id from" + PRODUCT_ATTRIBUTE_VALUE + "where value = #{value}")
    Integer getAttributeValueId(String value);

    @InsertProvider(type = AdminProvider.class,method = "saveProductAttributeValueRelation")
    int saveProductAttributeValueRelation(ProductAttributeRelation productAttributeRelation);

    @InsertProvider(type = AdminProvider.class,method = "saveAttributeValue")
    @Options(keyProperty = "valueId",useGeneratedKeys = true)
    int saveAttributeValue(ProductAttributeValue attributeValue);

    @Insert("insert into"+ CATEGORY_ATTRIBUTE +
            "(category_id,attribute_id)value(#{categoryId},#{attributeId})")
    int saveCategoryAttributeRelation(CategoryAttribute categoryAttribute);

    @Select("select attr_id from"+ PRODUCT_ATTRIBUTE +"where attr_name = #{attr_name}")
    @Results({
            @Result(column = "attr_id",property = "attrId")
    })
    Integer findCategoryIdByCategoryName(String attr_name);
}
