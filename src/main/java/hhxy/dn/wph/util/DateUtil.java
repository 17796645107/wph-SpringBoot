package hhxy.dn.wph.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 20:59 2019/4/5
 */

public class DateUtil {
    public static String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
    public static String getBirthday(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static void main(String[] args) {
    }
}
