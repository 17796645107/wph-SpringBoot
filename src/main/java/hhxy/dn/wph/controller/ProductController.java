package hhxy.dn.wph.controller;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Brand;
import hhxy.dn.wph.entity.Category;
import hhxy.dn.wph.entity.ProductAttribute;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.ProductExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.ProductException;
import hhxy.dn.wph.service.ProductService;
import hhxy.dn.wph.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author 邓宁
 * @date Created in 21:47 2018/11/4
 * 商品模块控制器类
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 获取商户的二级商品分类
     * @param sellerId 商户ID
     * @return Result
     */
    @GetMapping("/findCategoryBySellerId/{sellerId}")
    public Result findCategoryBySellerId(@PathVariable Integer sellerId){
        List<Category> secondaryList = productService.listCategoryBySellerId(sellerId);
        return ResultUtil.success(secondaryList);
    }

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param categoryId 目录
     * @return Result
     */
    @GetMapping("/findAllProductSizeByPrimaryCategoryId/{categoryId}")
    public Result findProductSizeByPrimaryCategoryId(@PathVariable Integer categoryId){
        List<ProductSize> productSizeList = productService.listProductSizeByCategoryId(categoryId);
        return ResultUtil.success(productSizeList);
    }

    /**
     * 根据商品ID查询商品
     * @param productId 商品ID
     * @return Result
     */
    @GetMapping("/getProductByProductId/{productId}")
    public Result findOneProductById(@PathVariable String productId){
        Product product = productService.getProductById(productId);
        return ResultUtil.success(product);
    }

    /**
     * 获取商品分类目录
     * @param parentId 父目录ID
     * @return Result
     */
    @GetMapping("/findCategoryByParentId/{parentId}")
    public Result listPrimaryCategory(@PathVariable Integer parentId){
        List<Category> categoryList = productService.listCategoryByParentId(parentId);
        return ResultUtil.success(categoryList);
    }

    /**
     * 分页查询商户的所有在售商品
     * @param sellerId 商户ID
     * @return Result
     */
    @GetMapping("/findProductBySellerId/{sellerId}")
    public Result listProductBySellerId(@PathVariable Integer sellerId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "50",required = false)Integer countOfPage){
        PageInfo<Product> productList = productService.listProductBySellerId(sellerId,page,countOfPage);
        return ResultUtil.success(productList);
    }

    /**
     * 查询商品的所有图片
     * @param productId 商品ID
     * @return Result
     */
    @GetMapping("/findProductImages/{productId}")
    public Result listProductImages(@PathVariable String productId){
        List<ProductImage> imageList = productService.listProductImageByProductId(productId);
        return ResultUtil.success(imageList);
    }

    /**
     * 查询商品的所有颜色
     * @param productId 商品ID
     * @return Result
     */
    @GetMapping("/findProductColors/{productId}")
    public Result listProductColors(@PathVariable String productId){
        List<ProductColor> colorList = productService.listProductColorByProductId(productId);
        return ResultUtil.success(colorList);
    }

    /**
     * 查询商品的所有尺寸
     * @param productId 商品ID
     * @return Result
     */
    @GetMapping("/findProductSizeByProductId/{productId}")
    public Result listProductSizes(@PathVariable String productId){
        List<ProductSize> colorList = productService.listProductSizeByProductId(productId);
        return ResultUtil.success(colorList);
    }

    /**
     * 查询商品的库存
     * @param productNum
     * @return Result
     */
    @PostMapping("/findProductNum")
    public Result listProductNum(@RequestBody ProductNum productNum){
        Integer num = productService.getProductNum(productNum);
        return ResultUtil.success(num);
    }

    /**
     * 根据二级分类分页查询商品
     * @param categoryId, page, countOfPage 分类ID，当前页，页面商品数量
     * @return Result
     */
    @RequestMapping("/getProductByCategoryId/{categoryId}")
    public Result getProductByCategoryId(@PathVariable Integer categoryId,
            @RequestParam(value = "page",defaultValue = "1",required = false)Integer page,
            @RequestParam(value = "countOfPage",defaultValue = "50",required = false)Integer countOfPage){
        List<Product> productList = productService.listProductByCategoryId(categoryId,page,countOfPage);
        return ResultUtil.success(productList);
    }

    /**
     * 根据分类获取品牌列表
     * @param categoryId
     * @return Result
     */
    @RequestMapping("/getBrandByCategoryId/{categoryId}")
    public Result listBrandByCategoryId(@PathVariable Integer categoryId){
        List<Brand> brandList = productService.listBrandByCategoryId(categoryId);
        return ResultUtil.success(brandList);
    }

    /**
     * 根据商品分类获取商品属性列表
     * @param categoryId 分类ID
     * @return Result
     */
    @RequestMapping("/getProductAttributeByCategoryId/{categoryId}")
    public Result listProductAttributeByCategoryId(@PathVariable Integer categoryId){
        List<ProductAttribute> attributeList = productService.listProductAttributeByCategoryId(categoryId);
        return ResultUtil.success(attributeList);
    }

    /**
     * 分页检索商品
     * @param condition 检索条件
     * @param pageNum 当前页
     * @param pageCount 每页数量
     * @return Result
     */
    @RequestMapping("/listProductInSellerByCondition/{pageNum}/{pageCount}")
    public Result listProductInSellerByCondition(@RequestBody ProductSelectCondition condition,
                    @PathVariable Integer pageNum,@PathVariable Integer pageCount){
        PageInfo<Product> productList = productService.findProductInSeller(condition,pageNum,pageCount);
        return ResultUtil.success(productList);
    }

    /**
     * 废弃
     * @return Result
     */
    @RequestMapping("/categoryTree")
    public Result listCategoryTree(){
        List<Category> categoryList = productService.listCategoryTree();
        return ResultUtil.success(categoryList);
    }

    @GetMapping("/searchProduct/{userId}/{productTitle}")
    public Result userSearchProduct(@PathVariable int userId,@PathVariable String productTitle){
        if (productTitle == null){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        List<Product> productList = productService.searchProduct(userId,productTitle);
        return ResultUtil.success(productList);

    }

}
