package hhxy.dn.wph.controller;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Brand;
import hhxy.dn.wph.entity.Category;
import hhxy.dn.wph.entity.ProductAttribute;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.service.ProductService;
import hhxy.dn.wph.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: 邓宁
 * @Date: Created in 21:47 2018/11/4
 * 商品模块控制器类
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * @Description:获取商户的二级商品分类
     * @param: [seller_id]商户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findCategoryBySellerId/{sellerId}")
    public Result findCategoryBySellerId(@PathVariable Integer sellerId){
        List<Category> secondaryList = productService.listCategoryBySellerId(sellerId);
        return ResultUtil.success(secondaryList);
    }

    /**
     * @Description:根据一级目录查询所有的商品尺寸
     * @param: [parent_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findAllProductSizeByPrimaryCategoryId/{categoryId}")
    public Result findAllProductSizeByPrimaryCategoryId(@PathVariable Integer categoryId){
        List<ProductSize> productSizeList = productService.listProductSizeByCategoryId(categoryId);
        return ResultUtil.success(productSizeList);
    }

    /**
     * @Description:根据商品ID查询商品
     * @param: [product_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/getProductByProductId/{productId}")
    public Result findOneProductById(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);
        return ResultUtil.success(product);
    }

    /**
     * @Description:获取商品分类目录
     * @param: [parentId]父目录ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findCategoryByParentId/{parentId}")
    public Result listPrimaryCategory(@PathVariable Integer parentId){
        List<Category> categoryList = productService.listCategoryByParentId(parentId);
        return ResultUtil.success(categoryList);
    }

    /**
     * @Description:分页查询商户的所有在售商品
     * @param: [sellerId]商户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductBySellerId/{sellerId}")
    public Result listProductBySellerId(@PathVariable Integer sellerId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "50",required = false)Integer countOfPage){
        PageInfo<Product> productList = productService.listProductBySellerId(sellerId,page,countOfPage);
        return ResultUtil.success(productList);
    }

    /**
     * @Description:查询商品的所有图片
     * @param: [productId]商品ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductImages/{productId}")
    public Result listProductImages(@PathVariable Integer productId){
        List<ProductImage> imageList = productService.listProductImageByProductId(productId);
        return ResultUtil.success(imageList);
    }

    /**
     * @Description:查询商品的所有颜色
     * @param: [productId]商品ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductColors/{productId}")
    public Result listProductColors(@PathVariable Integer productId){
        List<ProductColor> colorList = productService.listProductColorByProductId(productId);
        return ResultUtil.success(colorList);
    }

    /**
     * @Description:查询商品的所有尺寸
     * @param: [productId]商品ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findProductSizeByProductId/{productId}")
    public Result listProductSizes(@PathVariable Integer productId){
        List<ProductSize> colorList = productService.listProductSizeByProductId(productId);
        return ResultUtil.success(colorList);
    }

    /**
     * @Description:查询商品的库存
     * @param: [productNum]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/findProductNum")
    public Result listProductNum(@RequestBody ProductNum productNum){
        Integer num = productService.getProductNum(productNum);
        return ResultUtil.success(num);
    }

    /**
     * @Description:根据分类分页查询商品
     * @param: [categoryId, page, countOfPage]分类ID，当前页，页面商品数量
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getProductByCategoryId/{categoryId}")
    public Result getProductByCategoryId(@PathVariable Integer categoryId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "50",required = false)Integer countOfPage){
        List<Product> productList = productService.listProductByCategoryId(categoryId,page,countOfPage);
        return ResultUtil.success(productList);
    }

    /**
     * @Description:根据分类获取品牌列表
     * @param: [categoryId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getBrandByCategoryId/{categoryId}")
    public Result listBrandByCategoryId(@PathVariable Integer categoryId){
        List<Brand> brandList = productService.listBrandByCategoryId(categoryId);
        return ResultUtil.success(brandList);
    }

    /**
     * @Description:根据商品分类获取商品属性列表
     * @param: [categoryId]分类ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getProductAttributeByCategoryId/{categoryId}")
    public Result listProductAttributeByCategoryId(@PathVariable Integer categoryId){
        List<ProductAttribute> attributeList = productService.listProductAttributeByCategoryId(categoryId);
        return ResultUtil.success(attributeList);
    }

    /**
     *
     * @param condition
     * @param pageNum
     * @param pageCount
     * @return hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/listProductInSellerByCondition/{pageNum}/{pageCount}")
    public Result listProductInSellerByCondition(@RequestBody ProductSelectCondition condition,
                    @PathVariable Integer pageNum,@PathVariable Integer pageCount){
        PageInfo<Product> productList = productService.findProductInSeller(condition,pageNum,pageCount);
        return ResultUtil.success(productList);
    }
}
