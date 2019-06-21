package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.mapper.ProductMapper;
import hhxy.dn.wph.service.ProductService;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 21:48 2018/11/4
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 查询商户所有的商品二级目录（店铺的所有分类）
     * @param seller_id
     * @return java.util.List<hhxy.dn.wph.entity.Category>
     */
    @Override
    public List<Category> listCategoryBySellerId(Integer sellerId) {
        String cacheKey = "SecondaryCategory:";
        //查询缓存
        if (redisUtil.hasKey(cacheKey + sellerId)){
            String cache = (String) redisUtil.get(cacheKey + sellerId);
            return JsonUtil.jsonToList(cache,Category.class);
        }
        //查询数据库
        List<Category> secondaryCategoryList = productMapper.listCategoryBySellerId(sellerId);
        secondaryCategoryList.forEach(category -> {
            //统计每种分类的商品数量
            int count = productMapper.getProductCount(category.getId(),sellerId);
            category.setProductCount(count);
        });
        //查询结果为空。则抛出异常
        validIsEmpty(secondaryCategoryList.isEmpty());
        //写入缓存
        redisUtil.set(cacheKey + sellerId,JsonUtil.objectToJson(secondaryCategoryList));
        return secondaryCategoryList;
    }

    /**
     * 验证查询结果是否为空
     * @param empty List.isEmpty()方法,
     * @return void
     */
    private void validIsEmpty(boolean empty) {
        if (empty) {
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND);
        }
    }

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param categoryId
     * @return java.util.List<hhxy.dn.wph.entity.ProductSize>
     */
    @Override
    public List<ProductSize> listProductSizeByCategoryId(Integer categoryId) {
        String cacheKey = "ProductSize:";
        //查询缓存
        if (redisUtil.hasKey(cacheKey + categoryId)){
            String cache = (String) redisUtil.get(cacheKey + categoryId);
            return JsonUtil.jsonToList(cache,ProductSize.class);
        }
        //查询数据库
        List<ProductSize> productSizeList = productMapper.listProductSizeByCategoryId(categoryId);
        //查询结果为空。则抛出异常
        validIsEmpty(productSizeList.isEmpty());
        //写入缓存
        redisUtil.set(cacheKey + categoryId,JsonUtil.objectToJson(productSizeList));
        return productSizeList;
    }

    /**
     * 根据商品ID查询商品
     * @param productId
     * @return hhxy.dn.wph.entity.Product
     */
    @Override
    public Product getProductById(Integer productId) {
        final String cacheKey = "Product:"+ productId;
        if(redisUtil.hasKey(cacheKey)){
            String productCache = (String) redisUtil.get(cacheKey);
            return JsonUtil.jsonToPojo(productCache,Product.class);
        }
        Product product = productMapper.getProductById(productId);
        validIsEmpty(product == null);
        redisUtil.set(cacheKey,JsonUtil.objectToJson(product));
        return product;
    }

    /**
     * 获取商品分类目录
     * @param parentId
     * @return java.util.List<hhxy.dn.wph.entity.Category>
     */
    @Override
    public List<Category> listCategoryByParentId(Integer parentId) {
        final String categoryKey = "Category:";
        if (redisUtil.hasKey(categoryKey + parentId)){
            String categoryList = (String) redisUtil.get(categoryKey + parentId);
            return  JsonUtil.jsonToList(categoryList,Category.class);
        }
        List<Category> categories = productMapper.listCategoryByParentId(parentId);
        //如果查询结果为空，抛出异常，（空结果禁止写入缓存）！
        validIsEmpty(categories.isEmpty());
        redisUtil.set(categoryKey + parentId, JsonUtil.objectToJson(categories));
        return categories;
    }

    /**
     * @Description: 根据商品分类查询商品
     * @param: [categoryId, page, countOfPage]商品分类Id，当前页，页面商品数量
     * @return: java.util.List<hhxy.dn.wph.entity.Product>
     */
    @Override
    public List<Product> listProductByCategoryId(Integer categoryId, Integer page, Integer countOfPage) {
        final String productListKey = "ProductListByCategoryId:"+ categoryId +":Page" + page;
        if (redisUtil.hasKey(productListKey)){
            String products = (String) redisUtil.get(productListKey);
            return JsonUtil.jsonToList(products,Product.class);
        }
        List<Product> productList = productMapper.listProductByCategoryId(categoryId);
        validIsEmpty(productList.isEmpty());
        redisUtil.set(productListKey,JsonUtil.objectToJson(productList));
        return productList;
    }

    /**
     * 查询品牌列表
     * @param categoryId
     * @return java.util.List<hhxy.dn.wph.entity.Brand>
     */
    @Override
    public List<Brand> listBrandByCategoryId(Integer categoryId) {
        final String brandKey = "Brand:";
        if (redisUtil.hasKey(brandKey + categoryId)){
            String cache = (String) redisUtil.get(brandKey + categoryId);
            return JsonUtil.jsonToList(cache,Brand.class);
        }
        List<Brand> brandList = productMapper.listBrandByCategoryId(categoryId);
        validIsEmpty(brandList.size() == 0);
        redisUtil.set(brandKey + categoryId,JsonUtil.objectToJson(brandList));
        return brandList;
    }

    /**
     * @Description:根据商品分类获取商品属性列表和商品属性值列表
     * @param: [categoryId]
     * @return: java.util.List<hhxy.dn.wph.entity.ProductAttribute>
     */
    @Override
    public List<ProductAttribute> listProductAttributeByCategoryId(Integer categoryId) {
        final String productAttributeKey = "ProductAttributeByCategoryId:";
        if (redisUtil.hasKey(productAttributeKey + categoryId)){
            String cache = (String) redisUtil.get(productAttributeKey + categoryId);
            return JsonUtil.jsonToList(cache,ProductAttribute.class);
        }
        //获取商品属性
        List<ProductAttribute> productAttributeList = productMapper.listProductAttributeByCategoryId(categoryId);
        productAttributeList.forEach(productAttribute -> {
            //获取商品属性值
            List<ProductAttributeValue> productAttributeValueList = productMapper.listProductAttributeValueByAttributeId(productAttribute.getId());
            productAttribute.setAttributeValues(productAttributeValueList);
        });
        validIsEmpty(productAttributeList.isEmpty());
        redisUtil.set(productAttributeKey + categoryId,JsonUtil.objectToJson(productAttributeList));
        return productAttributeList;
    }

    /**
     * @Description:根据商户查询商品
     * @param: [sellerId, page, countOfPage]
     * @return: java.util.List<hhxy.dn.wph.entity.Product>
     */
    @Override
    public List<Product> listProductBySellerId(Integer sellerId,Integer page,Integer countOfPage) {
        final String productListKey = "ProductListBySellerId:"+ sellerId +":Page"+ page;
        if (redisUtil.hasKey(productListKey)){
            String productListCache = (String) redisUtil.get(productListKey);
            return JsonUtil.jsonToList(productListCache,Product.class);
        }
        List<Product> productList = productMapper.listProductBySellerId(sellerId);
        validIsEmpty(productList.isEmpty());
        redisUtil.set(productListKey,JsonUtil.objectToJson(productList));
        return productList;
    }

    /**
     * @Description 查询商品颜色列表
     * @param productId
     * @return java.util.List<hhxy.dn.wph.entity.ProductColor>
     */
    @Override
    public List<ProductColor> listProductColorByProductId(Integer productId) {
        return productMapper.listProductColorByProductId(productId);
    }

    /**
     * @Description 查询商品尺寸列表
     * @param productId
     * @return java.util.List<hhxy.dn.wph.entity.ProductSize>
     */
    @Override
    public List<ProductSize> listProductSizeByProductId(Integer productId) {
        return productMapper.listProductSizeByProductId(productId);
    }

    /**
     * @Description 查询商品图片
     * @param productId
     * @return java.util.List<hhxy.dn.wph.entity.ProductImage>
     */
    @Override
    public List<ProductImage> listProductImageByProductId(Integer productId) {
        return productMapper.listProductImageByProductId(productId);
    }

    /**
     * @Description 查询商品库存
     * @param productNum
     * @return java.lang.Integer
     */
    @Override
    public Integer getProductNum(ProductNum productNum) {

        List<Integer> numList = productMapper.listProductNum(productNum);
        Integer num = 0;
        for(Integer i:numList){
            num += i;
        }
        return num;
    }

    public List<Product> findProductByArrtibute(List<ProductAttributeRelation> attributeRelations){
        Integer[] productIdArray = null;
        for (int i = 0; i < attributeRelations.size(); i++) {
            if (i == 0){
                List<Integer> productId = productMapper.listProductIdByArrtibute(attributeRelations.get(i));
                productIdArray = listToArray(productId);
            }else{
                List<Integer> productId = productMapper.listProductId(attributeRelations.get(i),productIdArray);
                productIdArray = listToArray(productId);
            }
            if (productIdArray.length == 0){
                System.out.println("没有查询到商品");
                break;
            }
            for (Object integer : productIdArray) {
                System.out.println(integer);
            }
        }
        return  null;
    }

    public Integer[] listToArray(List<Integer> productIdList){
        int size = productIdList.size();
        Integer[]array = new Integer[productIdList.size()];
        for (int i = 0; i < size; i++) {
            array[i] = productIdList.get(i);
        }
        return array;
    }

    /**
     * @Description 检索商品
     * @param sellerId
     * @param secoundCategoryId
     * @param sizeId
     * @param type
     * @param hasNum
     * @return java.util.List<hhxy.dn.wph.entity.Product>
     */
    public List<Product> findProductInSeller(Integer sellerId,Integer secoundCategoryId,Integer sizeId,Integer type,Integer hasNum){
        List<Product> productList = productMapper.listProductInSeller(sellerId,secoundCategoryId,sizeId,type,hasNum);
        return  productList;
    }

    /*@Transactional(rollbackFor = Exception.class)
    public void update(){
        List<Integer> idList = productMapper.listProductId();
        idList.forEach(integer -> {
            String productNo  = productMapper.getProductNo(integer);
            productMapper.updateProduct(IDUtil.createProductNo(),integer);
            productMapper.updateCart(integer,Integer.valueOf(productNo));
            productMapper.updateProductAttributeRelation(integer,Integer.valueOf(productNo));
            productMapper.updateProductColor(integer,Integer.valueOf(productNo));
            productMapper.updateProductEvaluation(integer,Integer.valueOf(productNo));
            productMapper.updateProductImage(integer,Integer.valueOf(productNo));
            productMapper.updateProductNum1(integer,Integer.valueOf(productNo));
            productMapper.updateProductImageSize(integer,Integer.valueOf(productNo));
            productMapper.updateOrderProduct(integer,Integer.valueOf(productNo));
        });
    }*/
}
