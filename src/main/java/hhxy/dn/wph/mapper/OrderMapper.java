package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.mapper.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 14:54 2019/5/3
 */

public interface OrderMapper {
    /**
     * 查询订单
     * @param orderNo
     * @return hhxy.dn.wph.entity.Order
     */
    @Select("select * from"+ ORDER +"where id = #{orderNo}")
    @Results(id = "orderMap",value = {
        @Result(column = "order_no",property = "orderNo"),
        @Result(column = "user_id",property = "userId"),
        @Result(column = "address_id",property = "addressId"),
        @Result(column = "product_total",property = "productTotal"),
        @Result(column = "order_count",property = "orderCount")
        }
    )
    Order getOrderByOrderNo(Integer orderNo);


    /**
     * 创建订单
     * @param order
     * @return int
     */
    @InsertProvider(type = OrderProvider.class,method = "saveOrder")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    int saveOrder(Order order);

    /**
     * 创建订单商品详情
     * @param goodCart
     * @param orderId
     * @return int
     */
    @InsertProvider(type = OrderProvider.class,method = "saveOrderProductDetail")
    int saveOrderProductDetail(@Param("goodCart") Cart goodCart, @Param("orderId") Integer orderId);

    /**
     * 查询订单列表
     * @param userId 用户ID
     * @return List<Order>
     */
    @Select("select * from"+ ORDER +"where user_id = #{userId}")
    @Results({
            @Result(column = "order_no",property = "orderNo"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "address_id",property = "addressId"),
            @Result(column = "product_total",property = "productTotal"),
            @Result(column = "order_count",property = "orderCount"),
            @Result(column = "pay_channel",property = "payChannel"),
            @Result(column = "pay_no",property = "payNo"),
            //查询订单商品详情
            @Result(property = "goodCartList",column = "id",
                    many = @Many(
                            select = "hhxy.dn.wph.mapper.OrderMapper.listProductDetailByOrderId",
                            fetchType = FetchType.EAGER
                    ))
    })
    List<Order> listOrderByUserId(Integer userId);

    /**
     * 查询订单商品详情列表
     * @param orderId
     * @return List<Cart>
     */
    @Select("select * from"+ ORDER_PRODUCT +"where order_id = #{orderId}")
    @Results({
            @Result(column = "product_id",property = "product",
                    one = @One(
                            //查询商品信息
                            select = "hhxy.dn.wph.mapper.ProductMapper.getProductById",
                            //查询类型:立即加载
                            fetchType = FetchType.EAGER
                    ))
    })
    List<Cart> listProductDetailByOrderId(Integer orderId);

    /**
     * 删除订单
     * @param orderNo
     * @return java.lang.Integer
     */
    @Delete("delete from " + ORDER +"where order_no = #{orderNo}")
    Integer deleteOrderByNo(Integer orderNo);

    /**
     * 查询订单
     * @param outTradeNo
     * @return hhxy.dn.wph.entity.Order
     */
    @Select("select * from"+ ORDER +"where id = #{outTradeNo}")
    Order getOrderByOrderId(String outTradeNo);

    /**
     * 填写订单支付流水号
     * @param outTradeNo
     * @param tradeNo
     * @return int
     */
    @Update("update"+ ORDER +"set pay_no = #{tradeNo},pay_channel = 1,status = 2 where order_id = #{outTradeNo}")
    int updateOrderPayNo(@Param("outTradeNo") String outTradeNo, @Param("tradeNo") String tradeNo);
}