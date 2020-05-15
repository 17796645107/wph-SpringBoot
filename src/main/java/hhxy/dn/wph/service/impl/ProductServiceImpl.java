package hhxy.dn.wph.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @author 邓宁
 * @date Created in 21:48 2018/11/4
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
     * @param sellerId
     * @return List<Category>
     */
    @Override
    public List<Category> listCategoryBySellerId(Integer sellerId) {
        List<Category> secondaryCategoryList = productMapper.listCategoryBySellerId(sellerId);
        secondaryCategoryList.forEach(category -> {
            //统计每种分类的商品数量
            int count = productMapper.getProductCount(category.getId(),sellerId);
            category.setProductCount(count);
        });
        //查询结果为空。则抛出异常
        validIsEmpty(secondaryCategoryList.isEmpty());
        //写入缓存
        return secondaryCategoryList;
    }

    /**
     * 验证查询结果是否为空
     * @param empty List.isEmpty()方法,

     */
    private void validIsEmpty(boolean empty) {
        if (empty) {
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
    }

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param categoryId 一级目录
     * @return List<ProductSize>
     */
    @Override
    public List<ProductSize> listProductSizeByCategoryId(Integer categoryId) {
        List<ProductSize> productSizeList = productMapper.listProductSizeByCategoryId(categoryId);
        //查询结果为空。则抛出异常
        validIsEmpty(productSizeList.isEmpty());
        return productSizeList;
    }

    /**
     * 根据商品ID查询商品
     * @param productId 商品ID(主键)
     * @return Product
     */
    @Override
    public Product getProductById(String productId) {
        Product product = productMapper.getProductById(productId);
        validIsEmpty(product == null);
        return product;
    }

    /**
     * 获取商品分类目录
     * @param parentId
     * @return List<Category>
     */
    @Override
    public List<Category> listCategoryByParentId(Integer parentId) {

        List<Category> categories = productMapper.listCategoryByParentId(parentId);
        //如果查询结果为空，抛出异常，（空结果禁止写入缓存）！
        validIsEmpty(categories.isEmpty());
        return categories;
    }

    /**
     * 根据商品分类查询商品
     * @param categoryId 分类ID
     * @param page 当前页数
     * @param countOfPage 页面显示数量
     * @return List<Product>
     */
    @Override
    public List<Product> listProductByCategoryId(Integer categoryId, Integer page, Integer countOfPage) {
        List<Product> productList = productMapper.listProductByCategoryId(categoryId);
        validIsEmpty(productList.isEmpty());
        return productList;
    }

    /**
     * 查询品牌列表
     * @param categoryId
     * @return List<Brand>
     */
    @Override
    public List<Brand> listBrandByCategoryId(Integer categoryId) {
        List<Brand> brandList = productMapper.listBrandByCategoryId(categoryId);
        validIsEmpty(brandList.isEmpty());
        return brandList;
    }

    /**
     * 根据商品分类获取商品属性列表和商品属性值列表
     * @param categoryId
     * @return List<ProductAttribute>
     */
    @Override
    public List<ProductAttribute> listProductAttributeByCategoryId(Integer categoryId) {
        //获取商品属性
        List<ProductAttribute> productAttributeList = productMapper.listProductAttributeByCategoryId(categoryId);
        productAttributeList.forEach(productAttribute -> {
            //获取商品属性值
            List<ProductAttributeValue> productAttributeValueList = productMapper.listProductAttributeValueByAttributeId(productAttribute.getId());
            productAttribute.setAttributeValues(productAttributeValueList);
        });
        validIsEmpty(productAttributeList.isEmpty());
        return productAttributeList;
    }

    /**
     * 根据商户查询商品
     * @param sellerId 商户ID
     * @param pageIndex 当前页数
     * @param pageCount 每页显示数量
     * @return PageInfo<Product>
     */
    @Override
    public PageInfo<Product> listProductBySellerId(Integer sellerId,Integer pageIndex, Integer pageCount) {
        List<Product> productList = productMapper.listProductBySellerId(sellerId);
        validIsEmpty(productList.isEmpty());
        //开始分页
        PageHelper.startPage(pageIndex,pageCount);
        //把查询结果传给PageInfo,生成分页

        return new PageInfo<>(productList);
    }

    /**
     * 查询商品颜色列表
     * @param productId
     * @return List<ProductColor>
     */
    @Override
    public List<ProductColor> listProductColorByProductId(String productId) {
        return productMapper.listProductColorByProductId(productId);
    }

    /**
     *  查询商品尺寸列表
     * @param productId
     * @return List<ProductSize>
     */
    @Override
    public List<ProductSize> listProductSizeByProductId(String productId) {
        return productMapper.listProductSizeByProductId(productId);
    }

    /**
     *  查询商品图片
     * @param productId
     * @return List<ProductImage>
     */
    @Override
    public List<ProductImage> listProductImageByProductId(String productId) {
        return productMapper.listProductImageByProductId(productId);
    }

    /**
     *  查询商品库存
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

    private List<Product> findProductByAttribute(List<ProductAttributeRelation> attributeRelations){
        Integer[] productIdArray = null;
        for (int i = 0; i < attributeRelations.size(); i++) {
            if (i == 0){
                List<Integer> productId = productMapper.listProductIdByAttribute(attributeRelations.get(i));
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

    private Integer[] listToArray(List<Integer> productIdList){
        int size = productIdList.size();
        Integer[]array = new Integer[productIdList.size()];
        for (int i = 0; i < size; i++) {
            array[i] = productIdList.get(i);
        }
        return array;
    }

    /**
     * 分页检索商品
     * @param condition
     * @param pageNum
     * @param pageCount
     * @return PageInfo<Product>
     */
    @Override
    public PageInfo<Product> findProductInSeller(ProductSelectCondition condition,Integer pageNum,Integer pageCount){
        /*final String productListKey = "ProductListBySellerId:"+ condition.getSellerId() +":Page"+ pageNum;
        if (redisUtil.hasKey(productListKey)){
            String productListCache = (String) redisUtil.get(productListKey);
            return JsonUtil.jsonToPojo(productListCache,PageInfo.class);
        }*/
        List<Product> productList = productMapper.listProductInSeller(condition);
        PageHelper.startPage(pageNum,pageCount);

        return new PageInfo<>(productList);
    }

    @Override
    public List<Category> listCategoryTree() {
        return productMapper.CATEGORY_LIST();
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
