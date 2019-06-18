package hhxy.dn.wph.controller;

import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.entity.Seller;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.CookieUtil;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: 邓宁
 * @Date: Created in 13:47 2018/11/12
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    private final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SellerService sellerService;

    //获得某一类型的所有商家
    @RequestMapping("/getSellerByType/{primaryId}")
    public Result getSellerByPrimaryCategoryId(@PathVariable Integer primaryId){
        return ResultUtil.success(sellerService.getSellerByPrimaryCategoryId(primaryId));
    }

    /*
     * @Description:获取商户信息
     * @param: [sellerId]商户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getSellerById/{sellerNo}")
    public Result getSellerById(@PathVariable Integer sellerNo){
        Seller seller = sellerService.getSellerById(sellerNo);
        return ResultUtil.success(seller);
    }

    /*
     * @Description:获取商户的关注量
     * @param: [sellerId]
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/getSellerCollectNum/{sellerId}")
    public Result getSellerCollectNum(@PathVariable Integer sellerId){
        int result = sellerService.getSellerCollectNum(sellerId);
        return ResultUtil.success(result);
    }

    @PostMapping("/login")
    public Result sellerLogin(@RequestBody SellerAccount sellerAccount,
                              HttpServletRequest request,HttpServletResponse response){
        Seller seller = sellerService.login(sellerAccount);
        String token = UUID.randomUUID().toString();
        redisUtil.set("token", JsonUtil.objectToJson(seller));
        CookieUtil.setCookie(request,response,"SellerToken",token,60*60*24*7);
        return ResultUtil.success(seller);
    }

}
