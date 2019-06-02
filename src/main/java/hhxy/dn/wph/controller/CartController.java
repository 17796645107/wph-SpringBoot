package hhxy.dn.wph.controller;

import hhxy.dn.wph.entity.GoodCart;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.service.GoodCartService;
import hhxy.dn.wph.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @Author: 邓宁
 * @Date: Created in 13:47 2018/11/12
 */

@RestController
@RequestMapping("/goodCart")
public class CartController {

    @Autowired
    private GoodCartService goodCartService;

    /*
     * @Description:生成一条购物车记录
     * @param: [goodCart]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/saveGoodCart")
    public Result saveGoodCart(@RequestBody GoodCart goodCart){
        goodCartService.saveGoodCart(goodCart);
        return ResultUtil.success();
    }

    /*
     * @Description:查询购物车列表
     * @param: [userId]用户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getGoodCart/{userId}")
    public Result getGoodCart(@PathVariable Integer userId){
        List<GoodCart> goodCartList = goodCartService.getGoodCart(userId);
        return ResultUtil.success(goodCartList);
    }

    @GetMapping("/deleteGoodCartById/{id}")
    public Result deleteGoodCartById(@PathVariable Integer id){
        goodCartService.deleteGoodCartById(id);
        return ResultUtil.success();
    }

    @RequestMapping("/getCartCount/{userId}")
    public Result getCartCount(@PathVariable Integer userId){
        Integer cartCount = goodCartService.getCartCount(userId);
        return ResultUtil.success(cartCount);
    }

    @RequestMapping("/getGoodCartByIdList")
    public Result getGoodCartByIdList(@RequestParam("idList") int[] idList){
        List<GoodCart> goodCartList = goodCartService.getGoodCartByIdList(idList);
        return ResultUtil.success(goodCartList);
    }

    @RequestMapping("/updateGoodCartById")
    public Result updateGoodCartById(@RequestBody GoodCart goodCart){
        goodCartService.updateGoodCartById(goodCart);
        return ResultUtil.success();
    }
}
