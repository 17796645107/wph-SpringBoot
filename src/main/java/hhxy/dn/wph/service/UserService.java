package hhxy.dn.wph.service;

import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public interface UserService extends UserDetailsService {

    //用户注册
    void userRegister(UserRegister user);

    //用户登录
    User userLogin(String telephone, String password);

    //检查手机号是否注册过
    void userCheckTelephone(String telephone);

    //发送手机验证码
    void userSendCode(String telephone) throws ClientException;

    //图片验证码
    void userImageCode(HttpServletResponse response, HttpSession session) throws IOException;

    //更新用户信息
    void updateUser(User user,String token);

    //添加用户收货地址
    int saveUserAddress(UserAddress address);

    //更新用户收货地址
    int updateUserAddress(UserAddress address);

    //更新默认收货地址
    int updateDefaultUserAddress(Integer user_no,Integer address_id);

    //删除收货地址
    int deleteUserAddressByAddressID(Integer userId,Integer address_id);

    //查收用户所有收货地址
    List<UserAddress> findAllUserAddress(Integer user_no);

    //查询搜索历史记录
    List<String> findAllSearchHistory(Integer user_no);

    //用户清除搜索历史记录
    int deleteAllSearchHistory(Integer user_no);

    //收藏商户
    void collectSeller(UserCollectSeller collect);

    //根据用户ID获取收藏的商户
    List<Seller> getCollectSellerByUserId(Integer user_no);

    //获取用户信息
    User getUserDetail(Integer user_no);

    //用户上传头像
    void saveUserHeadIcon(MultipartFile file,String token,Integer user_no);

    //查询用户是否收藏了商户
    String selectUserCollectSeller(Integer seller_no, Integer user_no);

    //根据手机号码加载用户
    @Override
    UserDetails loadUserByUsername(String telephone) throws UsernameNotFoundException;
}
