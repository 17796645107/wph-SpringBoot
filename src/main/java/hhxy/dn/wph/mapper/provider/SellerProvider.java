package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

/**
 * @author 邓宁
 * @date Created in 10:41 2019/5/24
 */

public class SellerProvider {

    public String saveProduct(Product product){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT);
                VALUES("product_no","#{productNo}");
                VALUES("seller_id","#{sellerId}");
                VALUES("brand_id","#{brandId}");
                VALUES("category_id","#{categoryId}");
                VALUES("title","#{title}");
                VALUES("detail","#{detail}");
                VALUES("price","#{price}");
                VALUES("collect","#{collect}");
                VALUES("is_hot","#{isHot}");
                VALUES("is_new","#{isNew}");
                VALUES("state","#{state}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductSize(ProductSize productSize){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_SIZE);
                VALUES("product_id","#{productId}");
                VALUES("size","#{size}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductColor(ProductColor productColor){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_COLOR);
                VALUES("product_id","#{productId}");
                VALUES("color","#{color}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductImage(ProductImage productImage){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_IMAGE);
                VALUES("product_id","#{productId}");
                VALUES("image","#{image}");
                VALUES("color_id","#{colorId}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductNum(ProductNum productNum){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_NUM);
                VALUES("product_id","#{productId}");
                VALUES("product_color","#{productColor}");
                VALUES("product_size","#{productSize}");
                VALUES("num","#{num}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String updateProduct(Product product) {
        return new SQL() {
            {
                UPDATE(PRODUCT);
                if (!StringUtils.isEmpty(product.getProductNo())) {
                    SET("product_no = #{productNo}");
                }
                if (product.getSellerId() != null) {
                    SET("seller_id = #{sellerId}");
                }
                if (product.getBrandId() != null) {
                    SET("brand_id = #{brandId}");
                }
                if (product.getCategoryId() != null) {
                    SET("category_id = #{categoryId}");
                }
                if (!StringUtils.isEmpty(product.getTitle())) {
                    SET("title = #{title}");
                }
                if (!StringUtils.isEmpty(product.getDetail())) {
                    SET("detail = #{detail}");
                }
                if (product.getPrice() != null) {
                    SET("price = #{price}");
                }
                if (product.getCollect() != null) {
                    SET("collect = #{collect}");
                }
                if (product.getIsHot() != null) {
                    SET("is_hot = #{isHot}");
                }
                if (product.getIsNew() != null) {
                    SET("is_new = #{isNew}");
                }
                if (product.getState() != null) {
                    SET("state = #{state}");
                }
                    WHERE("id = #{id}");
            }
        }.toString();
    }
}
