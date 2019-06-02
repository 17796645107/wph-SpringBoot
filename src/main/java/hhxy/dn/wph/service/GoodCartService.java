package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.GoodCart;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 22:10 2019/4/28
 */

public interface GoodCartService {

    void saveGoodCart(GoodCart goodCart);

    List<GoodCart> getGoodCart(Integer userId);

    void deleteGoodCartById(Integer id);

    Integer getCartCount(Integer userId);

    List<GoodCart> getGoodCartByIdList(int[] idList);

    void updateGoodCartById(GoodCart goodCart);
}
