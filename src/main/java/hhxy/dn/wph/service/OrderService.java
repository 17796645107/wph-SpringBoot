package hhxy.dn.wph.service;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Order;

/**
 * @author 邓宁
 * @date Created in 14:53 2019/5/3
 */

public interface OrderService {

    /**
     *
     * @param order
     * @return java.lang.Integer
     */
    Integer saveOrder(Order order);

    /**
     *
     * @param userId
     * @param page
     * @param countOfPage
     * @return com.github.pagehelper.PageInfo<hhxy.dn.wph.entity.Order>
     */
    PageInfo<Order> listOrderPage(Integer userId, Integer page, Integer countOfPage);

    /**
     * 查询订单
     * @param orderNo 订单编号
     * @return hhxy.dn.wph.entity.Order
     */
    Order getOrderByNo(Integer orderNo);

    /**
     * 删除订单
     * @param orderNo 订单编号

     */
    void deleteOrderByNo(Integer orderNo);

    /**
     * 查询订单
     * @param outTradeNo 订单ID
     * @return hhxy.dn.wph.entity.Order
     */
    Order getOrderByOrderId(String outTradeNo);

    /**
     * ????
     * @param outTradeNo
     * @param tradeNo
     * @return int
     */
    int updateOrderSetPayNo(String outTradeNo,String tradeNo);
}
