package hhxy.dn.wph.service;

import hhxy.dn.wph.domain.SellerAccount;
import hhxy.dn.wph.entity.Seller;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 16:27 2019/4/7
 */

public interface SellerService {

    List<Seller> getSellerByPrimaryCategoryId(Integer primaryId);

    Seller getSellerById(Integer sellerId);

    int getSellerCollectNum(Integer sellerId);

    Seller login(SellerAccount sellerAccount);
}
