package hhxy.dn.wph.controller;

import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.entity.Seller;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 邓宁
 * @Date: Created in 13:47 2018/11/12
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    private final Logger logger = LoggerFactory.getLogger(SellerController.class);

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
    @RequestMapping("/getSellerById/{sellerId}")
    public Result getSellerById(@PathVariable Integer sellerId){
        Seller seller = sellerService.getSellerById(sellerId);
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


}
