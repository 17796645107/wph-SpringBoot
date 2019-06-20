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
 * @Author: 邓宁
 * @Date: Created in 16:28 2019/4/7
 */

public interface SellerMapper {
    final String seller_field = " id,brand_id,name,show_image,type,status,created,updated ";
    final String brand_field = " id,brand_name,brand_icon ";

    //根据分类ID获取商户信息
    @Select("select"+ seller_field +
            "from"+ SELLER +
            "where type = #{primaryId} and status = 1")
    @Results(id = "SellerMap",value = {
            @Result(property = "showImage",column = "show_image"),
            @Result(property = "brand",column = "brand_id",
                    one = @One(
                            select = "hhxy.dn.wph.mapper.SellerMapper.getBrandById",
                            fetchType = FetchType.EAGER
            ))
    })
    List<Seller> getSellerByPrimaryCategoryId(Integer primaryId);

    //根据品牌ID获取品牌信息
    @Select("select"+ brand_field +
            "from"+ BRAND +"" +
            "where id = #{brandId}")
    @Results(id = "brandMap",value = {
        @Result(column = "brand_id",property = "brandId"),
        @Result(column = "brand_name",property = "brandName"),
        @Result(column = "brand_icon",property = "brandIcon"),
    })
    Brand getBrandById(Integer brandId);

    //根据商户ID获取商户信息
    @Select("select"+ seller_field +
            "from"+ SELLER +
            "where id = #{sellerId} and status = 1")
    @ResultMap(value = "SellerMap")
    Seller getSellerById(Integer sellerId);

    //获取商品收藏量
    @Select("select count(seller_id) from"+ USER_COLLECT_SELLER +"where seller_id = #{sellerId}")
    int getSellerCollectNum(Integer sellerId);

    //添加一个商品
    @InsertProvider(type = SellerProvider.class,method = "saveOneProduct")
    int saveOneProduct(Product product);

    @InsertProvider(type = SellerProvider.class,method = "saveProductSize")
    int saveProductSize(ProductSize productSize);

    @InsertProvider(type = SellerProvider.class,method = "saveProductColor")
    @Options(keyProperty = "color_id",useGeneratedKeys = true)
    int saveProductColor(ProductColor productColor);

    @InsertProvider(type = SellerProvider.class,method = "saveProductImage")
    int saveProductImage(ProductImage productImage);

    @InsertProvider(type = SellerProvider.class,method = "saveProductNum")
    int saveProductNum(ProductNum productNum);

    int saveProductAttributeRelation(ProductAttributeRelation productAttributeRelation);

    @Select("select seller_id from"+ SELLER_ACCOUNT +
            "where seller_username = #{sellerUsername} and seller_password = #{sellerPassword}")
    Integer findSellerAccount(SellerAccount sellerAccount);
}
