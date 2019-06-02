
package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.GoodCart;
import hhxy.dn.wph.mapper.provider.GoodCartProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

import static hhxy.dn.wph.util.DBTableUtil.CART;

/**
 * @Author: 邓宁
 * @Date: Created in 22:31 2019/4/28
 */

public interface CartMapper {

    final String CoodCart_field = " cart_id,user_id,product_id,product_number,product_color,product_size,state,created,updated ";

    @Select("select"+ CoodCart_field +
            "from"+ CART +" where cart_id = #{id} and state = 1")
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
    //根据ID查询单条购物车记录
    GoodCart getOneGoodCart(Integer id);

    @InsertProvider(type = GoodCartProvider.class,method = "saveGoodCart")
    //生成一条购物车记录
    int insert(GoodCart goodCart);

    @Select("select cart_id from "+ CART +
            "where user_id = #{user_id} and product_id = #{product_id} " +
            "and  product_color = #{product_color} and product_size = #{product_size} and state = 1")
    //根据条件查询一条购物车记录
    Integer selectGoodCart(GoodCart goodCart);

    @Update("update"+ CART +
            "set product_number = product_number + #{number} " +
            "where cart_id = #{cartId} and state = 1")
    //根据ID更新购物车数量
    int updateGoodCartNumber(@Param("cartId") Integer cartId, @Param("number") Integer number);

    //根据用户ID查询所有购物车记录
    @Select("select"+ CoodCart_field +
            "from"+ CART +"where user_id = #{userId} and state = 1")
    @ResultMap(value = "GoodCartMap")
    List<GoodCart> getGoodCart(Integer userId);

    //根据ID删除一条购物车记录
    @Delete("delete FROM"+ CART +"where cart_id = #{id}")
    int deleteGoodCartById(Integer id);

    //根据用户ID查询购物车记录数
    @Select("select COUNT(cart_id) FROM"+ CART +"where user_id = #{userId}  and state = 1")
    Integer getCartCount(Integer userId);

    @Update("update"+ CART + "set state = 0 where cart_id = #{id}")
    void updateStateGoodCartById(Integer id);

    @UpdateProvider(type = GoodCartProvider.class,method = "updateGoodCartById")
    Integer updateGoodCartById(GoodCart goodCart);
}
