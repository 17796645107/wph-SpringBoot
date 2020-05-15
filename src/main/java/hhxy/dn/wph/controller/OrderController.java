package hhxy.dn.wph.controller;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.service.OrderService;
import hhxy.dn.wph.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 邓宁
 * @date Created in 13:48 2018/11/12
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     * @param order 订单信息
     * @return 订单编号
     */
    @RequestMapping("/createdOrder")
    public Result createOrder(@RequestBody Order order){
        Integer orderId = orderService.saveOrder(order);
        return ResultUtil.success(orderId);
    }

    /**
     * 获取订单信息列表
     * @param userId 用户ID
     * @return Result
     */
    @RequestMapping("/getOrderByUserID/{userId}")
    public Result listOrderByUserId(@PathVariable Integer userId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "4",required = false)Integer countOfPage){
        PageInfo<Order> orderList = orderService.listOrderPage(userId,page,countOfPage);
        return ResultUtil.success(orderList);
    }

    /**
     * 获取一条订单信息
     * @param orderNo 订单编号
     * @return Result
     */
    @GetMapping("/getOrderByOrderNO/{orderNo}")
    public Result getOrderByOrderNo(@PathVariable Integer orderNo){
        Order order = orderService.getOrderByNo(orderNo);
        return ResultUtil.success(order);
    }

    /**
     * 删除订单
     * @param orderNo
     * @return Result
     */
    @RequestMapping("/deleteOrderByNO/{orderNO}")
    public Result deleteOrderByNo(@PathVariable Integer orderNo){
        orderService.deleteOrderByNo(orderNo);
        return ResultUtil.success();
    }
}
