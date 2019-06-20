package hhxy.dn.wph.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: 邓宁
 * @Date: Created in 14:22 2019/5/3
 */
//生成ID工具类
public class IDUtil {
    //生成订单ID
    public static String createOrderID(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuilder builder = new StringBuilder();
        builder.append(format.format(new Date()));
        builder.append(System.currentTimeMillis());
        return builder.toString();
    }

    /**
     * 生成商品编号
     * @param
     * @return java.lang.String
     */
    public static String createProductNo(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append(format.format(new Date()));
        builder.append(System.currentTimeMillis());
        return builder.toString();
    }

    /**
     * @Description:生成人员ID--用户,商户,管理员
     * @param: sub前缀字符串:用户-U,商户-S,管理员-A
     * @return:
     */
    public static String createPeopleID(String sub){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        StringBuilder builder = new StringBuilder();
        builder.append(sub);
        builder.append(getRandomCode());
        builder.append(format.format(new Date()));
        return builder.toString();
    }

    //生成6位数随机验证码
    public static String getRandomCode(){
        Random random = new Random();
        Integer code =  random.nextInt(9999);
        return String.valueOf(code < 1000 ? code += 1000 : code);
    }

    public static void main(String[] args) {
        /*System.out.println(DateUtil.getDate());
        System.out.println(System.currentTimeMillis());
        System.out.println(createOrderID());*/
        System.out.println(createPeopleID("A"));
    }
}
