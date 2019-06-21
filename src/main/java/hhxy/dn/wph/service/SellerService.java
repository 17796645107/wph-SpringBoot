package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.Seller;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 16:27 2019/4/7
 */

public interface SellerService {

    /**
     * 查询商户列表
     * @param categoryId 分类ID
     * @return java.util.List<hhxy.dn.wph.entity.Seller>
     */
    List<Seller> listSellerByCategoryId(Integer categoryId);

    /**
     * 根据编号获取商户信息
     * @param sellerId
     * @return hhxy.dn.wph.entity.Seller
     */
    Seller getSellerById(Integer sellerId);

    /**
     * 获取商户收藏量
     * @param sellerId
     * @return int
     */
    int getSellerCollectNum(Integer sellerId);

    /**
     * 商户登录
     * @param sellerAccount
     * @return hhxy.dn.wph.entity.Seller
     */
    Seller login(SellerAccount sellerAccount);
}
