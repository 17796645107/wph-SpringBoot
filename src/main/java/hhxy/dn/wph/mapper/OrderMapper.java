package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.mapper.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 14:54 2019/5/3
 */

public interface OrderMapper {

    @InsertProvider(type = OrderProvider.class,method = "createOrder")
    @Options(keyProperty = "order_no",useGeneratedKeys = true)
    int createOrder(Order order);

    @InsertProvider(type = OrderProvider.class,method = "saveOrderProductDetail")
    int saveOrderProductDetail(@Param("goodCart") Cart goodCart, @Param("order_no") Integer order_no);

    @Select("select * from"+ORDER+" where user_id = #{userId}")
    @Results({
            @Result(property = "goodCartList",column = "order_no",
                    many = @Many(
                            select = "hhxy.dn.wph.mapper.OrderMapper.getProductDetailByOrderID",
                            fetchType = FetchType.EAGER
                    ))
    })
    List<Order> getOrderByUserID(Integer userId);

    @Results({
            @Result(column = "product_id",property = "product",
            one = @One(
                    //查询商品信息
                    select = "hhxy.dn.wph.mapper.ProductMapper.getProductByProductId",
                    //查询类型:立即加载
                    fetchType = FetchType.EAGER
            ))
    })
    @Select("select * from"+ORDER_PRODUCT+"where order_id = #{order_id}")
    List<Cart> getProductDetailByOrderID(Integer order_id);

    @Select("select * from"+ORDER+"where order_no = #{orderNO}")
    Order getOrderByOrderNO(Integer orderNO);

    @Delete("delete from " +ORDER+"where order_no = #{orderNO}")
    Integer deleteOrderByNO(Integer orderNO);

    @Select("select * from"+ ORDER +"where order_id = #{out_trade_no}")
    Order getOrderByOrderId(String out_trade_no);

    @Update("update"+ ORDER +"set pay_no = #{trade_no},pay_channel = 1,status = 2 where order_id = #{out_trade_no}")
    int updateOrderSetPayNo(@Param("out_trade_no") String out_trade_no,@Param("trade_no") String trade_no);
}