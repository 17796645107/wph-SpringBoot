package hhxy.dn.wph.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 14:22 2019/5/3
 */

public class OrderIDUtil {
    public static String createOrderID(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer buffer = new StringBuffer();
        buffer.append(format.format(new Date()));
        buffer.append(System.currentTimeMillis());
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getDate());
        System.out.println(System.currentTimeMillis());
        System.out.println(createOrderID());
    }
}
