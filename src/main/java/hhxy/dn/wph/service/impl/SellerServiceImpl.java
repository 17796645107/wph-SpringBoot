package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.domain.ProductAttributeRelation;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.mapper.SellerMapper;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import hhxy.dn.wph.util.ResultUtil;
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

    public List<Seller> getSellerByPrimaryCategoryId(Integer primaryId){
        String sellerList = (String) redisUtil.get("SellerByPrimaryCategoryId:"+primaryId);
        if (sellerList != null){
            return JsonUtil.jsonToList(sellerList,Seller.class);
        }
        List<Seller> sellers = sellerMapper.getSellerByPrimaryCategoryId(primaryId);
        redisUtil.set("SellerByPrimaryCategoryId:"+primaryId,JsonUtil.objectToJson(sellers));
        return sellers;
    }

    @Override
    public Seller getSellerById(Integer sellerId) {
        return sellerMapper.getSellerById(sellerId);
    }

    @Override
    public int getSellerCollectNum(Integer sellerId) {
        int result = sellerMapper.getSellerCollectNum(sellerId);
        return result;
    }

    public int saveOneProduct(Product product){
        int result = sellerMapper.saveOneProduct(product);
        return product.getProduct_id();
    }

    /*public void saveProductSize(List<ProductSize> productSizeList){
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
    }*/
}
