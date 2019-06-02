package hhxy.dn.wph.service;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Order;

/**
 * @Author: 邓宁
 * @Date: Created in 14:53 2019/5/3
 */

public interface OrderService {

    Integer createOrder(Order order);

    PageInfo<Order> getOrderByUserID(Integer userId, Integer page, Integer countOfPage);

    Order getOrderByOrderNO(Integer orderNO);

    void deleteOrderByNO(Integer orderNO);

    Order getOrderByOrderId(String out_trade_no);

    int updateOrderSetPayNo(String out_trade_no,String trade_no);
}
