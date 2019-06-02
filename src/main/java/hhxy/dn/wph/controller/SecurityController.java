package hhxy.dn.wph.controller;

import hhxy.dn.wph.entity.Result;
import hhxy.dn.wph.util.ResultUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 邓宁
 * @Date: Created in 23:52 2019/5/6
 */
@RestController
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping("/accessDenied")
    public Result exceptionHandling(){
        return ResultUtil.error(-1,"没有权限访问");
    }
}
