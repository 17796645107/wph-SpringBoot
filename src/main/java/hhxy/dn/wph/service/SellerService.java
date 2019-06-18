package hhxy.dn.wph.service;

import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.Seller;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 16:27 2019/4/7
 */

public interface SellerService {

    List<Seller> getSellerByPrimaryCategoryId(Integer primaryId);

    //根据编号获取商户信息
    Seller getSellerById(Integer sellerNo);

    int getSellerCollectNum(Integer sellerId);

    Seller login(SellerAccount sellerAccount);
}
