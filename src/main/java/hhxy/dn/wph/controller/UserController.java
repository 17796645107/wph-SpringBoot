package hhxy.dn.wph.controller;

import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.enums.UserExceptionEnum;
import hhxy.dn.wph.exception.UserException;
import hhxy.dn.wph.service.UserService;
import hhxy.dn.wph.util.CookieUtil;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import hhxy.dn.wph.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.Assert.isTrue;

/**
 * @author 邓宁
 * @date Created in 13:35 2018/11/12
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userRegister 用户注册信息
     * @param bindingResult 验证结果
     * @return Result
     */
    @PostMapping(value = "/register")
    public Result userRegister(@Valid @RequestBody User userRegister, BindingResult bindingResult){
        //参数验证
        this.valid(bindingResult);
        userService.userRegister(userRegister);
        return ResultUtil.success();
    }

    /**
     * SpringMVC参数验证
     * @param bindingResult 验证结果
     */
    private void valid(BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            //获取错误信息列表
            List<FieldError> errorList = bindingResult.getFieldErrors();
            //通过断言抛出参数不合法的异常
            errorList.forEach(item -> isTrue(false,item.getDefaultMessage()));
        }
    }

    /**
     * 用户登录
     * @param user 用户账号密码
     * @return Result
     */
    @PostMapping("/login")
    public Result userLogin(@RequestBody User user){
        //验证账号密码
        User userMsg = userService.userLogin(user.getTelephone(),user.getPassword());
        return ResultUtil.success(userMsg);
    }

    /**
     * Cookie中是否存在Token
     * @param request
     * @return 根据Token返回Redis中用户信息
     */
    /*private String cookieHasToken(HttpServletRequest request){
        //从Cookie中获取Token
        String tokenCookie = CookieUtil.getCookieValue(request,"token");
        if (StringUtils.isNotBlank(tokenCookie)){
            //判断Redis中是否存此Token,
            if (redisUtil.hasKey(tokenCookie)){
                //根据Token返回Redis中用户信息
                return redisUtil.get(tokenCookie).toString();
            }
        }
        return null;
    }
*/
    /**
     * 将用户信息存储到Redis,并生成Token
     * @param user 用户信息
     * @return java.lang.String
     */
    /*private String setUserToRedis(User user){
        //生成Token令牌
        String token = UUID.randomUUID().toString();
        //将用户信息转成JSON格式写入Redis,超时时间:7天
        redisUtil.set(token, JsonUtil.objectToJson(user),60*60*24*7);
        return token;
    }*/

    /**
     * 注销
     * @return Result
     */
    @RequestMapping("/logout")
    public Result userLogout(){
        /*String token = CookieUtil.getCookieValue(request,"token");
        //如果用户没有携带Token令牌
        if (!redisUtil.hasKey(token)){
            throw new UserException(UserExceptionEnum.UNQUALIFIED_ERROR);
        }
        redisUtil.del(token);*/
        return ResultUtil.success();
    }

    /**
     * 发送短信验证码
     * @param user 手机号码
     * @return Result
     */
    @RequestMapping("/sendCode")
    public Result userSendCode(@RequestBody User user) throws ClientException {
        userService.userSendCode(user.getTelephone());
        return ResultUtil.success();
    }

    /**
     * 查询手机号是否注册
     * @param user 手机号码
     * @return Result
     */
    @RequestMapping("/checkTelephone")
    public Result userCheckTelephone(@RequestBody User user){
        userService.userCheckTelephone(user.getTelephone());
        return ResultUtil.success("手机号可以使用");
    }

    /**
     * 图片验证码
     * @param response req
     * @param session session
     */
    @RequestMapping(value="/getImageCode")
    public void userImageCode(HttpServletResponse response, HttpSession session) throws IOException {
        userService.userImageCode(response,session);
    }

    /**
     * 获取用户信息
     * @param userId 用户ID
     * @return Result
     */
    @RequestMapping("/getUserById/{userId}")
    public Result getUserDetail(@PathVariable Integer userId){
        User user = userService.getUserDetail(userId);
        return ResultUtil.success(user);
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return Result
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResultUtil.success();
    }

    /**
     * 用户上传头像
     * @param file 头像
     * @param userId 用户ID
     * @return Result
     */
    @PostMapping("/uploadUserHeadImage")
    public Result uploadUserHeadImage(@RequestParam("file") MultipartFile file,
                                      @RequestParam("userId") Integer userId){
        userService.saveUserHeadIcon(file,userId);
        return ResultUtil.success();
    }

    /**
     * 添加用户收货地址
     * @param address 收货地址
     * @return Result
     */
    @PostMapping("/saveUserAddress")
    public Result saveUserAddress(@RequestBody UserAddress address){
        userService.saveUserAddress(address);
        return ResultUtil.success();
    }

    /**
     * 更新用户收货地址
     * @param address 收货地址ID
     * @return Result
     */
    @PostMapping("/updateUserAddress")
    public Result updateUserAddress(@RequestBody UserAddress address){
        userService.updateUserAddress(address);
        return ResultUtil.success();
    }

    /**
     * 更新用户默认收货地址
     * @param addressId 收货地址ID
     * @return Result
     */
    @GetMapping("/updateDefaultUserAddress/{userId}/{addressId}")
    public Result updateDefaultUserAddress(@PathVariable Integer userId,@PathVariable Integer addressId){
        userService.updateDefaultUserAddress(userId,addressId);
        return ResultUtil.success();
    }

    /**
     * 删除用户收货地址
     * @param addressId 收货地址ID
     * @return Result
     */
    @GetMapping("/deleteUserAddressByAddressID/{userId}/{addressId}")
    public Result deleteUserAddressById(@PathVariable Integer userId,@PathVariable Integer addressId){
        userService.deleteUserAddressByAddressId(userId,addressId);
        return ResultUtil.success();
    }

    /**
     * 查询用户收货地址
     * @param userId 用户ID
     * @return Result
     */
    @GetMapping("/findAllUserAddress/{userId}")
    public Result findAllUserAddress(@PathVariable Integer userId){
        List<UserAddress>addressList = userService.findAllUserAddress(userId);
        return ResultUtil.success(addressList);
    }

    /**
     * 查询搜索历史记录
     * @param userId 用户ID
     * @return Result
     */
    @GetMapping("/findAllSearchHistory/{userId}")
    public Result findSearchHistory(@PathVariable Integer userId){
        List<String>searchHistory = userService.findAllSearchHistory(userId);
        return ResultUtil.success(searchHistory);
    }

    /**
     * 用户清除搜索历史记录
     * @param userId 用户ID
     * @return Result
     */
    @GetMapping("/deleteSearchHistoryByNo/{userId}")
    public Result deleteSearchHistoryById(@PathVariable Integer userId){
        userService.deleteAllSearchHistory(userId);
        return ResultUtil.success();
    }

    /**
     * 用户关注商铺
     * @param collect UserCollectSeller
     * @return Result
     */
    @RequestMapping("/collectSeller")
    public Result collectSeller(@RequestBody UserCollectSeller collect){
        userService.collectSeller(collect);
        return ResultUtil.success();
    }

    /**
     * 获取用户关注的商铺
     * @param userId 用户ID
     * @return Result
     */
    @GetMapping("/findCollectSellerById/{userId}")
    public Result getCollectSellerByUserId(@PathVariable Integer userId){
        List<Seller> sellerList = userService.getCollectSellerByUserId(userId);
        return ResultUtil.success(sellerList);
    }

    /**
     * 查询用户是否收藏了该商品
     * @param sellerId 商户ID
     * @param userId 用户ID
     * @return Result
     */
    @GetMapping("/findUserCollectSeller/{sellerId}/{userId}")
    public Result selectUserCollectSeller(@PathVariable Integer sellerId,@PathVariable Integer userId){
        String result = userService.selectUserCollectSeller(sellerId,userId);
        return ResultUtil.success(result);
    }

}
