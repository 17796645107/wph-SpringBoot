package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.domain.Category;
import hhxy.dn.wph.domain.ProductAttribute;
import hhxy.dn.wph.domain.ProductAttributeRelation;
import hhxy.dn.wph.domain.ProductAttributeValue;
import hhxy.dn.wph.util.DateUtil;
import org.apache.ibatis.jdbc.SQL;
import static hhxy.dn.wph.util.DBTableUtil.*;
/**
 * @Author: 邓宁
 * @Date: Created in 18:20 2019/5/14
 */

public class AdminProvider {

    public String saveCategory(Category category){
        return new SQL(){
            {
                INSERT_INTO(CATEGORY);
                VALUES("category_name","#{categoryName}");
                VALUES("category_sort","#{categorySort}");
                VALUES("parent_id","#{parentId}");
                VALUES("admin_id","#{adminId}");
                VALUES("created_time", "#{createdTime}");
                VALUES("status", "#{status}");
            }
        }.toString();
    }

    public String saveAttribute(ProductAttribute attribute){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_ATTRIBUTE);
                VALUES("attr_name","#{attrName}");
                VALUES("is_search","#{isSearch}");
                VALUES("admin_id","#{adminId}");
                VALUES("status", "#{status}");
                VALUES("created_time", "#{createdTime}");
            }
        }.toString();
    }

    public String saveProductAttributeValueRelation(ProductAttributeRelation productAttributeRelation){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_ATTRIBUTE_RELATION);
                VALUES("product_id","#{productId}");
                VALUES("attribute_id","#{attributeId}");
                VALUES("value_id","#{valueId}");
                VALUES("status", "#{status}");
                VALUES("created", "#{created}");
            }
        }.toString();
    }

    public String saveAttributeValue(ProductAttributeValue attributeValue){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_ATTRIBUTE_VALUE);
                VALUES("value","#{value}");
                VALUES("status","#{status}");
                VALUES("attribute_id","#{attributeId}");
                VALUES("created_time","#{createdTime}");
            }
        }.toString();
    }
}
