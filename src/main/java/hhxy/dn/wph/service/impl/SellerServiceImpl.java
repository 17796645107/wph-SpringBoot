package hhxy.dn.wph.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.SellerExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.SellerException;
import hhxy.dn.wph.mapper.SellerMapper;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 16:28 2019/4/7
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Seller> listSellerByCategoryId(Integer categoryId){
        /*String sellerList = (String) redisUtil.get("SellerByPrimaryCategoryId:"+categoryId);
        if (sellerList != null){
            return JsonUtil.jsonToList(sellerList,Seller.class);
        }*/
        List<Seller> sellers = sellerMapper.listSellerByCategoryId(categoryId);
        redisUtil.set("SellerByPrimaryCategoryId:"+categoryId,JsonUtil.objectToJson(sellers));
        return sellers;
    }

    @Override
    public Seller getSellerById(Integer sellerNo) {
        /*String cacheKey = "SellerByNo:";
        if (redisUtil.hasKey(cacheKey + sellerNo)){
            String seller = (String) redisUtil.get(cacheKey+ sellerNo);
            return JsonUtil.jsonToPojo(seller,Seller.class);
        }*/
        Seller seller = sellerMapper.getSellerById(sellerNo);
        if (seller == null){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        //redisUtil.set(cacheKey+ sellerNo,JsonUtil.objectToJson(seller));
        return seller;
    }

    @Override
    public int getSellerCollectNum(Integer sellerId) {
        int result = sellerMapper.getSellerCollectNum(sellerId);
        return result;
    }

    /**
     * 商户登录
     * @param sellerAccount 账户
     * @return Seller
     */
    @Override
    public Seller login(SellerAccount sellerAccount) {
        Integer sellerId = sellerMapper.getSellerId(sellerAccount);
        if (sellerId == null){
            throw new SellerException(SellerExceptionEnum.accountError);
        }

        return sellerMapper.getSellerById(sellerId);
    }

    /**
     *
     * @param sellerId
     * @param pageIndex
     * @param pageCount
     * @return PageInfo<Product>
     */
    @Override
    public PageInfo pageListProductById(int sellerId,int pageIndex, int pageCount) {
        List<Product> productList = sellerMapper.listProductById(sellerId);
        PageHelper.startPage(pageIndex,pageCount);
        return new PageInfo<>(productList);
    }

    /**
     * 添加商品
     * @param product
     * @param file
     * @param colors
     * @param sizes
     */
    @Override
    @Transactional
    public void addProduct(Product product, MultipartFile[] file, String[] colors, String[] sizes) {
        //上传图片
        UploadFileUtil.uploadFiles(file);
        //插入商品
        product.setCollect(0);
        product.setCreated(DateUtil.getDate());
        product.setState(1);
        product.setIsNew(1);
        product.setIsHot(0);
        product.setProductNo(IDUtil.createProductNo());
        int addProductResult = sellerMapper.saveProduct(product);
        if (addProductResult < 1){
            throw new GeneralException(GeneralExceptionEnum.SAVE_DATABASE_ERROR);
        }
        //插入商品颜色
        if (colors.length == 0){
            ProductColor productColor = new ProductColor();
            productColor.setProductId(product.getProductNo());
            productColor.setColor("默认");
            productColor.setCreated(DateUtil.getDate());
            int result = sellerMapper.saveProductColor(productColor);
            //插入商品图片
            for (int i = 0;i < 5;i++){
                ProductImage productImage = new ProductImage();
                productImage.setProductId(product.getProductNo());
                productImage.setColorId(productColor.getId());
                productImage.setImage(file[i].getOriginalFilename());
                productImage.setCreated(DateUtil.getDate());
                int result1 = sellerMapper.saveProductImage(productImage);
            }

            for (String size : sizes) {
                ProductNum productNum = new ProductNum();
                productNum.setProductId(product.getProductNo());
                productNum.setProductColor("默认");
                productNum.setProductSize(size);
                productNum.setNum(999);
                productNum.setCreated(DateUtil.getDate());
                sellerMapper.saveProductNum(productNum);
            }
        }
        else {
            for (String color : colors) {
                ProductColor productColor = new ProductColor();
                productColor.setProductId(product.getProductNo());
                productColor.setColor(color);
                productColor.setCreated(DateUtil.getDate());
                int result = sellerMapper.saveProductColor(productColor);
                int j = 0;
                for (int i = j;i < j + 5;i++){
                    ProductImage productImage = new ProductImage();
                    productImage.setProductId(product.getProductNo());
                    productImage.setColorId(productColor.getId());
                    productImage.setImage(file[i].getOriginalFilename());
                    productImage.setCreated(DateUtil.getDate());
                    int result1 = sellerMapper.saveProductImage(productImage);
                }
                j+= 5;

                for (String size : sizes) {
                    ProductNum productNum = new ProductNum();
                    productNum.setProductId(product.getProductNo());
                    productNum.setProductColor(color);
                    productNum.setProductSize(size);
                    productNum.setNum(999);
                    productNum.setCreated(DateUtil.getDate());
                    sellerMapper.saveProductNum(productNum);
                }
            }
        }
        //插入商品尺寸
        if (sizes.length == 0){
            ProductSize productSize = new ProductSize();
            productSize.setProductId(product.getProductNo());
            productSize.setSize("默认");
            productSize.setCreated(DateUtil.getDate());
            int result = sellerMapper.saveProductSize(productSize);
        }
        else {
            for (String size : sizes) {
                ProductSize productSize = new ProductSize();
                productSize.setProductId(product.getProductNo());
                productSize.setSize(size);
                productSize.setCreated(DateUtil.getDate());
                int result = sellerMapper.saveProductSize(productSize);
            }
        }

    }

    @Override
    public void updateProduct(int productId) {
        Product product = new Product();
        product.setId(productId);
        product.setState(0);
        sellerMapper.updateProduct(product);
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
