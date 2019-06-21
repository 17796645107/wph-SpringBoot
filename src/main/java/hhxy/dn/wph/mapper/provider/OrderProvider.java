package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

/**
 * @Author: 邓宁
 * @Date: Created in 15:19 2019/5/3
 */

public class OrderProvider {

    /**
     * 创建订单
     * @param order
     * @return java.lang.String
     */
    public String saveOrder(Order order){
        return new SQL(){
            {
                INSERT_INTO(ORDER);
                VALUES("id","#{id}");
                VALUES("user_id","#{userId}");
                VALUES("address_id","#{addressId}");
                VALUES("product_total","#{productTotal}");
                VALUES("order_count","#{orderCount}");
                VALUES("pay_channel","#{payChannel}");
                VALUES("pay_no","#{payNo}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    /**
     * 订单商品详情,@Param对应OrderMapper接口的参数
     * @param goodCart
     * @param orderId
     * @return java.lang.String
     */
    public String saveOrderProductDetail(@Param("goodCart") Cart goodCart, @Param("orderId") Integer orderId){
        return new SQL(){
            {
                INSERT_INTO(ORDER_PRODUCT);
                VALUES("product_id","#{goodCart.product.productId}");
                VALUES("order_id","#{orderId}");
                VALUES("product_number","#{goodCart.productNumber}");
                VALUES("product_size","#{goodCart.productSize}");
                VALUES("product_color","#{goodCart.productColor}");
            }
        }.toString();
    }
}
