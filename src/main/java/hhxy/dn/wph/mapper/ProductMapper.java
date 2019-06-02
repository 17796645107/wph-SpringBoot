package hhxy.dn.wph.mapper;

import hhxy.dn.wph.domain.*;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.mapper.provider.ProductProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.mapping.FetchType;

import static hhxy.dn.wph.util.DBTableUtil.*;
import java.util.List;
import java.util.Set;

/**
 * @Author: 邓宁
 * @Date: Created in 21:49 2018/11/4
 */
public interface ProductMapper {
    final String product_field = " product_id,seller_id,category_id,title,detail,price,collect,is_hot,is_new ";
    final String productAttribute_field = " attr_id,attr_name ";
    final String categort_field = " category_id,category_name,category_sort,parent_id,admin_id ";

    //根据商品ID查询商品
    @Select("select"+ product_field +"from"+ PRODUCT +"where product_id = #{product_id} and status = 1")
    @Results(id = "productMap",value = {
            @Result(column = "product_id",property = "default_image",
                    one = @One(
                            //查询商品默认图片
                            select = "hhxy.dn.wph.mapper.ProductMapper.getImageByProductId",
                            //查询类型:立即加载
                            fetchType = FetchType.EAGER
                    )
            ),
            @Result(property = "seller",column = "seller_id",javaType = Seller.class,
                    one = @One(
                            select = "hhxy.dn.wph.mapper.SellerMapper.getSellerById",
                            fetchType = FetchType.EAGER
                    )
            )
    })
    Product getProductByProductId(Integer product_id);

    //获取商品分类目录,根据category_sort排序
    @Select("select category_id,category_name,parent_id,category_sort " +
            "from"+ CATEGORY +
            "where status = 1 and parent_id = #{parentId} order by category_sort")
    @Results(id = "categoryMap",value = {
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "parent_id",property = "parentId"),
            @Result(column = "category_sort",property = "categorySort"),
            @Result(column = "admin_id",property = "adminId")
    })
    List<Category> findCategoryByParentId(Integer parentId);

    //查询商户的商品二级目录
    @Select("SELECT distinct"+ categort_field +
            "FROM "+ CATEGORY +
            "WHERE category_id in (" +
                                    "SELECT category_id " +
                                    "FROM tb_product " +
                                    "WHERE seller_id = #{seller_id}"+
                                    ")" +
            "and status = 1 " +
            "ORDER BY category_sort")
    @ResultMap(value = "categoryMap")
    List<Category> findCategoryBySellerId(Integer seller_id);

    //根据商品ID查询所有的商品Size
    @Select("select size_id,size from"+ PRODUCT_SIZE +"where product_id = #{product_id} and status=1")
    Set<ProductSize> findProductSizeByProductId(Integer product_id);

    //根据一级目录查询所有的商品尺寸
    @SelectProvider(type = ProductProvider.class,method = "findAllProductSizeByPrimaryCategoryId")
    List<ProductSize> findAllProductSizeByPrimaryCategoryId(Integer primary_id);

    //添加商品
    @InsertProvider(type = ProductProvider.class,method = "saveProduct")
    int saveProduct(Product product);

    //查询商户所有商品
    @Select("select"+ product_field +"from"+ PRODUCT +"where seller_id = #{sellerId} and status = 1")
    @Results(id = "productImageMap",value = {
            @Result(column = "product_id",property = "default_image",
                one = @One(
                        //查询商品默认图片
                        select = "hhxy.dn.wph.mapper.ProductMapper.getImageByProductId",
                        //查询类型:立即加载
                        fetchType = FetchType.EAGER
                )
            )
    })
    List<Product> findProductBySellerId(Integer sellerId);

    @Select("select color_id,product_id,color " +
            "from"+ PRODUCT_COLOR +"where product_id = #{productId} and status = 1")
    List<ProductColor> findProductColorByProductId(Integer productId);

    @Select("select image_id,product_id,image,color_id "+
            "from"+ PRODUCT_IMAGE +
            "where product_id = #{productId} and status = 1")
    List<ProductImage> findProductImageByProductId(Integer productId);

    //根据图片ID获取图片
    @Select("select image from"+ PRODUCT_IMAGE +"where image_id = #{imageId}")
    String getImageById(Integer imageId);

    //根据商品ID获取图片，默认为第一张
    @Select("select image_id,product_id,image from"+ PRODUCT_IMAGE +"where product_id = #{product_id} " +
            "order by image_id limit 1")
    ProductImage getImageByProductId(Integer product_id);

    @SelectProvider(type = ProductProvider.class,method = "findProductNum")
    List<Integer> findProductNum(ProductNum productNum);

    @Select("select num from" + PRODUCT_NUM + "where product_size = #{product_size} and product_id = #{product_id}")
    List<Integer> findProductNumBySize(ProductNum productNum);

    @Select("select num from" + PRODUCT_NUM + "where product_color = #{product_color} and product_id = #{product_id}")
    List<Integer> findProductNumByColor(ProductNum productNum);

    @UpdateProvider(type = ProductProvider.class,method = "updateProductNum")
    Integer updateProductNum(@Param("product_id") Integer product_id,
                          @Param("product_color") String product_color,
                          @Param("product_size") String product_size,
                          @Param("product_number") Integer product_number);

    @Select("select count(product_id) from" + PRODUCT +
            "where category_id = #{categoryId} and seller_id = #{sellerId} and status = 1")
    Integer getProductCountBySecoundaryCategory(@Param("categoryId") Integer categoryId,@Param("sellerId") Integer sellerId);

    @Select("select"+ product_field +"from"+ PRODUCT +"where category_id = #{categoryId} and status = 1")
    @ResultMap(value = "productMap")
    List<Product> getProductByCategoryId(Integer categoryId);

    @Select("select brand_id,brand_name,brand_icon from"+ BRAND +
            " where brand_id in(" +
                                "select brand_id from"+
                                 PRODUCT +"where category_id = #{categoryId} and status = 1" +
            ")")
    @Results(id = "brandMap",value = {
            @Result(column = "brand_id",property = "brandId"),
            @Result(column = "brand_name",property = "brandName"),
            @Result(column = "brand_icon",property = "brandIcon")
    })
    List<Brand> getBrandByCategoryId(Integer categoryId);

    @Select("select"+ productAttribute_field +
            "from"+ PRODUCT_ATTRIBUTE +
            "where attr_id in("+
                            "SELECT attribute_id FROM "+
                             CATEGORY_ATTRIBUTE_RELATION +"where category_id = #{categoryId}"+
                            ")"
            )
    @Results(id = "productAttributeMap",value = {
            @Result(column = "attr_id",property = "attrId"),
            @Result(column = "attr_name",property = "attrName")
    })
    List<ProductAttribute> getProductAttributeByCategoryId(Integer categoryId);

    @Select("select value_id,value from"+
            PRODUCT_ATTRIBUTE_VALUE +"where value_id in(" +
                            "select value_id from"+ PRODUCT_ATTRIBUTE_RELATION +
                            "where attribute_id = #{attrId} and status = 1" +
            ") and status = 1")
    @Results({@Result(column = "value_id",property = "valueId")})
    List<ProductAttributeValue> getProductAttributeValueByAttributeId(Integer attrId);

//    @SelectProvider(type = ProductProvider.class,method = "findProductByArrtibute")
    @Select("select product_id from"+ PRODUCT_ATTRIBUTE_RELATION +
            "where attribute_id = #{attributeId} and value_id = #{valueId}")
    List<Integer> findProductByArrtibute(ProductAttributeRelation attributeRelation);

   /* @Select("select product_id from"+ PRODUCT_ATTRIBUTE_RELATION +
            "where attribute_id = #{attributeRelation.attributeId} and value_id = #{attributeRelation.valueId} " +
            "and product_id in (#{productIdArray}) ")*/
    @SelectProvider(type = ProductProvider.class,method = "findProductByArrtibute1")
    List<Integer> findProductByArrtibute1(
            @Param("attributeRelation") ProductAttributeRelation attributeRelation,
            @Param("productIdArray") Object[] productIdList);

    @SelectProvider(type = ProductProvider.class,method = "findProductInSeller")
    @ResultMap(value = "productImageMap")
    List<Product> findProductInSeller(
            @Param("seller_id") Integer seller_id,@Param("secoundCategoryId") Integer secoundCategoryId,
            @Param("size_id")Integer size_id,@Param("type") Integer type, @Param("hasNum")Integer hasNum);
}
