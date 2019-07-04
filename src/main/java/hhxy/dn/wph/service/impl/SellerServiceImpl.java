package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.SellerExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.SellerException;
import hhxy.dn.wph.mapper.SellerMapper;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 16:28 2019/4/7
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Seller> listSellerByCategoryId(Integer categoryId){
        String sellerList = (String) redisUtil.get("SellerByPrimaryCategoryId:"+categoryId);
        if (sellerList != null){
            return JsonUtil.jsonToList(sellerList,Seller.class);
        }
        List<Seller> sellers = sellerMapper.listSellerByCategoryId(categoryId);
        redisUtil.set("SellerByPrimaryCategoryId:"+categoryId,JsonUtil.objectToJson(sellers));
        return sellers;
    }

    @Override
    public Seller getSellerById(Integer sellerNo) {
        String cacheKey = "SellerByNo:";
        if (redisUtil.hasKey(cacheKey + sellerNo)){
            String seller = (String) redisUtil.get(cacheKey+ sellerNo);
            return JsonUtil.jsonToPojo(seller,Seller.class);
        }
        Seller seller = sellerMapper.getSellerById(sellerNo);
        if (seller == null){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        redisUtil.set(cacheKey+ sellerNo,JsonUtil.objectToJson(seller));
        return seller;
    }

    @Override
    public int getSellerCollectNum(Integer sellerId) {
        int result = sellerMapper.getSellerCollectNum(sellerId);
        return result;
    }

    /**
     *
     * @param sellerAccount
     * @return hhxy.dn.wph.entity.Seller
     */
    @Override
    public Seller login(SellerAccount sellerAccount) {
        Integer sellerId = sellerMapper.getSellerId(sellerAccount);
        if (sellerId == null){
            throw new SellerException(SellerExceptionEnum.accountError);
        }
        String cacheKey = "SellerById:";
        if (redisUtil.hasKey(cacheKey + sellerId)){
            String seller = (String) redisUtil.get(cacheKey+ sellerId);
            return JsonUtil.jsonToPojo(seller,Seller.class);
        }
        Seller seller = sellerMapper.getSellerById(sellerId);
        redisUtil.set(cacheKey+ sellerId,JsonUtil.objectToJson(seller));
        return seller;

    }

    public int saveProduct(Product product){
        int result = sellerMapper.saveProduct(product);
        return product.getId();
    }

    public void saveProductSize(List<ProductSize> productSizeList){
        productSizeList.forEach(productSize -> {
            int result = sellerMapper.saveProductSize(productSize);
        });
    }
    public void saveProductColor(List<ProductColor> productColorList){
        productColorList.forEach(productColor -> {
            int result = sellerMapper.saveProductColor(productColor);
        });
    }
    public void saveProductImage(List<ProductImage> productImageList){
        productImageList.forEach(productImage -> {
            int result = sellerMapper.saveProductImage(productImage);
        });
    }
    public void saveProductNum(List<ProductNum> productNumList){
        productNumList.forEach(productNum -> {
            int result = sellerMapper.saveProductNum(productNum);
        });
    }
    public void saveProductAttributeRelation(ProductAttributeRelation productAttributeRelation){
        int result = sellerMapper.saveProductAttributeRelation(productAttributeRelation);
    }
}
