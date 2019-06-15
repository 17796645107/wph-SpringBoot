package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.GoodCart;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 22:10 2019/4/28
 */

public interface GoodCartService {

    void saveGoodCart(GoodCart goodCart);

    //查询购物车列表
    List<GoodCart> getGoodCart(Integer userNo);

    void deleteGoodCartById(Integer id);

    Integer getCartCount(Integer userNo);

    List<GoodCart> getGoodCartByIdList(int[] idList);

    void updateGoodCartById(GoodCart goodCart);
}
