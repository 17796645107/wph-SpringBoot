package hhxy.dn.wph.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.GeneralExceptionEnum;
import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.exception.GeneralException;
import hhxy.dn.wph.exception.UserException;
import hhxy.dn.wph.mapper.UserMapper;
import hhxy.dn.wph.service.UserService;
import hhxy.dn.wph.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 邓宁
 * @date Created in 13:38 2018/11/12
 * 用户信息接口实现类
 */
@Service
public class
UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户注册
     * @param userRegister 用户注册信息
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void userRegister(User userRegister) {
        //校验手机验证码
        validateTelephoneCode(userRegister.getTelephone(),userRegister.getTelephoneCode());
        //创建用户对象
        User user = new User();
        user.setUserNo(IDUtil.createPeopleID("U"));
        user.setTelephone(userRegister.getTelephone());
        user.setCreated(DateUtil.getDate());
        //注册用户基本信息
        Integer result = userMapper.saveUser(user);
        if (result < 1){
            //异常时销毁Redis中短信验证码
            redisUtil.del(userRegister.getTelephone());
            throw new UserException(UserExceptionEnum.REGISTER_ERROR);
        }
        //注册用户密码
        int resultUserPwd = userMapper.saveUserPassword(user);
        if (resultUserPwd < 1){
            //异常时销毁Redis中短信验证码
            redisUtil.del(userRegister.getTelephone());
            throw new UserException(UserExceptionEnum.REGISTER_ERROR);
        }
    }

    /**
     * 校验手机验证码
     * @param telephone 手机号
     * @param code 手机验证码
     */
    private void validateTelephoneCode(String telephone, String code){
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
        //程序执行到这里说明验证码正确,将手机验证码从缓存中删除
        redisUtil.del(telephone);
    }

    /**
     * 用户登录
     * @param telephone 手机号
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public User userLogin(String telephone,String password) {
        //查询用户基本信息
        User user = userMapper.getUserByTelephoneAndPassword(telephone,MD5Util.getMD5(password));
        if (user == null){
            throw new UserException(UserExceptionEnum.LOGIN_ERROR);
        }
        return user;
    }

    /**
     * 查询手机号是否注册
     * @param telephone 手机号
     */
    @Override
    public void userCheckTelephone(String telephone) {
        String result = userMapper.getTelephone(telephone);
        if (result != null){
            throw new UserException(UserExceptionEnum.TELEPHONE_ERROR);
        }
    }

    /**
     * 发送短信验证码
     * @param telephone 手机号
     */
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
        String success = "OK";
        if(success.equals(response.getCode())){
            //放入Redis,(手机号码,验证码),time(s):超时时间:5分钟
            redisUtil.set(telephone,code,60*5);
        }
        else{
            LOGGER.info("手机验证码发送错误 = {}",response.getMessage());
            throw new UserException(UserExceptionEnum.SENDCODE_ERROR);
        }
    }

    /**
     * 生成图片验证码
     * @param response Req
     * @param session seession
     */
    @Override
    public void userImageCode(HttpServletResponse response, HttpSession session)throws IOException  {
        ImageCodeUtil.getImageCode(response,session);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        int result = userMapper.updateUser(user);
        if (result != 1){
            throw new GeneralException(GeneralExceptionEnum.UPDATE_DATABASE_ERROR);
        }
    }

    /**
     * 添加用户收货地址
     * @param address
     * @return int
     */
    @Override
    public Integer saveUserAddress(UserAddress address) {
        int result = userMapper.saveUserAddress(address);
        if (result != 1){
            throw new UserException(UserExceptionEnum.SAVE_USER_ADDRESS_ERROR);
        }else{
            //清除缓存
            redisUtil.del("UserAddress:" + address.getUserId());
        }
        return result;
    }

    /**
     * 更新用户收货地址
     * @param address
     * @return int
     */
    @Override
    public Integer updateUserAddress(UserAddress address) {
        int result = userMapper.updateUserAddressById(address);
        if (result != 1){
            throw new UserException(UserExceptionEnum.SAVE_USER_ADDRESS_ERROR);
        }else{
            //清除缓存
            redisUtil.del("UserAddress:" + address.getUserId());
        }
        return result;
    }

    /**
     * 更新默认收货地址
     * @param userId
     * @param addressId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDefaultUserAddress(Integer userId, Integer addressId) {
        //把此用户的所有收货地址都重置为非默认收货地址
        Integer result1 = userMapper.updateAllUserAddressById(userId);
        if (result1 == null){
            throw new UserException(UserExceptionEnum.UPDATE_ADDRESS_ERROR);
        }
        //更新默认收货地址
        Integer result2 = userMapper.updateDefaultUserAddressById(addressId);
        if (result2 == null){
            throw new UserException(UserExceptionEnum.UPDATE_ADDRESS_ERROR);
        }else{
            //清除缓存
            redisUtil.del("UserAddress:" + userId);
        }
    }

    /**
     * 删除收货地址
     * @param userId
     * @param addressId
     * @return int
     */
    @Override
    public Integer deleteUserAddressByAddressId(Integer userId,Integer addressId) {
        int isDefault = userMapper.getUserAddressIsDefaultById(addressId);
        if (isDefault == 1){
            throw new UserException(UserExceptionEnum.DELETE_DEFAULT_ADDRESS__Error);
        }
        Integer result = userMapper.deleteUserAddressById(addressId);
        if (result != 1){
            throw new UserException(UserExceptionEnum.DELETE_ADDRESS_ERROR);
        }
        redisUtil.del("UserAddress:"+ userId);
        return result;
    }

    /**
     * 查询用户所有收货地址
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.UserAddress>
     */
    @Override
    public List<UserAddress> findAllUserAddress(Integer userId) {
        //缓存标记
        String cacheFlag = "UserAddress:";
        if (redisUtil.hasKey(cacheFlag + userId)){
            //读取缓存
            String userAddress = (String) redisUtil.get(cacheFlag + userId);
            return JsonUtil.jsonToList(userAddress,UserAddress.class);
        }
        //缓存没有,则查询数据库
        List<UserAddress> addressList = userMapper.listAddressByUserId(userId);
        if (addressList.size() == 0){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        redisUtil.set(cacheFlag + userId,JsonUtil.objectToJson(addressList));
        return addressList;
    }

    /**
     * 查询用户搜索历史记录
     * @param userId
     * @return java.util.List<java.lang.String>
     */
    @Override
    public List<String> findAllSearchHistory(Integer userId) {
        String cacheFlag = "SearchHistory:";
        if (redisUtil.hasKey(cacheFlag + userId)){
            String searchHistorys = (String) redisUtil.get(cacheFlag + userId);
            return JsonUtil.jsonToList(searchHistorys,String.class);
        }
        List<String> searchHostoryList = userMapper.listSearchHistory(userId);
        if (searchHostoryList.size() == 0){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        redisUtil.set(cacheFlag + userId,JsonUtil.objectToJson(searchHostoryList));
        return searchHostoryList;
    }

    /**
     * 用户清除搜索历史记录
     * @param userId
     * @return int
     */
    @Override
    public Integer deleteAllSearchHistory(Integer userId) {
        Integer result = userMapper.deleteAllSearchHistory(userId);
        if (result == null){
            throw new UserException(UserExceptionEnum.DELETE_SEARCH_HISTORY_ERROR);
        }
        redisUtil.del("SearchHistory:"+ userId);
        return result;
    }

    /**
     * 用户关注商铺
     * @param collect
     */
    @Override
    public void collectSeller(UserCollectSeller collect) {
        int result = userMapper.saveCollectSeller(collect);
        if (result != 1){
            LOGGER.error(GeneralException.class.getName() + ":" + GeneralExceptionEnum.SAVE_DATABASE_ERROR.getMsg());
            LOGGER.error("at " + this.getClass().getName() + ".collectSeller(" +
                    this.getClass().getSimpleName() + ".java:377)");
            throw new GeneralException(GeneralExceptionEnum.SAVE_DATABASE_ERROR);
        }
        //清除缓存
        redisUtil.del("CollectSeller:"+ collect.getUserId());
    }

    /**
     * 获取用户查询关注的商铺
     * @param userId
     * @return java.util.List<hhxy.dn.wph.entity.Seller>
     */
    @Override
    public List<Seller> getCollectSellerByUserId(Integer userId) {
        //缓存标记
        String cacheFlag = "CollectSeller:";
        if (redisUtil.hasKey(cacheFlag + userId)){
            String sellers = (String) redisUtil.get(cacheFlag + userId);
            return JsonUtil.jsonToList(sellers,Seller.class);
        }
        List<Seller> sellerList = userMapper.listCollectSellerByUserId(userId);
        if (sellerList.size() == 0){
            throw new GeneralException(GeneralExceptionEnum.NOT_FOUND_ERROR);
        }
        redisUtil.set(cacheFlag + userId, JsonUtil.objectToJson(sellerList));
        return sellerList;
    }

    /**
     * 根据用户编号获取用户信息
     * @param userId 用户编号
     * @return User
     */
    @Override
    public User getUserDetail(Integer userId) {
        return userMapper.getUserById(userId);
    }

    /**
     * 上传用户头像
     * @param file 头像文件
     * @param userId 用户ID
     */
    @Override
    public void saveUserHeadIcon(MultipartFile file,Integer userId) {
        //上传图片
        UploadFileUtil.uploadUserIcon(file);
        //更新用户头像url
        int result = userMapper.updateUserHeadIcon(file.getOriginalFilename(),userId);
        if (result != 1){
            throw new GeneralException(GeneralExceptionEnum.UPDATE_DATABASE_ERROR);
        }
    }

    /**
     * 查询用户是否收藏了该商户
     * @param sellerId
     * @param userId
     * @return java.lang.String
     */
    @Override
    public String selectUserCollectSeller(Integer sellerId, Integer userId) {
        UserCollectSeller collect = userMapper.getUserCollectSeller(sellerId,userId);
        return collect == null ? "false" : "true";
    }

}
