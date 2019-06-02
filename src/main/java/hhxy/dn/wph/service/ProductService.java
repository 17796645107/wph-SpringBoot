package hhxy.dn.wph.service;

import hhxy.dn.wph.domain.Brand;
import hhxy.dn.wph.domain.Category;
import hhxy.dn.wph.domain.ProductAttribute;
import hhxy.dn.wph.entity.*;

import java.util.List;
import java.util.Set;

/**
 * @Author: 邓宁
 * @Date: Created in 21:48 2018/11/4
 */

public interface ProductService {
    //查询所有商品二级目录
    List<Category> findCategoryBySellerId(Integer seller_id);

    //根据一级目录查询所有的商品尺寸
    List<ProductSize> findAllProductSizeByPrimaryCategoryId(Integer primary_id);

    //根据商品ID(主键)查询商品
    Product getProductByProductId(Integer product_id);

    //添加商品
    int saveProduct(Product product);

    //根据商家查询商品
    List<Product> findProductBySellerId(Integer sellerId,Integer page,Integer countOfPage);

    //根据商品ID查询商品的所有颜色
    List<ProductColor> findProductColorByProductId(Integer productId);

    //根据商品ID查询商品的所有尺寸
    Set<ProductSize> findProductSizeByProductId(Integer productId);

    //根据商品ID查询商品的所有图片
    List<ProductImage> findProductImageByProductId(Integer productId);

    //查询商品库存
    Integer findProductNum(ProductNum productNum);

    //根据父ID查询分类
    List<Category> findCategoryByParentId(Integer parentId);

    //根据分类ID分页查询商品列表
    List<Product> getProductByCategoryId(Integer categoryId, Integer page, Integer countOfPage);

    //根据分类ID查询品牌列表
    List<Brand> getBrandByCategoryId(Integer categoryId);

    //根据分类ID查询商品的所有属性及属性值
    List<ProductAttribute> getProductAttributeByCategoryId(Integer categoryId);
}
