package hhxy.dn.wph.util;

import hhxy.dn.wph.entity.Seller;
import hhxy.dn.wph.entity.User;
import hhxy.dn.wph.entity.UserAddress;

import java.lang.reflect.Field;

/**
 * @Author: 邓宁
 * @Date: Created in 20:52 2019/6/18
 * 反射工具类
 */
public class ReflectUtil {
    /**
     * 获取成员变量
     * @param c
     * @return java.lang.String
     */
    public static String getField(Class c){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        Field[] fields = c.getDeclaredFields();
        for (int i = 0,l = fields.length; i < l; i++) {
            stringBuilder.append(fields[i].getName());
            if (i < l - 1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getField(Seller.class));
        System.out.println(MD5Util.getMD5("123456"));
    }
}
