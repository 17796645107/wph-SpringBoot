package hhxy.dn.wph.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 邓宁
 * @date Created in 14:22 2019/5/3
 */
//生成ID工具类
public class IDUtil {
    //生成订单ID
    public static String createOrderID(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date()) + System.currentTimeMillis();
    }

    /**
     * 生成商品编号
     * @return java.lang.String
     */
    public static String createProductNo(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date()) + System.currentTimeMillis();
    }

    /**
     * 生成人员ID--用户,商户,管理员
     * @param sub 前缀字符串:用户-U,商户-S,管理员-A
     * @return String
     */
    public static String createPeopleID(String sub){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return sub + getRandomCode() + format.format(new Date());
    }

    //生成6位数随机验证码
    private static String getRandomCode(){
        Random random = new Random();
        int code =  random.nextInt(9999);
        return String.valueOf(code < 1000 ? code + 1000 : code);
    }

    public static void main(String[] args) {
        /*System.out.println(DateUtil.getDate());
        System.out.println(System.currentTimeMillis());
        System.out.println(createOrderID());*/
        System.out.println(createOrderID());
    }
}
