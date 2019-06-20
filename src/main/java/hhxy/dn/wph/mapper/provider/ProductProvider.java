package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.ProductAttributeRelation;
import hhxy.dn.wph.entity.Product;
import hhxy.dn.wph.entity.ProductNum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

/**
 * @Author: 邓宁
 * @Date: Created in 21:47 2018/11/19
 * 商品动态SQL类
 */
public class ProductProvider {

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param primary_id
     * @return java.lang.String
     */
    public String findAllProductSizeByPrimaryCategoryId(Integer categotyId){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT distinct size " +
                    "FROM tb_product_size " +
                    "WHERE product_id in (" +
                                        "SELECT id " +
                                        "FROM tb_product " +
                                        "WHERE category_id in (" +
                                                            "SELECT id " +
                                                            "FROM tb_category " +
                                                            "WHERE parent_id = #{categotyId}" +
                                        ")" +
                    ")" +
                    "ORDER BY id");
        return sql.toString();
    }

    /**
     * 查询商品库存
     * @param productNum
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
    public String updateProductNum(
            @Param("productId") Integer productId,
            @Param("productColor") String productColor,
            @Param("productSize") String productSize,
            @Param("productNumber") Integer productNumber){

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

    /**
     * 检索商品
     * @param sellerId
     * @param categoryId
     * @param sizeId
     * @param type 排序类型 1:价格,2:收藏
     * @param hasNum 是否有库存
     * @return java.util.List<hhxy.dn.wph.entity.Product>
     */
    public String findProductInSeller(
            @Param("sellerId") Integer sellerId,@Param("categoryId") Integer categoryId,
            @Param("sizeId")Integer sizeId,@Param("type") Integer type, @Param("hasNum")Integer hasNum){
        return new SQL(){
            {
                SELECT("p.*");
                FROM("tb_product p ,tb_product_size s,tb_product_num n");
                WHERE("p.product_id = s.product_id");
                WHERE("p.product_id = n.product_id");
                WHERE("p.seller_id = #{sellerId}");
                if (categoryId != null){
                    WHERE("p.category_id = #{categoryId}");
                }
                if (sizeId != null){
                    WHERE("s.size_id = #{sizeId}");
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
        /*  SELECT p.*, s.*,n.*
            FROM tb_product p ,tb_product_size s,tb_product_num n
            where p.product_id = s.product_id and p.product_id = n.product_id
            and p.seller_id = 1
            and p.category_id = 43
            and s.size_id = 139
            and n.num > 0
            ORDER BY p.price
         */
    }
}
