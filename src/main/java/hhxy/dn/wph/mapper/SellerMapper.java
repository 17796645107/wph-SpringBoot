package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Brand;
import hhxy.dn.wph.entity.ProductAttributeRelation;
import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.mapper.provider.SellerProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.mapping.FetchType;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
import java.util.List;

/**
 * @author 邓宁
 * @date Created in 16:28 2019/4/7
 */

public interface SellerMapper {
    String SELLER_FIELD = " id,brand_id,name,show_image,type,state,created ";
    String BRAND_FIELD = " id,brand_name,brand_icon ";

    /**
     * 根据分类ID获取商户信息
     * @param categoryId
     * @return java.util.List<hhxy.dn.wph.entity.Seller>
     */
    @Select("select"+ SELLER_FIELD +
            "from"+ SELLER +
            "where type = #{categoryId} and state = 1")
    @Results(id = "SellerMap",value = {
            @Result(property = "showImage",column = "show_image"),
            @Result(property = "brand",column = "brand_id",
                    one = @One(
                            select = "hhxy.dn.wph.mapper.SellerMapper.getBrandById",
                            fetchType = FetchType.EAGER
            ))
    })
    List<Seller> listSellerByCategoryId(Integer categoryId);

    /**
     * 根据品牌ID获取品牌信息
     * @param brandId 品牌ID
     * @return Brand
     */
    @Select("select"+ BRAND_FIELD +
            "from"+ BRAND +"" +
            "where id = #{brandId}")
    @Results(id = "brandMap",value = {
        @Result(column = "brand_id",property = "brandId"),
        @Result(column = "brand_name",property = "brandName"),
        @Result(column = "brand_icon",property = "brandIcon"),
    })
    Brand getBrandById(Integer brandId);

    /**
     * 根据商户ID获取商户信息
     * @param sellerId 商户ID
     * @return Seller
     */
    @Select("select"+ SELLER_FIELD +
            "from"+ SELLER +
            "where id = #{sellerId} and state = 1")
    @ResultMap(value = "SellerMap")
    Seller getSellerById(Integer sellerId);

    /**
     * 获取商品收藏量
     * @param sellerId
     * @return int
     */
    @Select("select count(*) from"+ USER_COLLECT_SELLER +"where seller_id = #{sellerId}")
    int getSellerCollectNum(Integer sellerId);

    /**
     * 添加一个商品
     * @param product
     * @return int
     */
    @InsertProvider(type = SellerProvider.class,method = "saveProduct")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    int saveProduct(Product product);

    /**
     * 添加商品尺寸
     * @param productSize
     * @return int
     */
    @InsertProvider(type = SellerProvider.class,method = "saveProductSize")
    int saveProductSize(ProductSize productSize);

    /**
     * 添加商品颜色
     * @param productColor
     * @return int
     */
    @InsertProvider(type = SellerProvider.class,method = "saveProductColor")
    @Options(keyProperty = "id",useGeneratedKeys = true)
    int saveProductColor(ProductColor productColor);

    /**
     * 添加商品图片
     * @param productImage
     * @return int
     */
    @InsertProvider(type = SellerProvider.class,method = "saveProductImage")
    int saveProductImage(ProductImage productImage);

    /**
     * 添加商品库存
     * @param productNum
     * @return int
     */
    @InsertProvider(type = SellerProvider.class,method = "saveProductNum")
    int saveProductNum(ProductNum productNum);

    /**
     * 添加商品属性-属性值关系
     * @param productAttributeRelation
     * @return int
     */
    int saveProductAttributeRelation(ProductAttributeRelation productAttributeRelation);

    /**
     * 查询商户ID
     * @param sellerAccount
     * @return java.lang.Integer
     */
    @Select("select seller_id from"+ SELLER_ACCOUNT +
            "where seller_username = #{sellerUsername} and seller_password = #{sellerPassword}")
    Integer getSellerId(SellerAccount sellerAccount);

    /**
     *
     * @param sellerId
     * @return
     */
    @Select("select ")
    List<Product> listProductById(int sellerId);

    @UpdateProvider(type = SellerProvider.class,method = "updateProduct")
    void updateProduct(Product product);
}
