package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.GoodCartExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.GoodCartException;
import hhxy.dn.wph.mapper.CartMapper;
import hhxy.dn.wph.service.GoodCartService;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 邓宁
 * @date Created in 13:22 2019/4/12
 */

@Service
public class GoodCartServiceImpl implements GoodCartService {

    @Autowired
    private CartMapper goodCartMapper;

    /**
     * 添加商品到购物车
     * @param goodCart 购物车信息
     */
    @Override
    public void saveGoodCart(Cart goodCart) {
        //判断购物车是否存在了这个商品
        Integer cartId = goodCartMapper.getCartId(goodCart);
        //此购物车已存在
        if(cartId != null){
            //只更新购物车数量
            int result = goodCartMapper.updateGoodCartNumber(cartId,goodCart.getProductNumber());
            if (result != 1){
                throw new GoodCartException(GoodCartExceptionEnum.GOODCART_ADD_EXCEPTION);
            }
        }
        //此购物车不存在,将商品添加到购物车
        else{
            int result = goodCartMapper.saveCart(goodCart);
            if (result != 1){
                throw new GoodCartException(GoodCartExceptionEnum.GOODCART_ADD_EXCEPTION);
            }
        }
    }

    /**
     * 根据用户ID获取购物车信息
     * @param userId 用户Id
     * @return List<Cart>
     */
    @Override
    public List<Cart> listGoodCartByUserId(Integer userId){
        List<Cart> cartList = goodCartMapper.listGoodCartByUserId(userId);
        if (cartList.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        return cartList;
    }

    /**
     * 删除一条购物车记录
     * @param id 购物车Id
     */
    @Override
    public void deleteGoodCartById(Integer id) {
        int result = goodCartMapper.deleteGoodCartById(id);
        if (result != 1 ){
            throw new GoodCartException(GoodCartExceptionEnum.DELETE_CART_ERROR);
        }
    }

    /**
     * 获取购物车数量
     * @param userId 用户Id
     * @return java.lang.Integer
     */
    @Override
    public Integer getCartCount(Integer userId) {
        return goodCartMapper.getCartCount(userId);
    }

    /**
     * 获取购物车列表
     * @param idList 购物车ID数组
     * @return List<Cart>
     */
    @Override
    public List<Cart> listGoodCartByIdList(int[] idList) {
        List<Cart> goodCartList = new ArrayList<>();
        for (Integer id:idList) {
            goodCartList.add(goodCartMapper.getGoodCartById(id));
        }
        return goodCartList;
    }

    /**
     * 更新购物车
     * @param goodCart 购物车信息
     */
    @Override
    public void updateGoodCartById(Cart goodCart) {
        Integer result = goodCartMapper.updateGoodCartById(goodCart);
        if (result != 1){
            throw new GoodCartException(GoodCartExceptionEnum.GOODCART_UPDATE_EXCEPTION);
        }
    }
}
