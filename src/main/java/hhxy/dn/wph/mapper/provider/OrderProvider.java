package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.GoodCart;
import hhxy.dn.wph.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import static hhxy.dn.wph.util.DBTableUtil.*;

/**
 * @Author: 邓宁
 * @Date: Created in 15:19 2019/5/3
 */

public class OrderProvider {

    public String createOrder(Order order){
        return new SQL(){
            {
                INSERT_INTO(ORDER);
                VALUES("order_id","#{order_id}");
                VALUES("user_id","#{user_id}");
                VALUES("address_id","#{address_id}");
                VALUES("product_total","#{product_total}");
                VALUES("order_count","#{order_count}");
                VALUES("pay_channel","#{pay_channel}");
                VALUES("pay_no","#{pay_no}");
                VALUES("status","#{status}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    //订单商品详情,@Param对应OrderMapper接口的参数
    public String saveOrderProductDetail(@Param("goodCart") GoodCart goodCart, @Param("order_no") Integer order_no){
        return new SQL(){
            {
                INSERT_INTO(ORDER_PRODUCT);
                VALUES("product_id","#{goodCart.product.product_id}");
                VALUES("order_id","#{order_no}");
                VALUES("product_number","#{goodCart.product_number}");
                VALUES("product_size","#{goodCart.product_size}");
                VALUES("product_color","#{goodCart.product_color}");
            }
        }.toString();
    }
}
