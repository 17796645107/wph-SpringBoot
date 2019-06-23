package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.*;
import org.apache.ibatis.jdbc.SQL;
import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

/**
 * @Author: 邓宁
 * @Date: Created in 10:41 2019/5/24
 */

public class SellerProvider {

    public String saveProduct(Product product){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT);
                VALUES("product_id","#{product_id}");
                VALUES("seller_id","#{seller_id}");
                VALUES("brand_id","#{brand_id}");
                VALUES("category_id","#{category_id}");
                VALUES("title","#{title}");
                VALUES("detail","#{detail}");
                VALUES("price","#{price}");
                VALUES("collect","#{collect}");
//                VALUES("default_image","#{default_image}");
                VALUES("is_hot","#{is_hot}");
                VALUES("is_new","#{is_new}");
                VALUES("status","#{status}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductSize(ProductSize productSize){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_SIZE);
                VALUES("product_id","#{product_id}");
                VALUES("size","#{size}");
                VALUES("status","#{status}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductColor(ProductColor productColor){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_COLOR);
                VALUES("product_id","#{product_id}");
                VALUES("color","#{color}");
                VALUES("status","#{status}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductImage(ProductImage productImage){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_IMAGE);
                VALUES("product_id","#{product_id}");
                VALUES("image","#{image}");
                VALUES("color_id","#{color_id}");
                VALUES("status","#{status}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String saveProductNum(ProductNum productNum){
        return new SQL(){
            {
                INSERT_INTO(PRODUCT_NUM);
                VALUES("product_id","#{product_id}");
                VALUES("product_color","#{product_color}");
                VALUES("product_size","#{product_size}");
                VALUES("num","#{num}");
                VALUES("created","#{created}");
            }
        }.toString();
    }
}
