package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.Cart;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 22:10 2019/4/28
 */

public interface GoodCartService {

    //添加一条购物车记录
    void saveGoodCart(Cart goodCart);

    //查询购物车列表
    List<Cart> getGoodCart(Integer userNo);

    void deleteGoodCartById(Integer id);

    Integer getCartCount(Integer userNo);

    List<Cart> getGoodCartByIdList(int[] idList);

    void updateGoodCartById(Cart goodCart);
}
