package hhxy.dn.wph.controller;

import hhxy.dn.wph.entity.Cart;
import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.service.GoodCartService;
import hhxy.dn.wph.util.ResultUtil;
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

    /**
     * @Description: 生成一条购物车记录
     * @param: [goodCart]
     * @return: hhxy.dn.wph.entity.Result
     */
    @PostMapping("/saveGoodCart")
    public Result saveCart(@RequestBody Cart cart){
        goodCartService.saveGoodCart(cart);
        return ResultUtil.success();
    }

    /**
     * @Description:查询购物车列表
     * @param: [userId]用户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getGoodCart/{userId}")
    public Result getGoodCart(@PathVariable Integer userId){
        List<Cart> goodCartList = goodCartService.listGoodCartByUserId(userId);
        return ResultUtil.success(goodCartList);
    }

    /**
     * @Description:删除购物车记录
     * @param: [id]购物车ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @GetMapping("/deleteGoodCartById/{id}")
    public Result deleteGoodCartById(@PathVariable Integer id){
        goodCartService.deleteGoodCartById(id);
        return ResultUtil.success();
    }

    /**
     * @Description:获取购物车记录数
     * @param: [userId]用户ID
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getCartCount/{userId}")
    public Result getCartCount(@PathVariable Integer userId){
        Integer cartCount = goodCartService.getCartCount(userId);
        return ResultUtil.success(cartCount);
    }

    /**
     * @Description:获取购物车列表
     * @param: [idList]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/getGoodCartByIdList")
    public Result getGoodCartByIdList(@RequestParam("idList") int[] idList){
        List<Cart> goodCartList = goodCartService.listGoodCartByIdList(idList);
        return ResultUtil.success(goodCartList);
    }

    /**
     * @Description:更新一个购物车
     * @param: [goodCart]
     * @return: hhxy.dn.wph.entity.Result
     */
    @RequestMapping("/updateGoodCartById")
    public Result updateGoodCartById(@RequestBody Cart goodCart){
        goodCartService.updateGoodCartById(goodCart);
        return ResultUtil.success();
    }
}
