package hhxy.dn.wph.controller;

import com.github.pagehelper.PageInfo;
import hhxy.dn.wph.entity.Product;
import hhxy.dn.wph.entity.SellerAccount;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.entity.Seller;
import hhxy.dn.wph.service.ProductService;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.CookieUtil;
import hhxy.dn.wph.util.JsonUtil;
import hhxy.dn.wph.util.RedisUtil;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @author 邓宁
 * @date Created in 13:47 2018/11/12
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    @Autowired
    private ProductService productService;

    /**
     * 获得某一类型的所有商家
     * @param categoryId
     * @return Result
     */
    @RequestMapping("/getSellerByType/{categoryId}")
    public Result listSellerByCategoryId(@PathVariable Integer categoryId){
        List<Seller> sellerList = sellerService.listSellerByCategoryId(categoryId);
        return ResultUtil.success(sellerList);
    }

    /**
     * 获取商户信息
     * @param sellerNo 商户ID
     * @return Result
     */
    @RequestMapping("/getSellerById/{sellerNo}")
    public Result getSellerById(@PathVariable Integer sellerNo){
        Seller seller = sellerService.getSellerById(sellerNo);
        return ResultUtil.success(seller);
    }

    /**
     * 获取商户的关注量
     * @param sellerId
     * @return Result
     */
    @GetMapping("/getSellerCollectNum/{sellerId}")
    public Result getSellerCollectNum(@PathVariable Integer sellerId){
        int collectNum = sellerService.getSellerCollectNum(sellerId);
        return ResultUtil.success(collectNum);
    }

    /**
     * 商户登录
     * @param sellerAccount 账户
     * @return Result
     */
    @PostMapping("/login")
    public Result sellerLogin(@RequestBody SellerAccount sellerAccount){
        Seller seller = sellerService.login(sellerAccount);
        return ResultUtil.success(seller);
    }

    /**
     * 分页获取商品列表
     * @param sellerId 商户id
     * @param pageIndex 当前页
     * @param pageCount 每页数量
     * @return Result
     */
    @GetMapping("/pageList/{sellerId}/{pageIndex}/{pageCount}")
    public Result pageListProductById(@PathVariable int sellerId,@PathVariable int pageIndex,@PathVariable int pageCount){
        PageInfo pageInfo = productService.listProductBySellerId(sellerId,pageIndex,pageCount);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 添加商品
     * @param product 商品信息
     * @param files 商品图片
     * @return Result
     */
    @RequestMapping("/addProduct")
    public Result addProduct(Product product, MultipartFile[] files,String[] colors,String[] sizes){
        sellerService.addProduct(product,files,colors,sizes);
        return ResultUtil.success();
    }

    /**
     * 逻辑删除商品
     * @param productId 商品ID
     * @return Result
     */
    @RequestMapping("/updateProduct/{productId}")
    public Result updateProduct(@PathVariable int productId){
        sellerService.updateProduct(productId);
        return ResultUtil.success();
    }

}
