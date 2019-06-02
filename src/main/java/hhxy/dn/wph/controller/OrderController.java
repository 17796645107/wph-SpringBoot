package hhxy.dn.wph.controller;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.service.OrderService;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 13:48 2018/11/12
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
     * @Description:创建订单
     * @param: [order]
     * @return: [order_no]订单编号
     */
    @RequestMapping("/createdOrder")
    public Result createOrder(@RequestBody Order order){
        Integer order_no = orderService.createOrder(order);
        return ResultUtil.success(order_no);
    }

    /*
     * @Description:获取订单信息列表
     * @param: [userId]用户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getOrderByUserID/{userId}")
    public Result getOrderByUserID(@PathVariable Integer userId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "4",required = false)Integer countOfPage){
        PageInfo<Order> orderList = orderService.getOrderByUserID(userId,page,countOfPage);
        return ResultUtil.success(orderList);
    }

    /*
     * @Description:获取一条订单信息
     * @param: [orderNO]订单编号
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/getOrderByOrderNO/{orderNO}")
    public Result getOrderByOrderNO(@PathVariable Integer orderNO){
        Order order = orderService.getOrderByOrderNO(orderNO);
        return ResultUtil.success(order);
    }

    @RequestMapping("/deleteOrderByNO/{orderNO}")
    public Result deleteOrderByNO(@PathVariable Integer orderNO){
        orderService.deleteOrderByNO(orderNO);
        return ResultUtil.success();
    }
}
