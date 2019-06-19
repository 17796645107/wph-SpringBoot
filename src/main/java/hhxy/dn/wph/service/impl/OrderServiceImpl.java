package hhxy.dn.wph.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.entity.ProductNum;
import hhxy.dn.wph.enums.GoodCartExceptionEnum;
import hhxy.dn.wph.enums.OrderExceptionEnum;
import hhxy.dn.wph.enums.ProductExceptionEnum;
import hhxy.dn.wph.exception.GoodCartException;
import hhxy.dn.wph.exception.OrderException;
import hhxy.dn.wph.exception.ProductException;
import hhxy.dn.wph.mapper.CartMapper;
import hhxy.dn.wph.mapper.OrderMapper;
import hhxy.dn.wph.mapper.ProductMapper;
import hhxy.dn.wph.service.OrderService;
import hhxy.dn.wph.util.DateUtil;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.IDUtil;
import hhxy.dn.wph.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: 邓宁
 * @Date: Created in 14:53 2019/5/3
 */
//订单业务实现类
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /*
     * @Description:创建订单
     */
    @Override
    @Transactional
    public Integer createOrder(Order order) {
        //检查库存
        checkProductNum(order);

        //创建订单
        order.setOrderNo(IDUtil.createOrderID());//生成订单号
        order.setStatus(1);//订单状态
        order.setCreate(new Date());//创建订单时间
        int result = orderMapper.createOrder(order);
        if (result != 1){
            throw new OrderException(OrderExceptionEnum.createOrder_error);
        }

        //生成订单详情
        List<Cart> goodCarts = order.getGoodCartList();
        for (Cart goodCart:goodCarts){
            //生成订单详情记录
            int results = orderMapper.saveOrderProductDetail(goodCart,order.getId());
            if (results != 1){
                throw new OrderException(OrderExceptionEnum.createOrderProductDeatil_error);
            }
            //删除购物车的记录
            int results1 = cartMapper.deleteGoodCartById(goodCart.getId());
            if (results1 != 1){
                throw new GoodCartException(GoodCartExceptionEnum.deleteCartById_error);
            }
            //更新商品库存
            int results2 = productMapper.updateProductNum(goodCart.getProduct().getId(),goodCart.getProductColor(),goodCart.getProductSize(),goodCart.getProductNumber());
            if (results2 != 1){
                throw new ProductException(ProductExceptionEnum.updateProductNum_error);
            }
        }
        //清除缓存
        //批量模糊删除
        Set<String> keys = redisTemplate.keys("OrderList:" + order.getUserId() + "*");
        redisTemplate.delete(keys);

        return order.getId();
    }

    private  void checkProductNum(Order order){
        List<Cart> goodList = order.getGoodCartList();
        ProductNum productNum = new ProductNum();
        Integer num = 0;
        for(Cart goodCart:goodList){
            productNum.setProductColor(goodCart.getProductColor());
            productNum.setProductSize(goodCart.getProductSize());
            productNum.setProductId(goodCart.getProduct().getId());
            //查询库存
            List<Integer> numList = productMapper.listProductNum(productNum);
            for(Integer i:numList){
                num += i;
            }
            //购买数量超出库存
            if (goodCart.getProductNumber() > num){
                throw new ProductException(ProductExceptionEnum.FIND_SECONDARTID_BY_PARENTID_ERROR);
            }
        }
    }

    @Override
    public PageInfo<Order> getOrderByUserID(Integer userId, Integer page, Integer countOfPage) {
        String orders = (String) redisUtil.get("OrderList:"+userId+":Page"+page);
        if (orders != null){
            return JsonUtil.jsonToPojo(orders,PageInfo.class);
        }
        //开始分页
        PageHelper.startPage(page,countOfPage);
        //查询数据库
        List<Order> orderList = orderMapper.getOrderByUserID(userId);
        //创建分页对象
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        //把分页存入缓存
        redisUtil.set("OrderList:"+userId+":Page"+page, JsonUtil.objectToJson(orderPageInfo));
        return orderPageInfo;
    }

    @Override
    public Order getOrderByOrderNO(Integer orderNO) {
        return orderMapper.getOrderByOrderNO(orderNO);
    }

    @Override
    public void deleteOrderByNO(Integer orderNO) {
        Integer result = orderMapper.deleteOrderByNO(orderNO);
    }

    @Override
    public Order getOrderByOrderId(String out_trade_no) {
        return orderMapper.getOrderByOrderId(out_trade_no);
    }

    @Override
    public int updateOrderSetPayNo(String out_trade_no,String trade_no) {
        return orderMapper.updateOrderSetPayNo(out_trade_no,trade_no);
    }
}
