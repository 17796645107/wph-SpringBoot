package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.domain.ProductAttributeRelation;
import hhxy.dn.wph.entity.Product;
import hhxy.dn.wph.entity.ProductNum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static hhxy.dn.wph.util.DBTableUtil.*;

/**
 * @Author: 邓宁
 * @Date: Created in 21:47 2018/11/19
 */
//商品动态SQL类
public class ProductProvider {

    //根据一级目录查询所有的商品尺寸
    public String findAllProductSizeByPrimaryCategoryId(Integer primary_id){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT distinct size " +
                    "FROM tb_product_size " +
                    "WHERE product_id in (" +
                                        "SELECT product_id " +
                                        "FROM tb_product " +
                                        "WHERE category_id in (" +
                                                            "SELECT category_id " +
                                                            "FROM tb_category " +
                                                            "WHERE parent_id=#{primary_id}" +
                                        ")" +
                    ")" +
                    "ORDER BY size_id");
        return sql.toString();
    }

    public String saveProduct(Product product){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT);
                VALUES("product_id","#{product_id}");
                VALUES("seller_id","#{seller_id}");
                VALUES("secondary_id","#{secondary_id}");
                VALUES("title","#{title}");
                VALUES("detail","#{detail}");
                VALUES("price","#{price}");
                VALUES("collect","#{collect}");
                VALUES("is_hot","#{is_hot}");
                VALUES("is_new","#{is_new}");
                VALUES("status","#{status}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String findProductNum(ProductNum productNum){
        return new SQL(){
            {
                SELECT("num");
                FROM(PRODUCT_NUM);
                if (!StringUtils.isBlank(productNum.getProduct_color())){
                    WHERE("product_color = #{product_color}");
                }
                if (!StringUtils.isBlank(productNum.getProduct_size())){
                    WHERE("product_size = #{product_size}");
                }
                WHERE("product_id = #{product_id}");
            }
        }.toString();
    }

    public String updateProductNum(
            @Param("product_id") Integer product_id,
            @Param("product_color") String product_color,
            @Param("product_size") String product_size,
            @Param("product_number") Integer product_number){

        return new SQL(){
            {
                UPDATE(PRODUCT_NUM);
                SET("num = num - #{product_number}");
                WHERE("product_color = #{product_color}");
                WHERE("product_size = #{product_size}");
                WHERE("product_id = #{product_id}");
            }
        }.toString();
    }

    public String findProductByArrtibute1(@Param("attributeRelation") ProductAttributeRelation attributeRelation,
                                         @Param("productIdArray") Integer[] productIdList){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(
                "select product_id from "+ PRODUCT_ATTRIBUTE_RELATION +
                "where attribute_id = #{attributeRelation.attributeId} "+
                "and value_id = #{attributeRelation.valueId} " +
                "and product_id in ("
        );
        for (int i = 0 ,l = productIdList.length;i < l;i++) {
            stringBuffer.append(productIdList[i]);
            if (i == l-1){
                break;
            }
            stringBuffer.append(",");
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public String findProductInSeller(
            @Param("seller_id") Integer seller_id,@Param("secoundCategoryId") Integer secoundCategoryId,
            @Param("size_id")Integer size_id,@Param("type") Integer type, @Param("hasNum")Integer hasNum){
        return new SQL(){
            {
                SELECT("p.*");
                FROM("tb_product p ,tb_product_size s,tb_product_num n");
                WHERE("p.product_id = s.product_id");
                WHERE("p.product_id = n.product_id");
                WHERE("p.seller_id = #{seller_id}");
                if (secoundCategoryId != null){
                    WHERE("p.category_id = #{secoundCategoryId}");
                }
                if (size_id != null){
                    WHERE("s.size_id = #{size_id}");
                }
                if (hasNum != null && hasNum == 1){
                    WHERE("n.num > 0");
                }
                if (type != null){
                    switch (type){
                        case 1:
                            ORDER_BY("p.price");
                            break;
                        case 2:
                            ORDER_BY("p.collect");
                            break;
                        default:
                    }
                }
            }
        }.toString();
    }
    /*SELECT p.*, s.*,n.*
    FROM tb_product p ,tb_product_size s,tb_product_num n
    where p.product_id = s.product_id and p.product_id = n.product_id
    and p.seller_id = 1
    and p.category_id = 43
    and s.size_id = 139
    and n.num > 0
    ORDER BY p.price*/
}
