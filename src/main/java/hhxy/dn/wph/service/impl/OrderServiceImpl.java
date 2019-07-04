package hhxy.dn.wph.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.entity.Order;
import hhxy.dn.wph.entity.ProductNum;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.GoodCartExceptionEnum;
import hhxy.dn.wph.enums.OrderExceptionEnum;
import hhxy.dn.wph.enums.ProductExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
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

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @Author: 邓宁
 * @Date: Created in 14:53 2019/5/3
 * 订单业务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 创建订单
     * @param order
     * @return java.lang.Integer
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveOrder(Order order) {
        //检查库存
        checkProductNum(order);

        //创建订单
        order.setOrderNo(IDUtil.createOrderID());
        order.setStatus(1);
        order.setCreated(DateUtil.getDate());
        int result = orderMapper.saveOrder(order);
        if (result != 1){
            throw new OrderException(OrderExceptionEnum.CREATE_ORDER_ERROR);
        }

        //生成订单详情
        List<Cart> goodCarts = order.getGoodCartList();
        for (Cart goodCart:goodCarts){
            //生成订单详情记录
            int results = orderMapper.saveOrderProductDetail(goodCart,order.getId());
            if (results != 1){
                throw new OrderException(OrderExceptionEnum.ORDER_EXCEPTION_ENUM);
            }
            //删除购物车的记录
            int results1 = cartMapper.deleteGoodCartById(goodCart.getId());
            if (results1 != 1){
                throw new GoodCartException(GoodCartExceptionEnum.DELETE_CART_ERROR);
            }
            //更新商品库存
            int results2 = productMapper.updateProductNum(goodCart.getProduct().getId(),goodCart.getProductColor(),goodCart.getProductSize(),goodCart.getProductNumber());
            if (results2 != 1){
                throw new ProductException(ProductExceptionEnum.UPDATE_PRODUCT_NUM_ERROR);
            }
        }
        //清除缓存
        //批量模糊删除
        Set<String> keys = redisTemplate.keys("OrderList:" + order.getUserId() + "*");
        redisTemplate.delete(keys);

        return order.getId();
    }

    /**
     * 检查商品库存
     * @param order
     * @return void
     */
    private  void checkProductNum(Order order){
        List<Cart> goodList = order.getGoodCartList();
        ProductNum productNum = new ProductNum();
        Integer num = 0;
        for(Cart goodCart:goodList){
            productNum.setProductColor(goodCart.getProductColor());
            productNum.setProductSize(goodCart.getProductSize());
            productNum.setProductId(goodCart.getProduct().getDefaultImage().getProductId());
            //查询库存
            List<Integer> numList = productMapper.listProductNum(productNum);
            for(Integer i:numList){
                num += i;
            }
            //购买数量超出库存
            if (goodCart.getProductNumber() > num){
                throw new ProductException(ProductExceptionEnum.PRODUCT_NUM_ERROR);
            }
        }
    }

    /**
     *
     * @param userId
     * @param page 当前页
     * @param countOfPage 每页显示数量
     * @return com.github.pagehelper.PageInfo<hhxy.dn.wph.entity.Order>
     */
    @Override
    public PageInfo<Order> listOrderPage(Integer userId, Integer page, Integer countOfPage) {
        //Redis key
        String cacheKey = "OrderList:" + userId + ":Page" + page;
        //查询缓存中是否存在订单缓存
        if (redisUtil.hasKey(cacheKey)){
            String cache = (String) redisUtil.get(cacheKey);
            return JsonUtil.jsonToPojo(cache,PageInfo.class);
        }
        //查询数据库
        List<Order> orderList = orderMapper.listOrderByUserId(userId);
        if (orderList.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        //开始分页
        PageHelper.startPage(page,countOfPage);
        //创建分页对象
        PageInfo<Order> orderPageInfo = new PageInfo<>(orderList);
        //把分页存入缓存
        redisUtil.set(cacheKey, JsonUtil.objectToJson(orderPageInfo));
        return orderPageInfo;
    }

    /**
     * 查询订单
     * @param orderNo
     * @return hhxy.dn.wph.entity.Order
     */
    @Override
    public Order getOrderByNo(Integer orderNo) {
        return orderMapper.getOrderByOrderNo(orderNo);
    }

    /**
     * 删除订单
     * @param orderNo
     * @return void
     */
    @Override
    public void deleteOrderByNo(Integer orderNo) {
        Integer result = orderMapper.deleteOrderByNo(orderNo);
    }

    /**
     * 查询订单
     * @param outTradeNo
     * @return hhxy.dn.wph.entity.Order
     */
    @Override
    public Order getOrderByOrderId(String outTradeNo) {
        return orderMapper.getOrderByOrderId(outTradeNo);
    }

    /**
     * ???
     * @param outTradeNo
     * @param tradeNo
     * @return int
     */
    @Override
    public int updateOrderSetPayNo(String outTradeNo,String tradeNo) {
        return orderMapper.updateOrderPayNo(outTradeNo,tradeNo);
    }
}
