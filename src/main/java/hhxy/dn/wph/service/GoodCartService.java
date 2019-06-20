package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Cart;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 22:10 2019/4/28
 */

public interface GoodCartService {

    /**
     * 添加一条购物车记录
     * @param goodCart
     * @return void
     */
    void saveGoodCart(Cart goodCart);

    /**
     *  查询购物车列表
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.Cart>
     */
    List<Cart> listGoodCartByUserId(Integer userId);

    /**
     * 删除一条购物车记录
     * @param id
     * @return void
     */
    void deleteGoodCartById(Integer id);

    /**
     * 查询购物车记录数量
     * @param userId
     * @return java.lang.Integer
     */
    Integer getCartCount(Integer userId);

    /**
     * 查询购物车列表
     * @param idList 购物车ID数组
     * @return java.util.List<hhxy.dn.wph.entity.Cart>
     */
    List<Cart> listGoodCartByIdList(int[] idList);

    /**
     * 更新购物车
     * @param goodCart
     * @return void
     */
    void updateGoodCartById(Cart goodCart);
}
