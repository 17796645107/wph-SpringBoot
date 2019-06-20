
package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.mapper.provider.GoodCartProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

import static hhxy.dn.wph.constant.DataBaseTableConstant.CART;

/**
 * @Author: 邓宁
 * @Date: Created in 22:31 2019/4/28
 */

public interface CartMapper {

    final String CART_FIELD = " id,user_id,product_id,product_number,product_color,product_size,status,created," +
            "updated ";

    /**
     * 根据ID查询单条购物车记录
     * @param id
     * @return hhxy.dn.wph.entity.Cart
     */
    @Select("select"+ CART_FIELD +
            "from"+ CART +" where id = #{id} and status = 1")
    @Results(id = "GoodCartMap",value = {
            @Result(column = "product_id",property = "product",
                    //一对一
                    one = @One(
                            //查询商品信息
                            select = "hhxy.dn.wph.mapper.ProductMapper.getProductByProductId",
                            //查询类型:立即加载
                            fetchType = FetchType.EAGER
                    ))
    })
    Cart getGoodCartById(Integer id);

    /**
     * 生成一条购物车记录
     * @param goodCart
     * @return int
     */
    @InsertProvider(type = GoodCartProvider.class,method = "saveGoodCart")
    int saveCart(Cart goodCart);

    /**
     * 根据条件查询一条购物车记录
     * @param goodCart
     * @return java.lang.Integer 购物车ID
     */
    @Select("select id from"+ CART +
            "where user_id = #{userId} and product_id = #{productId} " +
            "and  product_color = #{productColor} and product_size = #{productSize} and status = 1")
    Integer getCartId(Cart goodCart);

    /**
     * 根据ID更新购物车数量
     * @param cartId
     * @param number
     * @return int
     */
    @Update("update"+ CART +
            "set product_number = product_number + #{number} " +
            "where id = #{cartId} and status = 1")
    int updateGoodCartNumber(@Param("cartId") Integer cartId, @Param("number") Integer number);

    /**
     * 根据用户ID查询所有购物车记录
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.Cart>
     */
    @Select("select"+ CART_FIELD +
            "from"+ CART +"where user_id = #{userId} and status = 1")
    @ResultMap(value = "GoodCartMap")
    List<Cart> listGoodCartByUserId(Integer userId);

    /**
     * 根据ID删除一条购物车记录
     * @param id 购物车ID
     * @return int
     */
    @Delete("delete FROM"+ CART +"where id = #{id}")
    int deleteGoodCartById(Integer id);

    /**
     * 根据用户ID查询购物车记录数
     * @param userId
     * @return java.lang.Integer
     */
    @Select("select COUNT(*) FROM"+ CART +"where user_id = #{userId}  and status = 1")
    Integer getCartCount(Integer userId);

    /**
     *  更新购物车状态
     * @param id 购物车ID
     * @return void
     */
    @Update("update"+ CART + "set status = 0 where id = #{id}")
    void updateGoodCartStatusById(Integer id);

    /**
     * 更新购物车
     * @param goodCart
     * @return java.lang.Integer
     */
    @UpdateProvider(type = GoodCartProvider.class,method = "updateGoodCartById")
    Integer updateGoodCartById(Cart goodCart);
}
