package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.ProductAttributeRelation;
import hhxy.dn.wph.entity.Product;
import hhxy.dn.wph.entity.ProductNum;
import hhxy.dn.wph.entity.ProductSelectCondition;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

/**
 * @author 邓宁
 * @date Created in 21:47 2018/11/19
 * 商品动态SQL类
 */
public class ProductProvider {

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param categoryId 一级目录Id
     * @return java.lang.String
     */
    public String findAllProductSizeByPrimaryCategoryId(Integer categoryId){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT distinct size " +
                    "FROM tb_product_size " +
                    "WHERE product_id in (" +
                                        "SELECT id " +
                                        "FROM tb_product " +
                                        "WHERE category_id in (" +
                                                            "SELECT id " +
                                                            "FROM tb_category " +
                                                            "WHERE parent_id = #{categoryId}" +
                                        ")" +
                    ")" +
                    "ORDER BY id");
        return sql.toString();
    }

    /**
     * 查询商品库存
     * @param productNum 商品库存参数
     * @return java.lang.String
     */
    public String findProductNum(ProductNum productNum){
        return new SQL(){
            {
                SELECT("num");
                FROM(PRODUCT_NUM);
                if (!StringUtils.isBlank(productNum.getProductColor())){
                    WHERE("product_color = #{productColor}");
                }
                if (!StringUtils.isBlank(productNum.getProductSize())){
                    WHERE("product_size = #{productSize}");
                }
                WHERE("product_id = #{productId}");
            }
        }.toString();
    }

    /**
     * 更新商品库存
     * @param productId
     * @param productColor
     * @param productSize
     * @param productNumber
     * @return java.lang.String
     */
    public String updateProductNum(@Param("productId") Integer productId, @Param("productColor") String productColor,
            @Param("productSize") String productSize, @Param("productNumber") Integer productNumber){
        return new SQL(){
            {
                UPDATE(PRODUCT_NUM);
                SET("num = num - #{productNumber}");
                WHERE("product_color = #{productColor}");
                WHERE("product_size = #{productSize}");
                WHERE("product_id = #{productId}");
            }
        }.toString();
    }

    public String findProductByArrtibute(@Param("attributeRelation") ProductAttributeRelation attributeRelation,
                                         @Param("productIdArray") Integer[] productIdList){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                "select product_id from "+ PRODUCT_ATTRIBUTE_RELATION +
                "where attribute_id = #{attributeRelation.attributeId} "+
                "and value_id = #{attributeRelation.valueId} " +
                "and product_id in ("
        );
        for (int i = 0 ,l = productIdList.length;i < l;i++) {
            stringBuilder.append(productIdList[i]);
            if (i == l-1){
                break;
            }
            stringBuilder.append(",");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /**
     * 检索商品
     * @param condition 检索条件
     */
    public String findProductInSeller(ProductSelectCondition condition){
        return new SQL(){
            {
                SELECT_DISTINCT("p.*");
                FROM("tb_product p");
                LEFT_OUTER_JOIN("tb_product_size s on p.id = s.product_id");
                LEFT_OUTER_JOIN("tb_product_num n on p.id = n.product_id");
                WHERE("p.seller_id = #{sellerId}");
                if (condition.getCategoryId() != null){
                    WHERE("p.category_id = #{categoryId}");
                }
                if (condition.getProductSizeId() != null){
                    WHERE("s.id = #{productSizeId}");
                }
                if (condition.getHasNum() != null && condition.getHasNum() == 1){
                    WHERE("n.num > 0");
                }
                WHERE("p.state = 1");
                if (condition.getType() != null){
                    switch (condition.getType()){
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
        /*  SELECT p.*, s.*,n.*
            FROM tb_product p ,tb_product_size s,tb_product_num n
            where p.id = s.product_id and p.id = n.product_id
            and p.seller_id = 1
            and p.category_id = 43
            and s.id = 139
            and n.num > 0
            ORDER BY p.price
         */
        /*SELECT DISTINCT p.*
                FROM tb_product p
        LEFT OUTER JOIN tb_product_size s on p.id = s.product_id
        LEFT OUTER JOIN tb_product_num n on p.id = n.product_id
        WHERE p.seller_id = 1
        and p.category_id = 43
        and s.id = 139
        and n.num > 0
        ORDER BY p.price*/
    }
}
