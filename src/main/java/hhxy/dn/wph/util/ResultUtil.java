package hhxy.dn.wph.util;

import hhxy.dn.wph.entity.Result;

/**
 * @author 邓宁
 * @date Created in 13:58 2018/11/12
 */

public class ResultUtil {
    //成功(包含data)
    public static Result success(Object object){
        Result result=new Result();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    //成功(不包含data)
    public static Result success(){
        return success(null);
    }

    //失败
    public static Result error(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
