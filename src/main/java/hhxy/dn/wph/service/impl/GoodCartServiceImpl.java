package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.GoodCart;
import hhxy.dn.wph.enums.GoodCartExceptionEnum;
import hhxy.dn.wph.exception.GoodCartException;
import hhxy.dn.wph.mapper.CartMapper;
import hhxy.dn.wph.service.GoodCartService;
import hhxy.dn.wph.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 13:22 2019/4/12
 */

@Service
public class GoodCartServiceImpl implements GoodCartService {

    @Autowired
    private CartMapper goodCartMapper;

    //添加商品到购物车
    @Override
    public void saveGoodCart(GoodCart goodCart) {
        //判断购物车是否存在了这个商品
        Integer cart_id = goodCartMapper.selectGoodCart(goodCart);
        //存在
        if(cart_id != null){
            //只更新购物车数量
            Integer result = goodCartMapper.updateGoodCartNumber(cart_id,goodCart.getProduct_number());
        }else{
            //不存在,将商品添加到购物车
            goodCart.setCreated(DateUtil.getDate());
            Integer result = goodCartMapper.insert(goodCart);
            if (result != 1){
                throw new GoodCartException(GoodCartExceptionEnum.GOODCART_ADD_EXCEPTION);
            }
        }
    }

    /*
     * @Description:根据用户ID获取购物车信息
     * @param: [userId]
     * @return: java.util.List<hhxy.dn.wph.entity.GoodCart>
     */
    @Override
    public List<GoodCart> getGoodCart(Integer userId){
        return goodCartMapper.getGoodCart(userId);
    }

    /*
     * @Description:删除一条购物车记录
     * @param: [id]
     * @return: void
     */
    @Override
    public void deleteGoodCartById(Integer id) {
        int result = goodCartMapper.deleteGoodCartById(id);
        if (result != 1 ){
            throw new GoodCartException(GoodCartExceptionEnum.deleteCartById_error);
        }
    }

    /*
     * @Description:获取购物车数量
     * @param: [userId]
     * @return: java.lang.Integer
     */
    @Override
    public Integer getCartCount(Integer userId) {
        return goodCartMapper.getCartCount(userId);
    }

    /*
     * @Description:获取购物车列表
     * @param: [idList]购物车ID数组
     * @return: java.util.List<hhxy.dn.wph.entity.GoodCart>
     */
    @Override
    public List<GoodCart> getGoodCartByIdList(int[] idList) {
        List<GoodCart> goodCartList = new ArrayList<>();
        for (Integer id:idList) {
            goodCartList.add(goodCartMapper.getOneGoodCart(id));
        }
        return goodCartList;
    }

    @Override
    public void updateGoodCartById(GoodCart goodCart) {
        Integer result = goodCartMapper.updateGoodCartById(goodCart);
        if (result != 1){
            throw new GoodCartException(GoodCartExceptionEnum.GOODCART_UPDATE_EXCEPTION);
        }
    }
}
