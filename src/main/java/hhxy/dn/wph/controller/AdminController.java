package hhxy.dn.wph.controller;

import hhxy.dn.wph.entity.Seller;
import hhxy.dn.wph.entity.SellerAccount;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 邓宁
 * @date Created in 10:36 2019/5/9
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/login")
    public static String adminLogin(@RequestBody SellerAccount account){
        return "";
    }

}
