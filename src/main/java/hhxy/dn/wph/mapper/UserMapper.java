package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.mapper.provider.UserProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Result;
import org.springframework.data.annotation.Id;


import java.util.List;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
import static hhxy.dn.wph.constant.FieldConstant.*;

/**
 * @Author: 邓宁
 * @Date: Created in 13:39 2018/11/12
 */
public interface UserMapper {

    /**
     * 注册用户基本信息
     * @param user 用户实体User
     * @return java.lang.Integer
     */
    @Insert("insert into"+ USER +
            "(user_no,telephone,created)" +
            "values(#{userNo},#{telephone},#{created})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    Integer saveUser(User user);

    /**
     * 注册用户密码
     * @param userPassword
     * @return java.lang.Integer
     */
    @Insert("insert into" + USER_PWD + "(user_id,password)values(#{userId},#{password})")
    Integer saveUserPassword(UserPassword userPassword);

    /**
     * 查询用户信息
     * @param telephone
     * @return hhxy.dn.wph.entity.User
     */
    @Select("select" + USER_FIELD + "from"+ USER + "where telephone = #{telephone} and status = 1")
    @Results(id = "userResultMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_no",property = "userNo"),
            @Result(column = "head_image",property = "headImage"),
    })
    User getUserByTelephone(String telephone);

    /**
     * 查询用户密码
     * @param userId
     * @return java.lang.String
     */
    @Select("select password from" + USER_PWD + "where user_id = #{userId}")
    String getPasswordByUserId(Integer userId);

    /**
     * 查询手机号是否注册
     * @param telephone
     * @return java.lang.String
     */
    @Select("select telephone from"+ USER +"where telephone = #{telephone}")
    String getTelephone(String telephone);

    /**
     * 更新用户信息
     * @param user
     * @return java.lang.Integer
     */
    @UpdateProvider(type = UserProvider.class,method = "updateUser")
    Integer updateUser(User user);

    /**
     * 添加用户收货地址
     * @param address
     * @return java.lang.Integer
     */
    @InsertProvider(type = UserProvider.class,method = "saveUserAddress")
    Integer saveUserAddress(UserAddress address);

    /**
     * 更新用户收货地址
     * @param address
     * @return java.lang.Integer
     */
    @UpdateProvider(type = UserProvider.class,method = "updateUserAddress")
    Integer updateUserAddressById(UserAddress address);

    /**
     * 重置默认收货地址
     * @param userId
     * @return java.lang.Integer
     */
    @Update("update"+ USER_ADDRESS +"set is_default = 0 where user_id = #{userId} and status = 1")
    Integer updateAllUserAddressById(Integer userId);

    /**
     * 更新默认收货地址
     * @param id 收货地址ID
     * @return java.lang.Integer
     */
    @Update("update"+ USER_ADDRESS +"set is_default = 1 where id = #{id} and status = 1")
    Integer updateDefaultUserAddressById(Integer id);

    /**
     * 删除用户收货地址
     * @param id 收货地址ID
     * @return java.lang.Integer
     */
    @Delete("delete from"+ USER_ADDRESS +"where id = #{id}")
    Integer deleteUserAddressById(Integer id);

    /**
     * 查询用户所有收货地址
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.UserAddress>
     */
    @Select("select"+ USER_ADDRESS_FIELD +"from"+ USER_ADDRESS +"where user_id = #{userId}")
    List<UserAddress> listAddressByUserId(Integer userId);

    /**
     * 用户搜索商品关键词
     * @param searchTitle 搜索关键词
     * @param userId 用户ID
     * @return java.lang.Integer
     */
    @Insert("insert into"+ SEARCH_HISTORY +"(user_id,search_title)values(#{userId},#{searchTitle})")
    Integer saveSearchHistory(@Param("userId") Integer userId,@Param("searchTitle") String searchTitle);

    /**
     * 查询搜索历史记录
     * 按照主键search_id 降序排列，查询前6条数据(后6条记录)
     * @param userId
     * @return java.util.List<java.lang.String>
     */
    @Select("SELECT search_title FROM"+ SEARCH_HISTORY +
            "WHERE user_id = #{userId} ORDER BY search_id DESC LIMIT 6")
    List<String> listSearchHistory(Integer userId);

    /**
     * 用户清除搜索历史记录
     * @param userId
     * @return java.lang.Integer
     */
    @Delete("delete from"+ SEARCH_HISTORY +"where user_id = #{userId}")
    Integer deleteAllSearchHistory(Integer userId);

    /**
     * 用户收藏商户
     * @param collect
     * @return java.lang.Integer
     */
    @Insert("insert into"+ USER_COLLECT_SELLER +
            "(user_id,seller_id)values(#{userId},#{sellerId})")
    Integer saveCollectSeller(UserCollectSeller collect);

    /**
     * 根据用户ID获得收藏的商户
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.Seller>
     */
    @Select("select"+ SELLER_FIELD +"from"+ SELLER +"where id in(" +
            "select seller_id from"+ USER_COLLECT_SELLER +"where user_id = #{userId})")
    @Results({
        @Result(column = "seller_no",property = "sellerNo"),
        @Result(column = "show_image",property = "showImage")
    })
    List<Seller> listCollectSellerByUserId(Integer userId);

    /**
     * 更新用户头像
     * @param originalFilename 图片路径
     * @param userId
     * @return java.lang.Integer
     */
    @Update("update"+ USER +"set head_image = #{originalFilename} where user_id = #{userId}")
    Integer updateUserHeadIcon(@Param("originalFilename")String originalFilename,@Param("userId") Integer userId);

    /**
     * 查询用户是否收藏了该商户
     * @param sellerId
     * @param userId
     * @return hhxy.dn.wph.entity.UserCollectSeller
     */
    @Select("select user_id,seller_id from" + USER_COLLECT_SELLER +
            "where seller_id = #{sellerId} and user_id = #{userId}")
    @Results({
            @Result(column = "seller_id",property = "sellerId"),
            @Result(column = "user_id",property = "userId")
    })
    UserCollectSeller getUserCollectSeller(@Param("sellerId") Integer sellerId,
                                                @Param("userId") Integer userId);

    /**
     * 根据用户编号获取用户信息
     * @param id
     * @return hhxy.dn.wph.entity.User
     */
    @Select("select"+ USER_FIELD +"from"+ USER +"where id = #{id} and status = 1")
    @ResultMap(value = "userResultMap")
    User getUserById(Integer id);

    /**
     * 查询收货地址是否为默认收货地址
     * @param addressId
     * @return int
     */
    @Select("select is_default from" + USER_ADDRESS +" where id = #{addressId}")
    int getUserAddressIsDefaultById(Integer addressId);
}
