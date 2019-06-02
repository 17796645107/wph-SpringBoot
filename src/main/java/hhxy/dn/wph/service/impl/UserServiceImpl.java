package hhxy.dn.wph.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.exception.UserException;
import hhxy.dn.wph.mapper.UserMapper;
import hhxy.dn.wph.service.UserService;
import hhxy.dn.wph.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 邓宁
 * @Date: Created in 13:38 2018/11/12
 */
//用户业务接口实现类
@Service
public class UserServiceImpl implements UserService {

    @Value("${UPLOAD_URL}")
    private String UPLOAD_URL;

    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    //用户注册
    @Override
    @Transactional
    public void userRegister(String telephone,String password,String telephoneCode) {
        //从Redis中获取手机验证码
        String code = (String) redisUtil.get(telephone);
        //手机验证码错误||验证码为空
        if(!StringUtils.equals(telephoneCode,code) || StringUtils.isBlank(telephoneCode) || StringUtils.isBlank(code)){
            throw new UserException(UserExceptionEnum.CODE_ERROR);
        }
        User user = new User();
        user.setTelephone(telephone);
        user.setCreated(DateUtil.getDate());
        //注册用户基本信息
        int result = userMapper.userRegister(user);
        //获取自增主键ID
        Integer user_id = user.getUser_id();
        //创建用户密码对象
        UserPassword userPwd = new UserPassword();
        userPwd.setUser_id(user_id);
        //MD5加密密码
        userPwd.setPassword(MD5Util.getMD5(password));
        //注册用户密码
        int resultUserPwd = userMapper.userPasswordRegister(userPwd);
        //注册失败,抛出异常
        if (result != 1 || resultUserPwd !=1){
            //异常时销毁Redis中短信验证码
            redisUtil.del(telephone);
            throw new UserException(UserExceptionEnum.REGISTER_ERROR);
        }
    }

    //用户登录
    @Override
    public User userLogin(String telephone,String password) {
        //查询用户基本信息
        User user = userMapper.getUser(telephone);
        if (user == null){
            throw new UserException(UserExceptionEnum.LOGIN_ERROR);
        }
        //查询用户加密之后的密码
        String userPwd =userMapper.getUserPassword(user.getUser_id());
        if (!MD5Util.getMD5(password).equals(userPwd)){
            throw new UserException(UserExceptionEnum.LOGIN_ERROR);
        }
        return user;
    }

    //查询手机号是否注册
    @Override
    public String userCheckTelephone(String telephone) {

        return userMapper.userCheckTelephone(telephone);
    }

    //发送短信验证码
    @Override
    public String userSendCode(String telephone,HttpSession session) throws ClientException {
        //生成随机验证码
        String code=SendCodeUtil.getRandomCode();
        //判断Redis中是否已经存在了此手机号码的验证码,
        if(redisUtil.get(telephone) != null){
            //如果存在则删除
            redisUtil.del(telephone);
        }
        //发送验证码
        SendSmsResponse response=SendCodeUtil.sendSms(telephone,code);
        //验证返回信息
        if(response.getCode() != null && "OK".equals(response.getCode())){
            //放入Redis,(手机号码,验证码),time(s):超时时间:5分钟
            redisUtil.set(telephone,code,60*5);
            return response.getCode();
        }
        else{
            throw new UserException(UserExceptionEnum.SENDCODE_ERROR);
        }
    }

    //生成图片验证码
    @Override
    public void userImageCode(HttpServletResponse response, HttpSession session)throws IOException  {
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //随机字符串
        String randomString= ValidateCodeUtil.getRandomString();
        //放入的session中
        session.setAttribute("VALIDATE_CODE", randomString);
        //随机颜色
        Color color = ValidateCodeUtil.getRandomColor();
        //反色
        Color reverseColor = ValidateCodeUtil.getReverseColor(color);
        //创建一个彩色图片
        BufferedImage img = new BufferedImage(ValidateCodeUtil.width, ValidateCodeUtil.height, BufferedImage.TYPE_INT_BGR);
        //获取绘图对象
        Graphics2D g = img.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.setColor(color);
        g.fillRect(0, 0, ValidateCodeUtil.width, ValidateCodeUtil.height);
        g.setColor(reverseColor);
        g.drawString(randomString, 18, 20);
        g.dispose();
        ImageIO.write(img, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    //添加用户信息
    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    //更新用户信息
    @Override
    public int updateUser(User user,String token) {
        if (!redisUtil.hasKey(token)){
            throw new UserException(UserExceptionEnum.Unqualified);
        }
        int result = userMapper.updateUser(user);
        if (result != 1){
            throw new UserException(UserExceptionEnum.updateUserError);
        }
        //删除缓存
        redisUtil.del(token);
        return result;
    }

    //添加用户收货地址
    @Override
    public int saveUserAddress(UserAddress address) {
        //设置用户地址创建时间
        address.setCreated(DateUtil.getDate());
        int result = userMapper.saveUserAddress(address);
        if (result != 1){
            throw new UserException(UserExceptionEnum.saveUserAddressError);
        }
        redisUtil.del("UserAddress:"+address.getUser_id());
        return result;
    }

    //更新用户收货地址
    @Override
    public int updateUserAddress(UserAddress address) {
        //设置用户更新时间
        address.setUpdated(DateUtil.getDate());
        return userMapper.updateUserAddress(address);
    }

    //更新默认收货地址
    @Override
    @Transactional//异常回滚
    public int updateDefaultUserAddress(Integer address_id) {
        //先查询用户ID
        Integer user_id=userMapper.findUserIdByAddressId(address_id);
        //把此用户的所有收货地址都重置为非默认收货地址
        int result1=userMapper.updateAllUserAddress(user_id);
        //更新默认收货地址
        int result2=userMapper.updateDefaultUserAddress(address_id);
        if(result1 > 0 && result2 >0){
            return 1;
        }
        else{
            return 0;
        }
    }

    //删除收货地址
    @Override
    public int deleteUserAddressByAddressID(Integer userId,Integer address_id) {
        int result = userMapper.deleteUserAddressByAddressID(address_id);
        if (result != 1){
            throw new UserException(UserExceptionEnum.deleteAddressError);
        }
        redisUtil.del("UserAddress:"+userId);
        return result;
    }

    //查询用户所有收货地址
    @Override
    public List<UserAddress> findAllUserAddress(Integer user_id) {
        //读取缓存
        String userAddress = (String) redisUtil.get("UserAddress:"+user_id);
        if (userAddress != null){
            return JsonUtil.jsonToList(userAddress,UserAddress.class);
        }
        //缓存没有,则查询数据库
        List<UserAddress> addressList = userMapper.findAllUserAddress(user_id);
        redisUtil.set("UserAddress:"+user_id,JsonUtil.objectToJson(addressList));
        return addressList;
    }

    //用户搜索商品关键词
    @Override
    @Transactional
    public int saveSearchHistory(String search_title,Integer user_id) {
        int result = userMapper.saveSearchHistory(search_title,user_id);
        if (result < 1){
            throw new UserException(UserExceptionEnum.CODE_ERROR);
        }

        return result;
    }

    //查询搜索历史记录
    @Override
    public List<String> findAllSearchHistory(Integer user_id) {
        String searchHistorys = (String) redisUtil.get("SearchHistory:"+user_id);
        if (searchHistorys != null){
            return JsonUtil.jsonToList(searchHistorys,String.class);
        }
        List<String> searchHostoryList = userMapper.findAllSearchHistory(user_id);
        redisUtil.set("SearchHistory:"+user_id,JsonUtil.objectToJson(searchHostoryList));
        return searchHostoryList;
    }

    //用户清除搜索历史记录
    @Override
    public int deleteAllSearchHistory(Integer user_id) {
        redisUtil.del("SearchHistory:"+user_id);
        return userMapper.deleteAllSearchHistory(user_id);
    }

    //用户关注商铺
    @Override
    public void collectSeller(UserCollectSeller collect) {
        int result = userMapper.collectSeller(collect);
        if (result != 1){
            throw new UserException(UserExceptionEnum.collectSellerError);
        }
        Integer id = collect.getUser_id();
        //清除缓存
        redisUtil.del("CollectSeller:"+id);

    }

    //用户查询关注的商铺
    @Override
    public List<Seller> getCollectSellerByUserId(Integer userId) {
        String sellers = (String) redisUtil.get("CollectSeller:"+userId);
        if (sellers != null){
            return JsonUtil.jsonToList(sellers,Seller.class);
        }
        List<Seller> sellerList = userMapper.getCollectSellerByUserId(userId);
        redisUtil.set("CollectSeller:"+userId, JsonUtil.objectToJson(sellerList));
        return sellerList;
    }

    @Override
    public User getUserDetail(String telephone) {
        User user = userMapper.getUser(telephone);
        return user;
    }

    @Override
    public void saveUserHeadIcon(MultipartFile file,String token,Integer userId) {
        //上传图片
        UploadFileUtil.uploadFile(file,UPLOAD_URL);
        //更新用户头像url
        int result = userMapper.updateUserHeadIcon(file.getOriginalFilename(),userId);
        if (result != 1){
            throw new UserException(UserExceptionEnum.UpdateUserHeadIconError);
        }
        //删除缓存
        redisUtil.del(token);
    }

    @Override
    public String selectUserCollectSeller(Integer sellerId, Integer userId) {
        UserCollectSeller collect = userMapper.selectUserCollectSeller(sellerId,userId);
        if (collect != null) {
            return "true";
        }
        else {
            return "false";
        }
    }
}
