package hhxy.dn.wph.service;

import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 邓宁
 * @date Created in 13:38 2018/11/12
 * : 用户业务接口
 */
public interface UserService {

    /**
     * 用户注册
     * @param userRegister 用户注册信息
     */
    void userRegister(User userRegister);

    /**
     * 用户登录
     * @param telephone
     * @param password
     * @return User
     */
    User userLogin(String telephone, String password);

    /**
     * 检查手机号是否注册过
     *
     * @param telephone
     */
    void userCheckTelephone(String telephone);

    /**
     * 发送手机验证码
     *
     * @param telephone
     * @throws ClientException
     */
    void userSendCode(String telephone) throws ClientException;

    /**
     * 图片验证码
     *
     * @param response
     * @param session
     * @throws IOException
     */
    void userImageCode(HttpServletResponse response, HttpSession session) throws IOException;

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 添加用户收货地址
     *
     * @param address
     * @return java.lang.Integer
     */
    Integer saveUserAddress(UserAddress address);

    /**
     * 更新用户收货地址
     *
     * @param address
     * @return java.lang.Integer
     */
    Integer updateUserAddress(UserAddress address);

    /**
     * 更新默认收货地址
     *
     * @param userId
     * @param addressId
     */
    void updateDefaultUserAddress(Integer userId, Integer addressId);

    /**
     * 删除收货地址
     *
     * @param userId
     * @param addressId
     * @return java.lang.Integer
     */
    Integer deleteUserAddressByAddressId(Integer userId, Integer addressId);

    /**
     * 查收用户所有收货地址
     *
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.UserAddress>
     */
    List<UserAddress> findAllUserAddress(Integer userId);

    /**
     * 查询搜索历史记录
     *
     * @param userId
     * @return java.util.List<java.lang.String>
     */
    List<String> findAllSearchHistory(Integer userId);

    /**
     * 用户清除搜索历史记录
     *
     * @param userId
     * @return java.lang.Integer
     */
    Integer deleteAllSearchHistory(Integer userId);

    /**
     * 收藏商户
     *
     * @param collect
     */
    void collectSeller(UserCollectSeller collect);

    /**
     * 根据用户ID获取收藏的商户
     *
     * @param userId
     * @return List<Seller>
     */
    List<Seller> getCollectSellerByUserId(Integer userId);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return User
     */
    User getUserDetail(Integer userId);

    /**
     * 用户上传头像
     *
     * @param file
     * @param userId
     */
    void saveUserHeadIcon(MultipartFile file, Integer userId);

    /**
     * 查询用户是否收藏了商户
     *
     * @param sellerId
     * @param userId
     * @return java.lang.String
     */
    String selectUserCollectSeller(Integer sellerId, Integer userId);
}

