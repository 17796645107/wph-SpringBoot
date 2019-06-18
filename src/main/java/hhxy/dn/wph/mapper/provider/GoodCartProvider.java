package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.util.DateUtil;
import org.apache.ibatis.jdbc.SQL;
import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
/**
 * @Author: 邓宁
 * @Date: Created in 21:09 2019/4/28
 */

public class GoodCartProvider {

    public String saveGoodCart(Cart goodCart){
        return new SQL(){
            {
                INSERT_INTO(CART);
                VALUES("user_no","#{userNo}");
                VALUES("product_id","#{product_id}");
                VALUES("product_number","#{product_number}");
                VALUES("product_color","#{product_color}");
                VALUES("product_size","#{product_size}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    public String updateGoodCartById(Cart goodCart){
        return new SQL(){
            {
                UPDATE(CART);
                if (goodCart.getProductNumber() != null){
                    SET("product_number = #{product_number}");
                }
                if (goodCart.getProductSize() != null){
                    SET("product_size = #{product_size}");
                }
                if (goodCart.getProductColor() != null){
                    SET("product_color = #{product_color}");
                }
                SET("updated = "+ DateUtil.getDate());
                WHERE("user_id = #{user_id}");
            }
        }.toString();
    }
}
