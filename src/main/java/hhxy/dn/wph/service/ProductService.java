package hhxy.dn.wph.service;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Brand;
import hhxy.dn.wph.entity.Category;
import hhxy.dn.wph.entity.ProductAttribute;
import hhxy.dn.wph.entity.*;

import java.util.List;

/**
 * @author 邓宁
 * @date Created in 21:48 2018/11/4
 */

public interface ProductService {
    /**
     * 查询所有商品二级目录
     * @param sellerId 商户ID
     * @return java.util.List<hhxy.dn.wph.entity.Category>
     */
    List<Category> listCategoryBySellerId(Integer sellerId);

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param categoryId 一级目录ID
     * @return java.util.List<hhxy.dn.wph.entity.ProductSize>
     */
    List<ProductSize> listProductSizeByCategoryId(Integer categoryId);

    /**
     * 根据商品ID(主键)查询商品
     * @param productId 商品ID(主键)
     * @return Product
     */
    Product getProductById(String productId);

    /**
     * 分页查询,根据商家查询商品
     * @param sellerId 商户ID
     * @param pageIndex 当前页数
     * @param pageCount 每页显示数量
     * @return PageInfo<Product>
     */
    PageInfo<Product> listProductBySellerId(Integer sellerId, Integer pageIndex, Integer pageCount);

    /**
     * 根据商品ID查询商品的所有颜色
     * @param productId 商品ID
     * @return java.util.List<hhxy.dn.wph.entity.ProductColor>
     */
    List<ProductColor> listProductColorByProductId(String productId);

    /**
     * 根据商品ID查询商品的所有尺寸
     * @param productId 商品ID
     * @return java.util.List<hhxy.dn.wph.entity.ProductSize>
     */
    List<ProductSize> listProductSizeByProductId(String productId);

    /**
     * 根据商品ID查询商品的所有图片
     * @param productId 商品ID
     * @return java.util.List<hhxy.dn.wph.entity.ProductImage>
     */
    List<ProductImage> listProductImageByProductId(String productId);

    /**
     * 动态查询商品库存,
     * <p>传入productId 查商品库存总量</p>
     * <p>传入productId,productColor 根据ID和颜色查商品库存</p>
     * <p>传入productId,productColor,productSize 查单品库存</p>
     * @param productNum 库存实体
     * @return java.lang.Integer 商品库存
     */
    Integer getProductNum(ProductNum productNum);

    /**
     * 根据父ID查询分类
     * @param parentId
     * @return java.util.List<hhxy.dn.wph.entity.Category>
     */
    List<Category> listCategoryByParentId(Integer parentId);

    /**
     * 分页查询,根据分类ID分页查询商品列表
     * @param categoryId 分类ID
     * @param page 当前页数
     * @param countOfPage 页面显示数量
     * @return java.util.List<hhxy.dn.wph.entity.Product>
     */
    List<Product> listProductByCategoryId(Integer categoryId, Integer page, Integer countOfPage);

    /**
     * 根据分类ID查询品牌列表
     * @param categoryId 分类ID
     * @return java.util.List<hhxy.dn.wph.entity.Brand>
     */
    List<Brand> listBrandByCategoryId(Integer categoryId);

    /**
     * 根据分类ID查询商品的所有属性及属性值
     * @param categoryId
     * @return java.util.List<hhxy.dn.wph.entity.ProductAttribute>
     */
    List<ProductAttribute> listProductAttributeByCategoryId(Integer categoryId);

    /**
     * 检索商品
     * @param condition
     * @param pageNum
     * @param pageCount
     * @return java.util.List<hhxy.dn.wph.entity.Product>
     */
    PageInfo<Product> findProductInSeller(ProductSelectCondition condition,Integer pageNum,Integer pageCount);

    List<Category> listCategoryTree();

    List<Product> searchProduct(int userId, String productTitle);
}
