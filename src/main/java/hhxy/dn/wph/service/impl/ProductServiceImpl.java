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
import java.util.Set;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    //查询商户所有的商品二级目录（店铺的所有分类）
    @Override
    public List<Category> findCategoryBySellerId(Integer seller_id) {
        //查询缓存
        if (redisUtil.hasKey("SecondaryCategory:"+seller_id)){
            return JsonUtil.jsonToList(redisUtil.get("SecondaryCategory:"+seller_id).toString(),Category.class);
        }
        //查询数据库
        List<Category> SecondaryCategoryList = productMapper.findCategoryBySellerId(seller_id);
        SecondaryCategoryList.forEach(category -> {
            //统计每种分类的商品数量
            int count = productMapper.getProductCountBySecoundaryCategory(category.getId(),seller_id);
            category.setProductCount(count);
        });
        //查询结果为空。则抛出异常
        if (SecondaryCategoryList.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        //写入缓存
        redisUtil.set("SecondaryCategory:"+seller_id,JsonUtil.objectToJson(SecondaryCategoryList));
        return SecondaryCategoryList;
    }

    //根据一级目录查询所有的商品尺寸
    @Override
    public List<ProductSize> findAllProductSizeByPrimaryCategoryId(Integer primary_id) {
        return productMapper.findAllProductSizeByPrimaryCategoryId(primary_id);
    }

    //根据商品ID查询商品
    @Override
    public Product getProductByProductId(Integer product_id) {
        String key = "Product:"+product_id;
        if(redisUtil.hasKey(key)){
            String productCache = (String) redisUtil.get(key);
            return JsonUtil.jsonToPojo(productCache,Product.class);
        }
        Product product = productMapper.getProductByProductId(product_id);
        if (product == null){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set(key,JsonUtil.objectToJson(product));
        return product;
    }

    //获取商品分类目录
    @Override
    public List<Category> findCategoryByParentId(Integer parentId) {
        if (redisUtil.hasKey("Category:"+parentId)){
            String categoryList = (String) redisUtil.get("Category:"+parentId);
            return  JsonUtil.jsonToList(categoryList,Category.class);
        }
        List<Category> categories = productMapper.findCategoryByParentId(parentId);
        //如果查询结果为空，抛出异常，（空结果禁止写入缓存）！
        if (categories.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set("Category:"+parentId, JsonUtil.objectToJson(categories));
        return categories;
    }

    /*
     * @Description:根据商品分类查询商品
     * @param: [categoryId, page, countOfPage]商品分类Id，当前页，页面商品数量
     * @return: java.util.List<hhxy.dn.wph.entity.Product>
     */
    @Override
    public List<Product> getProductByCategoryId(Integer categoryId, Integer page, Integer countOfPage) {
        if (redisUtil.hasKey("ProductListByCategoryId:"+ categoryId +":Page"+page)){
            String products = (String) redisUtil.get("ProductListByCategoryId:"+ categoryId +":Page"+page);
            return JsonUtil.jsonToList(products,Product.class);
        }
        List<Product> productList = productMapper.getProductByCategoryId(categoryId);
        redisUtil.set("ProductListByCategoryId:"+ categoryId +":Page"+page,JsonUtil.objectToJson(productList));
        return productList;
    }

    @Override
    public List<Brand> getBrandByCategoryId(Integer categoryId) {
        if (redisUtil.hasKey("Brand:"+categoryId)){
            return JsonUtil.jsonToList(redisUtil.get("Brand:"+categoryId).toString(),Brand.class);
        }
        List<Brand> brandList = productMapper.getBrandByCategoryId(categoryId);
        redisUtil.set("Brand:"+categoryId,JsonUtil.objectToJson(brandList));
        return brandList;
    }

    /*
     * @Description:根据商品分类获取商品属性列表和商品属性值列表
     * @param: [categoryId]
     * @return: java.util.List<hhxy.dn.wph.entity.ProductAttribute>
     */
    @Override
    public List<ProductAttribute> getProductAttributeByCategoryId(Integer categoryId) {
        if (redisUtil.hasKey("ProductAttributeByCategoryId:"+categoryId)){
            return JsonUtil.jsonToList(redisUtil.get("ProductAttributeByCategoryId:"+categoryId).toString(),ProductAttribute.class);
        }
        //获取商品属性
        List<ProductAttribute> productAttributeList = productMapper.getProductAttributeByCategoryId(categoryId);
        productAttributeList.forEach(productAttribute -> {
            //获取商品属性值
            List<ProductAttributeValue> productAttributeValueList = productMapper.getProductAttributeValueByAttributeId(productAttribute.getId());
            productAttribute.setAttributeValues(productAttributeValueList);
        });
        if (productAttributeList.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set("ProductAttributeByCategoryId:"+categoryId,JsonUtil.objectToJson(productAttributeList));
        return productAttributeList;
    }

    @Override
    public int saveProduct(Product product) {
        return productMapper.saveProduct(product);
    }

    /*
     * @Description:根据商户查询商品
     * @param: [sellerId, page, countOfPage]
     * @return: java.util.List<hhxy.dn.wph.entity.Product>
     */
    @Override
    public List<Product> findProductBySellerId(Integer sellerId,Integer page,Integer countOfPage) {
        if (redisUtil.hasKey("ProductListBySellerId:"+ sellerId +":Page"+ page)){
            String productListCache = (String) redisUtil.get("ProductListBySellerId:"+ sellerId +":Page"+ page);
            return JsonUtil.jsonToList(productListCache,Product.class);
        }
        List<Product> productList = productMapper.findProductBySellerId(sellerId);
        if (productList.isEmpty()){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set("ProductListBySellerId:"+ sellerId +":Page"+ page,JsonUtil.objectToJson(productList));
        return productList;
    }

    @Override
    public List<ProductColor> findProductColorByProductId(Integer productId) {
        return productMapper.findProductColorByProductId(productId);
    }

    @Override
    public Set<ProductSize> findProductSizeByProductId(Integer productId) {
        return productMapper.findProductSizeByProductId(productId);
    }

    @Override
    public List<ProductImage> findProductImageByProductId(Integer productId) {
        return productMapper.findProductImageByProductId(productId);
    }

    @Override
    public Integer findProductNum(ProductNum productNum) {

        List<Integer> numList = productMapper.findProductNum(productNum);
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
                List<Integer> product_id = productMapper.findProductByArrtibute(attributeRelations.get(i));
                productIdArray = listToArray(product_id);
            }else{
                List<Integer> p = productMapper.findProductByArrtibute1(attributeRelations.get(i),productIdArray);
                productIdArray =listToArray(p);
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

    public List<Product> findProductInSeller(Integer seller_id,Integer secoundCategoryId,Integer size_id,Integer type,Integer hasNum){
        List<Product> productList = productMapper.findProductInSeller(seller_id,secoundCategoryId,size_id,type,hasNum);
        return  productList;
    }
}
