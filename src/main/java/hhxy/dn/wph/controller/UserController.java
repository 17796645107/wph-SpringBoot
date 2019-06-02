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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@RequestMapping(value = "/user")//访问路径前缀
//用户控制器类
public class UserController {

    //注入UserService
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    //日志
    private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);

    /*
     * @Description:用户注册
     * @param: [telephone, password, telephoneCode]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping(value = "/register")
    public Result userRegister(
            @RequestParam("telephone") String telephone,
            @RequestParam("password") String password,
            @RequestParam("code") String telephoneCode){
        userService.userRegister(telephone,password,telephoneCode);
        return ResultUtil.success();
    }

    /*
     * @Description:用户登录
     * @param: [telephone, password]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/login")
    public Result userLogin(
            @RequestParam("telephone") String telephone,
            @RequestParam("password") String password,
            HttpServletRequest request,HttpServletResponse response){
        User user = userService.userLogin(telephone,password);
        //从Cookie中获取Token
        String tokenCookie = CookieUtil.getCookieValue(request,"token");
        //判断Rdis中是否存此Token,
        if (redisUtil.get(tokenCookie) != null){
            //删除原来的令牌
            redisUtil.del(tokenCookie);
        }
        //将用户信息存储到Redis,并生成Token
        String token = setUserToRedis(user);
        //将Token存入Cookie
        CookieUtil.setCookie(request,response,"token",token,60*60*24*7);
        return ResultUtil.success(user);
    }

    private String setUserToRedis(User user){
        //生成Token令牌
        String token = UUID.randomUUID().toString();
        //将用户信息转成JSON格式写入Redis,超时时间:7天
        redisUtil.set(token, JsonUtil.objectToJson(user),60*60*24*7);
        return token;
    }

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

    /*
     * @Description:发送短信验证码
     * @param: []
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/sendCode")
    public Result userSendCode(@RequestBody User user,HttpSession session) throws ClientException {
        String result = userService.userSendCode(user.getTelephone(),session);
        if("OK".equals(result)){
            return ResultUtil.success("验证码发送成功");
        }
        else{
            return ResultUtil.error(-1,"验证码发送失败！错误原因："+result);
        }
    }

    /*
     * @Description:AJAX查询手机号是否注册
     * @param: [user]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/checkTelephone")
    public Result userCheckTelephone(@RequestBody User user){
        String Telephone=userService.userCheckTelephone(user.getTelephone());
        if(Telephone != null){
            return ResultUtil.error(-1,"手机号已被注册");
        }
        return ResultUtil.success("手机号可以使用");
    }

    /*
     * @Description:图片验证码
     * @param: [response, session]
     * @return: void
     */
    @RequestMapping(value="/getImageCode")
    public void userImageCode(HttpServletResponse response, HttpSession session) throws IOException {
        userService.userImageCode(response,session);
    }

    /*
     * @Description:添加用户信息
     * @param: [user]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping(value = "/saveUser")
    public Result saveUser(@RequestBody User user){
        int result=userService.saveUser(user);
        if(result>0){
            return ResultUtil.success();
        }
        else{
            return ResultUtil.error(-1,"添加用户信息失败");
        }
    }

    /*
     * @Description:获取用户信息
     * @param: [u, request, response]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getUserDetail")
    @Transactional
    public Result getUserDetail(@RequestBody User u,HttpServletRequest request,HttpServletResponse response){
        User user = userService.getUserDetail(u.getTelephone());
        String token = setUserToRedis(user);
        CookieUtil.deleteCookie(request,response,"token");
        CookieUtil.setCookie(request,response,"token",token,60*60*24*7);
        return ResultUtil.success(user);
    }

    /*
     * @Description:更新用户信息
     * @param: [user]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user,HttpServletRequest request){
        String token = CookieUtil.getCookieValue(request,"token");
        userService.updateUser(user,token);
        return ResultUtil.success();
    }

    /*
     * @Description:用户上传头像
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

    /*
     * @Description:添加用户收货地址
     * @param: [address]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/saveUserAddress")
    public Result saveUserAddress(@RequestBody UserAddress address){
        userService.saveUserAddress(address);
        return ResultUtil.success();
    }

    /*
     * @Description:更新用户收货地址
     * @param: [address]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/updateUserAddress")
    public Result updateUserAddress(@RequestBody UserAddress address){
        int result = userService.updateUserAddress(address);
        if (result > 0){
            return ResultUtil.success();
        }
        else{
            return ResultUtil.error(-1,"更新收货地址失败");
        }
    }

    /*
     * @Description:更新用户默认收货地址
     * @param: [address_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/updateDefaultUserAddress")
    public Result updateDefaultUserAddress(Integer address_id){
        int result=userService.updateDefaultUserAddress(address_id);
        if (result>0){
            return ResultUtil.success();
        }
        else{
            return ResultUtil.error(-1,"设置默认收货地址失败");
        }
    }

    /*
     * @Description:删除用户收货地址
     * @param: [address_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/deleteUserAddressByAddressID/{userId}/{address_id}")
    public Result deleteUserAddressByAddressID(@PathVariable Integer userId,@PathVariable Integer address_id){
        userService.deleteUserAddressByAddressID(userId,address_id);
        return ResultUtil.success();

    }

    /*
     * @Description:查询用户收货地址
     * @param: [user_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findAllUserAddress/{user_id}")
    public Result findAllUserAddress(@PathVariable Integer user_id){
        List<UserAddress>addressList=userService.findAllUserAddress(user_id);
        return ResultUtil.success(addressList);
    }

    /*
     * @Description:用户输入关键词搜索商品
     * @param: [search]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/searchProduct")
    public Result searchProduct(@RequestBody Search search){
        int result=userService.saveSearchHistory(search.getSearch_title(),search.getUser_id());
        if (result>0){
            return ResultUtil.success();
        }
        else {
            return ResultUtil.error(-1,"搜索失败");
        }
    }

    /*
     * @Description:查询搜索历史记录
     * @param: [user_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/findAllSearchHistory/{user_id}")
    public Result findAllSearchHistory(@PathVariable Integer user_id){
        List<String>searchHistory=userService.findAllSearchHistory(user_id);
        return ResultUtil.success(searchHistory);
    }

    /*
     * @Description:用户清除搜索历史记录
     * @param: [user_id]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/deleteAllSearchHistoryByUser_id/{user_id}")
    public Result deleteAllSearchHistory(@PathVariable Integer user_id){
        userService.deleteAllSearchHistory(user_id);
        return ResultUtil.success();
    }

    /*
     * @Description:用户关注商铺
     * @param: [collect]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/collectSeller")
    public Result collectSeller(@RequestBody UserCollectSeller collect){
        userService.collectSeller(collect);
        return ResultUtil.success();
    }

    /*
     * @Description:获取用户关注的商铺
     * @param: [userId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/getCollectSellerByUserId/{userId}")
    public Result getCollectSellerByUserId(@PathVariable Integer userId){
        List<Seller> sellerList = userService.getCollectSellerByUserId(userId);
        return ResultUtil.success(sellerList);
    }

    /*
     * @Description:查询用户是否收藏了该商品
     * @param: [sellerId, userId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/selectUserCollectSeller/{sellerId}/{userId}")
    public Result selectUserCollectSeller(@PathVariable Integer sellerId,@PathVariable Integer userId){
        String result = userService.selectUserCollectSeller(sellerId,userId);
        return ResultUtil.success(result);
    }

    @GetMapping("/getProductCollectNum/{productId}")
    public Result getProductCollectNum(){
        return ResultUtil.success();
    }
}
