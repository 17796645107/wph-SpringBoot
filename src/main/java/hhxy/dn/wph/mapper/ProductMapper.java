package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.mapper.provider.ProductProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.mapping.FetchType;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
import static hhxy.dn.wph.constant.FieldConstant.*;
import java.util.List;

/**
 * @author  邓宁
 * @date Created in 21:49 2018/11/4
 */
public interface ProductMapper {

    /**
     * 根据商品ID查询商品
     * @param productId 商品ID
     * @return Product
     */
    @Select("select"+ PRODUCT_FIELD +"from"+ PRODUCT +"where product_no = #{productId} and state = 1")
    @Results(id = "productResultMap",value = {
            @Result(property = "productNo",column = "product_no"),
            @Result(property = "categoryId",column = "category_id"),
            @Result(property = "sellerId",column = "seller_id"),
            @Result(property = "brandId",column = "brand_id"),
            @Result(property = "isHot",column = "is_hot"),
            @Result(property = "isNew",column = "is_new"),
            //查询商品默认图片
            @Result(property = "defaultImage",column = "product_no",
                    one = @One(
                            select = "hhxy.dn.wph.mapper.ProductMapper.getImageByProductId",
                            //查询类型:立即加载
                            fetchType = FetchType.EAGER
                    )
            ),
            //查询商户
            @Result(property = "seller",column = "seller_id",javaType = Seller.class,
                    one = @One(
                            select = "hhxy.dn.wph.mapper.SellerMapper.getSellerById",
                            fetchType = FetchType.EAGER
                    )
            )
    })
    Product getProductById(String productId);

    /**
     * 查询简单的商品信息,不需要很多关联查询
     * @param productId
     * @return Product
     */
    @Select("select"+ PRODUCT_FIELD +"from"+ PRODUCT +"where product_no = #{productId} and state = 1")
    @Results({
            //查询商品默认图片
            @Result(property = "defaultImage",column = "product_no",
                    one = @One(
                            select = "hhxy.dn.wph.mapper.ProductMapper.getImageByProductId",
                            //查询类型:立即加载
                            fetchType = FetchType.EAGER
                    )
            )
    })
    Product getSimpleProductById(Integer productId);

    /**
     * 获取商品分类目录,根据category_sort排序
     * @param parentId 父目录ID
     * @return List<Category>
     */
    @Select("select" + CATEGORY_FIELD +
            "from"+ CATEGORY +
            "where state = 1 and parent_id = #{parentId} order by category_sort")
    @Results(id = "categoryResultMap",value = {
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "parent_id",property = "parentId"),
            @Result(column = "category_sort",property = "categorySort"),
            @Result(column = "admin_id",property = "adminId")
    })
    List<Category> listCategoryByParentId(Integer parentId);

    /**
     * 查询商户的商品二级目录
     * @param sellerId
     * @return java.util.List<hhxy.dn.wph.entity.Category>
     */
    @Select("SELECT "+ CATEGORY_FIELD +
            "FROM "+ CATEGORY +
            "WHERE id in (" +
                                    "SELECT distinct category_id " +
                                    "FROM tb_product " +
                                    "WHERE seller_id = #{sellerId} and state = 1"+
                                    ")" +
            "and state = 1 " +
            "ORDER BY category_sort")
    @ResultMap(value = "categoryResultMap")
    List<Category> listCategoryBySellerId(Integer sellerId);

    /**
     * 根据商品ID查询所有的商品Size
     * @param productId product_no商品编号
     * @return List<ProductSize>
     */
    @Select("select id,size from"+ PRODUCT_SIZE +"where product_id = #{productId}")
    List<ProductSize> listProductSizeByProductId(String productId);

    /**
     * 根据一级目录查询所有的商品尺寸
     * @param categoryId 一级目录
     * @return List<ProductSize>
     */
    @SelectProvider(type = ProductProvider.class,method = "findAllProductSizeByPrimaryCategoryId")
    List<ProductSize> listProductSizeByCategoryId(int categoryId);

    /**
     * 查询商户所有商品
     * @param sellerId 商户ID
     * @return List<Product>
     */
    @Select("select"+ PRODUCT_FIELD +"from"+ PRODUCT +"where seller_id = #{sellerId} and state = 1")
    @ResultMap(value = "productResultMap")
    List<Product> listProductBySellerId(Integer sellerId);

    /**
     * 查询商品颜色
     * @param productId 商品编号
     * @return List<ProductColor>
     */
    @Select("select id,product_id,color " +
            "from"+ PRODUCT_COLOR +"where product_id = #{productId}")
    @Results({
            @Result(property = "productId",column = "product_id")
    })
    List<ProductColor> listProductColorByProductId(String productId);

    /**
     * 查询商品图片
     * @param productId 商品编号
     * @return List<ProductImage>
     */
    @Select("select id,product_id,image,color_id "+
            "from"+ PRODUCT_IMAGE +
            "where product_id = #{productId}")
    @Results(id = "productImageResultMap",value = {
            @Result(property = "productId",column = "product_id"),
            @Result(property = "colorId",column = "color_id"),
    })
    List<ProductImage> listProductImageByProductId(String productId);

    /**
     * 根据图片ID获取图片路径
     * @param imageId
     * @return java.lang.String
     */
    @Select("select image from"+ PRODUCT_IMAGE +"where id = #{imageId}")
    String getImageById(Integer imageId);

    /**
     * 根据商品ID获取图片，默认为第一张
     * @param productId 商品编号
     * @return hhxy.dn.wph.entity.ProductImage
     */
    @Select("select id,product_id,image,color_id from"+ PRODUCT_IMAGE +"where product_id = #{productId} " +
            " order by id limit 1")
    @ResultMap(value = "productImageResultMap")
    ProductImage getImageByProductId(String productId);

    /**
     * 查询商品库存
     * @param productNum
     * @return java.util.List<java.lang.Integer>
     */
    @SelectProvider(type = ProductProvider.class,method = "findProductNum")
    List<Integer> listProductNum(ProductNum productNum);

    /**
     * 查询商品尺寸
     * @param productNum
     * @return java.util.List<java.lang.Integer>
     */
    /*@Select("select num from" + PRODUCT_NUM + "where product_size = #{product_size} and product_id = #{productId}")
    List<Integer> listProductNumBySize(ProductNum productNum);
*/

    /**
     * 更新商品库存
     * @param productId
     * @param productColor
     * @param productSize
     * @param productNumber
     * @return java.lang.Integer
     */
    @UpdateProvider(type = ProductProvider.class,method = "updateProductNum")
    Integer updateProductNum(@Param("productId") String productId,
                          @Param("productColor") String productColor,
                          @Param("productSize") String productSize,
                          @Param("productNumber") Integer productNumber);

    /**
     * 查询商品数量
     * @param categoryId
     * @param sellerId
     * @return java.lang.Integer
     */
    @Select("select count(*) from" + PRODUCT +
            "where category_id = #{categoryId} and seller_id = #{sellerId} and state = 1")
    Integer getProductCount(@Param("categoryId") Integer categoryId,@Param("sellerId") Integer sellerId);

    /**
     * 根据目录ID查询商品
     * @param categoryId 分类Id
     * @return List<Product>
     */
    @Select("select"+ PRODUCT_FIELD +"from"+ PRODUCT +"where category_id = #{categoryId} and state = 1")
    @ResultMap(value = "productResultMap")
    List<Product> listProductByCategoryId(Integer categoryId);

    /**
     * 查询品牌列表
     * @param categoryId 分类Id
     * @return List<Brand>
     */
    @Select("select id,brand_name,brand_icon from"+ BRAND +
            " where id in(" +
                            "select brand_id from"+
                             PRODUCT +"where category_id = #{categoryId} and state = 1" +
            ")")
    @Results(id = "brandResultMap",value = {
            @Result(column = "brand_id",property = "brandId"),
            @Result(column = "brand_name",property = "brandName"),
            @Result(column = "brand_icon",property = "brandIcon")
    })
    List<Brand> listBrandByCategoryId(Integer categoryId);

    /**
     * 查询商品属性
     * @param categoryId 二级目录ID
     * @return java.util.List<hhxy.dn.wph.entity.ProductAttribute>
     */
    @Select("select"+ PRODUCT_ATTRIBUTE_FIELD +
            "from"+ PRODUCT_ATTRIBUTE +
            "where id in("+
                            "SELECT attribute_id FROM "+
                            CATEGORY_ATTRIBUTE +"where category_id = #{categoryId}"+
                            ")"
            )
    @Results(id = "productAttributeResultMap",value = {
            @Result(column = "attr_id",property = "attrId"),
            @Result(column = "attr_name",property = "attrName"),
            @Result(column = "is_search",property = "isSearch"),
    })
    List<ProductAttribute> listProductAttributeByCategoryId(Integer categoryId);

    /**
     * 查询商品属性值
     * @param attributeId
     * @return java.util.List<hhxy.dn.wph.entity.ProductAttributeValue>
     */
    @Select("select id,value from"+
            PRODUCT_ATTRIBUTE_VALUE +"where id in(" +
                            "select value_id from"+ PRODUCT_ATTRIBUTE_RELATION +
                            "where attribute_id = #{attributeId} and state = 1" +
            ") and state = 1")
    List<ProductAttributeValue> listProductAttributeValueByAttributeId(Integer attributeId);

    /**
     * 查询商品ID
     * @param attributeRelation 属性ID和属性值ID
     * @return java.util.List<java.lang.Integer>
     */
    @Select("select product_id from"+ PRODUCT_ATTRIBUTE_RELATION +
            "where attribute_id = #{attributeId} and value_id = #{valueId}")
    List<Integer> listProductIdByAttribute(ProductAttributeRelation attributeRelation);

   /**
    * 查询商品ID
    * @param attributeRelation
    * @param productIdList
    * @return java.util.List<java.lang.Integer>
    */
    @SelectProvider(type = ProductProvider.class,method = "findProductByArrtibute")
    List<Integer> listProductId(
            @Param("attributeRelation") ProductAttributeRelation attributeRelation,
            @Param("productIdArray") Object[] productIdList);

    /**
     * 检索商品
     * @param condition 检索条件
     * @return List<Product>
     */
    @SelectProvider(type = ProductProvider.class,method = "findProductInSeller")
    @ResultMap(value = "productResultMap")
    List<Product> listProductInSeller(ProductSelectCondition condition);

    /**
     * 查询所有商品分类
     * @return List<Category>
     */
    @Select("select id, category_name, category_sort, parent_id, admin_id, state, created\n" +
            "from tb_category where parent_id = 0 and state = 1")
    @Results({
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_sort",property = "categorySort"),
            @Result(column = "parent_id",property = "parentId"),
            @Result(column = "admin_id",property = "adminId"),
            //一对多
            @Result(column = "id",property = "children",many = @Many(
                    select = "hhxy.dn.wph.mapper.ProductMapper.listCategoryByParentId",
                    fetchType = FetchType.EAGER)
            )
    })
    List<Category> CATEGORY_LIST();

    @SelectProvider(type = ProductProvider.class,method = "listProductByTitle")
    List<Product> listProductByTitle(String productTitle);


    /**
     * 根据父Id查询商品分类
     * @param parentId
     * @return List<Category>
     */
    /*@Select("select id, category_name, category_sort, parent_id, admin_id, state, created\n" +
            "from tb_category where parent_id = #{parentId} and state = 1")
    List<Category> listCategoryByParentId(int parentId);*/




    /*@Update("update tb_product set product_no = #{productNo} where id = #{productId}")
    int updateProduct(@Param("productNo") String productNo,@Param("productId") Integer productId);
    @Update("update tb_product_attribute_relation set product_id = #{productId} where product_id = #{productNo}")
    int updateProductAttributeRelation(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_product_color set product_id = #{productId} where product_id = #{productNo}")
    int updateProductColor(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_product_evaluation set product_id = #{productId} where product_id = #{productNo}")
    int updateProductEvaluation(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_product_image set product_id = #{productId} where product_id = #{productNo}")
    int updateProductImage(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_product_num set product_id = #{productId} where product_id = #{productNo}")
    int updateProductNum1(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_product_size set product_id = #{productId} where product_id = #{productNo}")
    int updateProductImageSize(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_cart set product_id = #{productId} where product_id = #{productNo}")
    int updateCart(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Update("update tb_order_product set product_id = #{productId} where product_id = #{productNo}")
    int updateOrderProduct(@Param("productId") Integer productId,@Param("productNo") Integer productNo);
    @Select("select id from tb_product")
    List<Integer> listProductId();
    @Select("select product_no from tb_product where id = #{id}")
    String getProductNo(Integer id);*/
}
