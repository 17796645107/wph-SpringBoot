package hhxy.dn.wph.controller;

import com.aliyuncs.exceptions.ClientException;
import com.sun.org.apache.xpath.internal.operations.Mult;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.exception.UserException;
import hhxy.dn.wph.service.UserService;
import hhxy.dn.wph.util.CookieUtil;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import hhxy.dn.wph.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: 邓宁
 * @Date: Created in 13:35 2018/11/12
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户注册
     * @param userRegister
     * @param result
     * @return hhxy.dn.wph.entity.Result
     */
    @PostMapping(value = "/register")
    public Result userRegister(@Valid @RequestBody UserRegister userRegister, BindingResult result){
        //参数验证
        this.valid(result);
        userService.userRegister(userRegister);
        return ResultUtil.success();
    }

    /**
     * SpringMVC参数验证
     * @param result
     * @return void
     */
    public void valid(BindingResult result){
        if (result.hasFieldErrors()){
            //获取错误信息列表
            List<FieldError> errorList = result.getFieldErrors();
            //通过断言抛出参数不合法的异常
            errorList.stream().forEach(item -> Assert.isTrue(false,item.getDefaultMessage()));
        }
    }

    /**
     * 用户登录
     * @param userLogin UserLogin实体类
     * @param result
     * @param request
     * @param response
     * @return hhxy.dn.wph.entity.Result
     */
    @PostMapping("/login")
    public Result userLogin(@Valid @RequestBody UserLogin userLogin,BindingResult result,
            HttpServletRequest request,HttpServletResponse response){
        //判断Token是否存在--->自动登录
        String userCache = this.cookieHasToken(request);
        if (userCache != null){
            User user = JsonUtil.jsonToPojo(userCache,User.class);
            return ResultUtil.success(user);
        }
        //校验请求参数
        this.valid(result);
        //验证账号密码
        User user = userService.userLogin(userLogin.getTelephone(),userLogin.getPwd());
        //生成Token,存入Cookie,有效期:7天
        CookieUtil.setCookie(request,response,"token",this.setUserToRedis(user),60*60*24*7);
        return ResultUtil.success(user);
    }

    /**
     * Cookie中是否存在Token
     * @param request
     * @return java.lang.String
     */
    String cookieHasToken(HttpServletRequest request){
        //从Cookie中获取Token
        String tokenCookie = CookieUtil.getCookieValue(request,"token");
        if (StringUtils.isNotBlank(tokenCookie)){
            //判断Rdis中是否存此Token,
            if (redisUtil.hasKey(tokenCookie)){
                //根据Token返回Redie中用户信息
                return redisUtil.get(tokenCookie).toString();
            }
        }
        return null;
    }

    /**
     * 将用户信息存储到Redis,并生成Token
     * @param user
     * @return java.lang.String
     */
    String setUserToRedis(User user){
        //生成Token令牌
        String token = UUID.randomUUID().toString();
        //将用户信息转成JSON格式写入Redis,超时时间:7天
        redisUtil.set(token, JsonUtil.objectToJson(user),60*60*24*7);
        return token;
    }

    /**
     * 注销
     * @param request
     * @return hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/logout")
    public Result userLogout(HttpServletRequest request){
        String token = CookieUtil.getCookieValue(request,"token");
        //如果用户没有携带Token令牌
        if (!redisUtil.hasKey(token)){
            throw new UserException(UserExceptionEnum.Unqualified);
        }
        redisUtil.del(token);
        return ResultUtil.success();
    }

    /**
     * 发送短信验证码
     * @param user
     * @return hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/sendCode")
    public Result userSendCode(@RequestBody User user) throws ClientException {
        this.userService.userSendCode(user.getTelephone());
        return ResultUtil.success();
    }

    /**
     * 查询手机号是否注册
     * @param user
     * @return hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/checkTelephone")
    public Result userCheckTelephone(@RequestBody User user){
        userService.userCheckTelephone(user.getTelephone());
        return ResultUtil.success("手机号可以使用");
    }

    /**
     * 图片验证码
     * @param response
     * @param session
     * @return void
     */
    @RequestMapping(value="/getImageCode")
    public void userImageCode(HttpServletResponse response, HttpSession session) throws IOException {
        userService.userImageCode(response,session);
    }

    /**
     * @Description: 获取用户信息
     * @param userId
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getUserById/{userId}")
    public Result getUserDetail(@PathVariable Integer userId){
        User user = userService.getUserDetail(userId);
        return ResultUtil.success(user);
    }

    /**
     * @Description: 更新用户信息
     * @param: [user]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user,HttpServletRequest request){
        String token = CookieUtil.getCookieValue(request,"token");
        userService.updateUser(user,token);
        return ResultUtil.success();
    }

    /**
     * @Description: 用户上传头像
     * @param: [file]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/uploadUserHeadImage")
    public Result uploadUserHeadImage(@RequestParam("file") MultipartFile file,
                                      @RequestParam("userId") Integer userId,HttpServletRequest request){
        String token = CookieUtil.getCookieValue(request,"token");
        userService.saveUserHeadIcon(file,token,userId);
        return ResultUtil.success();
    }

    /**
     * @Description: 添加用户收货地址
     * @param: [address]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/saveUserAddress")
    public Result saveUserAddress(@RequestBody UserAddress address){
        userService.saveUserAddress(address);
        return ResultUtil.success();
    }

    /**
     * @Description: 更新用户收货地址
     * @param: [address]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/updateUserAddress")
    public Result updateUserAddress(@RequestBody UserAddress address){
        userService.updateUserAddress(address);
        return ResultUtil.success();
    }

    /**
     * @Description: 更新用户默认收货地址
     * @param: [address_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/updateDefaultUserAddress/{userId}/{address_id}")
    public Result updateDefaultUserAddress(@PathVariable Integer userId,@PathVariable Integer addressId){
        userService.updateDefaultUserAddress(userId,addressId);
        return ResultUtil.success();
    }

    /**
     * @Description: 删除用户收货地址
     * @param: [address_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/deleteUserAddressByAddressID/{userId}/{address_id}")
    public Result deleteUserAddressByAddressId(@PathVariable Integer userId,@PathVariable Integer addressId){
        userService.deleteUserAddressByAddressId(userId,addressId);
        return ResultUtil.success();
    }

    /**
     * @Description: 查询用户收货地址
     * @param: [user_no]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findAllUserAddress/{userId}")
    public Result findAllUserAddress(@PathVariable Integer userId){
        List<UserAddress>addressList = userService.findAllUserAddress(userId);
        return ResultUtil.success(addressList);
    }

    /**
     * @Description: 查询搜索历史记录
     * @param: [user_no]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findAllSearchHistory/{userId}")
    public Result findAllSearchHistory(@PathVariable Integer userId){
        List<String>searchHistory = userService.findAllSearchHistory(userId);
        return ResultUtil.success(searchHistory);
    }

    /**
     * @Description: 用户清除搜索历史记录
     * @param: [user_no]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/deleteSearchHistoryByNo/{userId}")
    public Result deleteSearchHistoryByNo(@PathVariable Integer userId){
        userService.deleteAllSearchHistory(userId);
        return ResultUtil.success();
    }

    /**
     * @Description: 用户关注商铺
     * @param: [collect]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/collectSeller")
    public Result collectSeller(@RequestBody UserCollectSeller collect){
        userService.collectSeller(collect);
        return ResultUtil.success();
    }

    /**
     * @Description: 获取用户关注的商铺
     * @param: [userId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findCollectSellerById/{userId}")
    public Result getCollectSellerByUserId(@PathVariable Integer userId){
        List<Seller> sellerList = userService.getCollectSellerByUserId(userId);
        return ResultUtil.success(sellerList);
    }

    /**
     * @Description: 查询用户是否收藏了该商品
     * @param: [sellerId, userId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findUserCollectSeller/{sellerId}/{userId}")
    public Result selectUserCollectSeller(@PathVariable Integer sellerId,@PathVariable Integer userId){
        String result = userService.selectUserCollectSeller(sellerId,userId);
        return ResultUtil.success(result);
    }

}
