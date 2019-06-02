package hhxy.dn.wph.controller;

import hhxy.dn.wph.domain.Brand;
import hhxy.dn.wph.domain.Category;
import hhxy.dn.wph.domain.ProductAttribute;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.ProductExceptionEnum;
import hhxy.dn.wph.exception.ProductException;
import hhxy.dn.wph.service.ProductService;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: 邓宁
 * @Date: Created in 21:47 2018/11/4
 */
//商品控制器类
@RestController
@RequestMapping("/product")
public class ProductController {

    //注入ProductService
    @Autowired
    private ProductService productService;

    /*
     * @Description:获取商户的二级商品分类
     * @param: [seller_id]商户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findCategoryBySellerId/{seller_id}")
    public Result findCategoryBySellerId(@PathVariable Integer seller_id){
        List<Category> secondaryList = productService.findCategoryBySellerId(seller_id);
        return ResultUtil.success(secondaryList);
    }

    /*
     * @Description:根据一级目录查询所有的商品尺寸
     * @param: [parent_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findAllProductSizeByPrimaryCategoryId/{primary_id}")
    public Result findAllProductSizeByPrimaryCategoryId(@PathVariable Integer primary_id){
        List<ProductSize> productSizeList = productService.findAllProductSizeByPrimaryCategoryId(primary_id);
        return ResultUtil.success(productSizeList);
    }

    /*
     * @Description:根据商品ID查询商品
     * @param: [product_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/getProductByProductId/{product_id}")
    public Result findOneProductById(@PathVariable Integer product_id){
        Product product = productService.getProductByProductId(product_id);
        return ResultUtil.success(product);
    }

    /*
     * @Description:获取商品分类目录
     * @param: [parentId]父目录ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findCategoryByParentId/{parentId}")
    public Result findPrimaryCategory(@PathVariable Integer parentId){
        List<Category> categoryList = productService.findCategoryByParentId(parentId);
        return ResultUtil.success(categoryList);
    }

    /*
     * @Description:分页查询商户的所有在售商品
     * @param: [sellerId]商户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductBySellerId/{sellerId}")
    public Result findProductBySellerId(@PathVariable Integer sellerId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "50",required = false)Integer countOfPage){
        List<Product> productList = productService.findProductBySellerId(sellerId,page,countOfPage);
        return ResultUtil.success(productList);
    }

    /*
     * @Description:查询商品的所有图片
     * @param: [productId]商品ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductImages/{productId}")
    public Result findProductImages(@PathVariable Integer productId){
        List<ProductImage> imageList = productService.findProductImageByProductId(productId);
        return ResultUtil.success(imageList);
    }

    /*
     * @Description:查询商品的所有颜色
     * @param: [productId]商品ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductColors/{productId}")
    public Result findProductColors(@PathVariable Integer productId){
        List<ProductColor> colorList = productService.findProductColorByProductId(productId);
        return ResultUtil.success(colorList);
    }

    /*
     * @Description:查询商品的所有尺寸
     * @param: [productId]商品ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductSizeByProductId/{productId}")
    public Result findProductSizes(@PathVariable Integer productId){
        Set<ProductSize> colorList = productService.findProductSizeByProductId(productId);
        return ResultUtil.success(colorList);
    }

    /*
     * @Description:查询商品的库存
     * @param: [productNum]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/findProductNum")
    public Result findProductNum(@RequestBody ProductNum productNum){
        Integer num = productService.findProductNum(productNum);
        return ResultUtil.success(num);
    }

    /*
     * @Description:根据分类分页查询商品
     * @param: [categoryId, page, countOfPage]分类ID，当前页，页面商品数量
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getProductByCategoryId/{categoryId}")
    public Result getProductByCategoryId(@PathVariable Integer categoryId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "50",required = false)Integer countOfPage){
        List<Product> productList = productService.getProductByCategoryId(categoryId,page,countOfPage);
        return ResultUtil.success(productList);
    }

    /*
     * @Description:根据分类获取品牌列表
     * @param: [categoryId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getBrandByCategoryId/{categoryId}")
    public Result getBrandByCategoryId(@PathVariable Integer categoryId){
        List<Brand> brandList = productService.getBrandByCategoryId(categoryId);
        return ResultUtil.success(brandList);
    }

    /*
     * @Description:根据商品分类获取商品属性列表
     * @param: [categoryId]分类ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getProductAttributeByCategoryId/{categoryId}")
    public Result getProductAttributeByCategoryId(@PathVariable Integer categoryId){
        List<ProductAttribute> attributeList = productService.getProductAttributeByCategoryId(categoryId);
        return ResultUtil.success(attributeList);
    }
}
