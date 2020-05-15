package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.util.DateUtil;
import org.apache.ibatis.jdbc.SQL;
import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
/**
 * @author 邓宁
 * @date Created in 21:09 2019/4/28
 */
public class GoodCartProvider {

    /**
     * 添加购物车
     * @param goodCart
     * @return java.lang.String
     */
    public String saveGoodCart(Cart goodCart){
        goodCart.setCreated(DateUtil.getDate());
        return new SQL(){
            {
                INSERT_INTO(CART);
                VALUES("user_id","#{userId}");
                VALUES("product_id","#{productId}");
                VALUES("product_number","#{productNumber}");
                VALUES("product_color","#{productColor}");
                VALUES("product_size","#{productSize}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    /**
     * 更新购物车
     * @param goodCart
     * @return java.lang.String
     */
    public String updateGoodCartById(Cart goodCart){
        return new SQL(){
            {
                UPDATE(CART);
                if (goodCart.getProductNumber() != null){
                    SET("product_number = #{productNumber}");
                }
                if (goodCart.getProductSize() != null){
                    SET("product_size = #{productSize}");
                }
                if (goodCart.getProductColor() != null){
                    SET("product_color = #{productColor}");
                }
                SET("updated = " + DateUtil.getDate());
                WHERE("user_id = #{userId}");
            }
        }.toString();
    }
}
