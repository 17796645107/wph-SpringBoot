package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Cart;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 22:10 2019/4/28
 */

public interface GoodCartService {

    /**
     * 添加一条购物车记录
     * @param goodCart

     */
    void saveGoodCart(Cart goodCart);

    /**
     *  查询购物车列表
     * @param userId
     * @return List<Cart>
     */
    List<Cart> listGoodCartByUserId(Integer userId);

    /**
     * 删除一条购物车记录
     * @param id

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
     * @return List<Cart>
     */
    List<Cart> listGoodCartByIdList(int[] idList);

    /**
     * 更新购物车
     * @param goodCart

     */
    void updateGoodCartById(Cart goodCart);
}
