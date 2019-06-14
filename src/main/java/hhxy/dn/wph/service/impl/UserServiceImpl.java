package hhxy.dn.wph.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.UserException;
import hhxy.dn.wph.mapper.RoleMapper;
import hhxy.dn.wph.mapper.UserMapper;
import hhxy.dn.wph.service.UserService;
import hhxy.dn.wph.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    //用户上传头像存储地址
    @Value("${UPLOAD_URL}")
    private String UPLOAD_URL;

    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisUtil redisUtil;

    //用户注册
    @Override
    @Transactional
    public void userRegister(UserRegister userRegister) {
        //校验手机验证码
        validateTelephoneCode(userRegister.getTelephone(),userRegister.getTelephoneCode());

        User user = new User();
        user.setTelephone(userRegister.getTelephone());
        //注册用户基本信息
        Integer result = userMapper.saveUser(user);
        if (result < 1){
            //异常时销毁Redis中短信验证码
            redisUtil.del(userRegister.getTelephone());
            throw new UserException(UserExceptionEnum.REGISTER_ERROR);
        }
        //获取自增主键ID
        Integer user_no = user.getUser_no();
        //创建用户密码对象
        UserPassword userPwd = new UserPassword();
        userPwd.setUser_no(user_no);
        userPwd.setPassword(userRegister.getPassword());
        //注册用户密码
        int resultUserPwd = userMapper.saveUserPassword(userPwd);
        //注册失败,抛出异常
        if (resultUserPwd < 1){
            //异常时销毁Redis中短信验证码
            redisUtil.del(userRegister.getTelephone());
            throw new UserException(UserExceptionEnum.REGISTER_ERROR);
        }
    }

    //校验手机验证码
    public void validateTelephoneCode(String telephone,String code){
        //缓存中不存在手机验证码
        if (!redisUtil.hasKey(telephone)){
            throw new UserException(UserExceptionEnum.CODE_ERROR);
        }
        //从Redis中根据手机号获取手机验证码
        String codeCache = (String) redisUtil.get(telephone);
        //手机验证码错误
        if(!StringUtils.equals(codeCache,code)){
            throw new UserException(UserExceptionEnum.CODE_ERROR);
        }
    }

    //用户登录
    @Override
    public User userLogin(String telephone,String password) {
        //查询用户基本信息
        User user = userMapper.findUserByTelephone(telephone);
        if (user == null){
            throw new UserException(UserExceptionEnum.LOGIN_ERROR);
        }
        //查询用户加密之后的密码
        String userPwd = userMapper.findUserPasswordByNo(user.getUser_no());
        if (!MD5Util.getMD5(password).equals(userPwd)){
            throw new UserException(UserExceptionEnum.LOGIN_ERROR);
        }
        return user;
    }

    //查询手机号是否注册
    @Override
    public void userCheckTelephone(String telephone) {
        String result = userMapper.findTelephone(telephone);
        if (result == null){
            throw new UserException(UserExceptionEnum.telehpne_error);
        }
    }

    //发送短信验证码
    @Override
    public void userSendCode(String telephone) throws ClientException {
        //生成随机验证码
        String code = SendCodeUtil.getRandomCode();
        //判断Redis中是否已经存在了此手机号码的验证码,
        if(redisUtil.get(telephone) != null){
            //如果存在则删除
            redisUtil.del(telephone);
        }
        //发送验证码
        SendSmsResponse response=SendCodeUtil.sendSms(telephone,code);
        //验证返回信息
        if("OK".equals(response.getCode())){
            //放入Redis,(手机号码,验证码),time(s):超时时间:5分钟
            redisUtil.set(telephone,code,60*5);
        }
        else{
            LOGGER.info("手机验证码发送错误 = {}",response.getMessage());
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

    //更新用户信息
    @Override
    public void updateUser(User user,String token) {
        int result = userMapper.updateUser(user);
        if (result != 1){
            throw new UserException(UserExceptionEnum.updateUserError);
        }else{
            //删除缓存
            redisUtil.del(token);
        }
    }

    //添加用户收货地址
    @Override
    public int saveUserAddress(UserAddress address) {
        int result = userMapper.saveUserAddress(address);
        if (result != 1){
            throw new UserException(UserExceptionEnum.saveUserAddressError);
        }
        //清除缓存
        redisUtil.del("UserAddress:"+address.getUser_no());
        return result;
    }

    //更新用户收货地址
    @Override
    public int updateUserAddress(UserAddress address) {
        int result = userMapper.updateUserAddressById(address);
        if (result != 1){
            throw new UserException(UserExceptionEnum.saveUserAddressError);
        }
        //清除缓存
        redisUtil.del("UserAddress:"+address.getUser_no());
        return result;
    }

    //更新默认收货地址
    @Override
    //异常回滚
    @Transactional
    public int updateDefaultUserAddress(Integer user_no,Integer address_id) {
        //把此用户的所有收货地址都重置为非默认收货地址
        Integer result1 = userMapper.updateAllUserAddressByNo(user_no);
        if (result1 == null){
            throw new UserException(UserExceptionEnum.updateAddressError);
        }
        //更新默认收货地址
        Integer result2 = userMapper.updateDefaultUserAddressByID(address_id);
        if (result2 == null){
            throw new UserException(UserExceptionEnum.updateAddressError);
        }
        redisUtil.del("UserAddress:"+ user_no);
        return 0;
    }

    //删除收货地址
    @Override
    public int deleteUserAddressByAddressID(Integer userId,Integer address_id) {
        int result = userMapper.deleteUserAddressByID(address_id);
        if (result != 1){
            throw new UserException(UserExceptionEnum.deleteAddressError);
        }
        redisUtil.del("UserAddress:"+ userId);
        return result;
    }

    //查询用户所有收货地址
    @Override
    public List<UserAddress> findAllUserAddress(Integer user_id) {
        if (redisUtil.hasKey("UserAddress:"+ user_id)){
            //读取缓存
            String userAddress = (String) redisUtil.get("UserAddress:"+ user_id);
            return JsonUtil.jsonToList(userAddress,UserAddress.class);
        }
        //缓存没有,则查询数据库
        List<UserAddress> addressList = userMapper.findAddressListByUserNo(user_id);
        if (addressList.size() == 0){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set("UserAddress:"+user_id,JsonUtil.objectToJson(addressList));
        return addressList;
    }

    //查询用户搜索历史记录
    @Override
    public List<String> findAllSearchHistory(Integer user_id) {
        if (redisUtil.hasKey("SearchHistory:"+ user_id)){
            String searchHistorys = (String) redisUtil.get("SearchHistory:"+user_id);
            return JsonUtil.jsonToList(searchHistorys,String.class);
        }
        List<String> searchHostoryList = userMapper.findAllSearchHistory(user_id);
        if (searchHostoryList.size() == 0){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set("SearchHistory:"+ user_id,JsonUtil.objectToJson(searchHostoryList));
        return searchHostoryList;
    }

    //用户清除搜索历史记录
    @Override
    public int deleteAllSearchHistory(Integer user_id) {
        Integer result = userMapper.deleteAllSearchHistory(user_id);
        if (result == null){
            throw new UserException(UserExceptionEnum.deleteAllSearchHistoryError);
        }
        redisUtil.del("SearchHistory:"+user_id);
        return result;
    }

    //用户关注商铺
    @Override
    public void collectSeller(UserCollectSeller collect) {
        int result = userMapper.saveCollectSeller(collect);
        if (result != 1){
            throw new UserException(UserExceptionEnum.collectSellerError);
        }
        //清除缓存
        redisUtil.del("CollectSeller:"+ collect.getUser_no());
    }

    //获取用户查询关注的商铺
    @Override
    public List<Seller> getCollectSellerByUserId(Integer userId) {
        if (redisUtil.hasKey("CollectSeller:"+userId)){
            String sellers = (String) redisUtil.get("CollectSeller:"+userId);
            return JsonUtil.jsonToList(sellers,Seller.class);
        }
        List<Seller> sellerList = userMapper.findCollectSellerById(userId);
        if (sellerList.size() == 0){
            throw new GeneralException(GeneralExceptionEnum.notFound);
        }
        redisUtil.set("CollectSeller:"+userId, JsonUtil.objectToJson(sellerList));
        return sellerList;
    }

    //根据用户编号获取用户信息
    @Override
    public User getUserDetail(Integer user_no) {
        User user = userMapper.findUserByNo(user_no);
        return user;
    }

    //上传用户头像
    @Override
    public void saveUserHeadIcon(MultipartFile file,String token,Integer userId) {
        //上传图片
        UploadFileUtil.uploadFile(file,UPLOAD_URL);
        //更新用户头像url
        int result = userMapper.updateUserHeadIcon(file.getOriginalFilename(),userId);
        if (result != 1){
            throw new UserException(UserExceptionEnum.UpdateUserHeadIconError);
        }else{
            //删除缓存
            redisUtil.del(token);
        }
    }

    //查询用户是否收藏了该商户
    @Override
    public String selectUserCollectSeller(Integer sellerId, Integer userId) {
        UserCollectSeller collect = userMapper.findUserCollectSellerById(sellerId,userId);
        return collect == null ? "false" : "true";
    }

    //根据手机号码加载用户
    @Override
    public UserDetails loadUserByUsername(String telephone) throws UsernameNotFoundException {
        User user = userMapper.findUserByTelephone(telephone);
        if (user == null){
            throw new UsernameNotFoundException("没有该用户");
        }
        //获取人员的权限
        List<Role> roleList = roleMapper.findRolesByPeopleId(user.getUser_id());
        UserLogin userLogin = new UserLogin();
        userLogin.setTelephone(telephone);
        userLogin.setRoles(roleList);
        return userLogin;
    }
}
