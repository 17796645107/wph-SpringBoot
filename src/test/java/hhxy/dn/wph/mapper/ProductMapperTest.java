package hhxy.dn.wph.mapper;

import hhxy.dn.wph.TestTemplate;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 20:24 2019/5/20
 */

public class ProductMapperTest extends TestTemplate {


    @Autowired
    private ProductMapper productMapper;

    @Test
    public void getProductById() {
        /*Product product = productMapper.getProductById(1);
        System.out.println(product);*/
    }

    @Test
    public void listCategoryByParentId() {
        List<Category> categoryList = productMapper.listCategoryByParentId(0);
        categoryList.forEach(System.out::println);
    }

    @Test
    public void listCategoryBySellerId() {
        List<Category> categoryList = productMapper.listCategoryBySellerId(1);
        categoryList.forEach(category -> System.out.println(category));
    }

    @Test
    public void listProductSizeByProductId() {
        //List<ProductSize> productSizeList = productMapper.listProductSizeByProductId(1);
        //productSizeList.forEach(System.out::println);
    }

    @Test
    public void listProductSizeByCategoryId() {
        List<ProductSize> productSizeList = productMapper.listProductSizeByCategoryId(1);
        productSizeList.forEach(System.out::println);
    }

    @Test
    public void listProductBySellerId() {
        List<Product> productList = productMapper.listProductBySellerId(8);
        productList.forEach(System.out::println);
    }

    @Test
    public void listProductColorByProductId() {
        /*List<ProductColor> productColorList = productMapper.listProductColorByProductId(1);
        productColorList.forEach(System.out::println);*/
    }

    @Test
    public void listProductImageByProductId() {
        /*List<ProductImage> productImageList = productMapper.listProductImageByProductId(15);
        productImageList.forEach(System.out::println);*/
    }

    @Test
    public void getImageById() {
        String imageUrl = productMapper.getImageById(111);
        LOGGER.info(imageUrl);
    }

    @Test
    public void getImageByProductId() {
        /*ProductImage productImage = productMapper.getImageByProductId(1);
        LOGGER.info(productImage.toString());*/
    }

    @Test
    public void listProductNum() {
        ProductNum productNum = new ProductNum();
        productNum.setProductId("1");
        List<Integer> productNumList = productMapper.listProductNum(productNum);
        productNumList.forEach(System.out::println);
    }

    @Test
    public void listProductNumBySize() {

    }

    @Test
    public void updateProductNum() {
        int integer = productMapper.updateProductNum("1","漂白","XS",
                2);
        Assert.assertEquals(1, integer);
    }

    @Test
    public void getProductCount() {
        int count = productMapper.getProductCount(40,8);
        LOGGER.info("count = {}",count);
    }

    @Test
    public void getProductByCategoryId() {
        List<Product> productList = productMapper.listProductByCategoryId(42);
        productList.forEach(System.out::println);
    }

    @Test
    public void listBrandByCategoryId() {
        List<Brand> brandList = productMapper.listBrandByCategoryId(40);
        brandList.forEach(System.out::println);
    }

    @Test
    public void listProductAttributeByCategoryId() {
        List<ProductAttribute> productAttributeList = productMapper.listProductAttributeByCategoryId(40);
        productAttributeList.forEach(System.out::println);
    }

    @Test
    public void listProductAttributeValueByAttributeId() {
        List<ProductAttributeValue> productAttributeValueList =
                productMapper.listProductAttributeValueByAttributeId(15);
        productAttributeValueList.forEach(System.out::println);
    }

    @Test
    public void listProductByArrtibute() {
        ProductAttributeRelation productAttributeRelation = new ProductAttributeRelation();
        productAttributeRelation.setAttributeId(15);
        List<Integer> intList = productMapper.listProductIdByAttribute(productAttributeRelation);
        intList.forEach(System.out::println);
    }

    @Test
    public void listProductIdByArrtibute() {
    }

    @Test
    public void listProductInSeller() {
        /*long time = System.currentTimeMillis();
        List<Product> productList = productMapper.listProductInSeller(1,43,
                139,1,1);
        productList.forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - time);*/
    }

    @Test
    public void CATEGORY_LIST() {
        List<Category> categoryList = productMapper.CATEGORY_LIST();
        LOGGER.info("json={}",JsonUtil.objectToJson(categoryList));
    }

    @Test
    public void listProductByTitle() {
        List<Product> productList = productMapper.listProductByTitle("//");
        productList.forEach(System.out::println);
    }

    /*@Test
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
