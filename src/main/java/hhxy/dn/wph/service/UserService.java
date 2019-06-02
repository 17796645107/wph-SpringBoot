package hhxy.dn.wph.service;

import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.Seller;
import hhxy.dn.wph.entity.User;
import hhxy.dn.wph.entity.UserAddress;
import hhxy.dn.wph.entity.UserCollectSeller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
 * @Author: 邓宁
 * @Date: Created in 13:38 2018/11/12
 */

//用户业务接口
public interface UserService {

    //用户注册
    void userRegister(String telephone,String password,String telephoneCode);

    //用户登录
    User userLogin(String telephone, String password);

    //检查手机号是否注册过
    String userCheckTelephone(String telephone);

    //发送手机验证码
    String userSendCode(String telephone,HttpSession session) throws ClientException;

    //图片验证码
    void userImageCode(HttpServletResponse response, HttpSession session) throws IOException;

    //添加用户信息
    int saveUser(User user);

    //更新用户信息
    int updateUser(User user,String token);

    //添加用户收货地址
    int saveUserAddress(UserAddress address);

    //更新用户收货地址
    int updateUserAddress(UserAddress address);

    //更新默认收货地址
    int updateDefaultUserAddress(Integer address_id);

    //删除收货地址
    int deleteUserAddressByAddressID(Integer userId,Integer address_id);

    //查收用户所有收货地址
    List<UserAddress> findAllUserAddress(Integer user_id);

    //用户搜索商品关键词
    int saveSearchHistory(String search_title,Integer user_id);

    //查询搜索历史记录
    List<String> findAllSearchHistory(Integer user_id);

    //用户清除搜索历史记录
    int deleteAllSearchHistory(Integer user_id);

    void collectSeller(UserCollectSeller collect);

    List<Seller> getCollectSellerByUserId(Integer userId);

    User getUserDetail(String telephone);

    void saveUserHeadIcon(MultipartFile file,String token,Integer userId);

    String selectUserCollectSeller(Integer sellerId, Integer userId);
}
