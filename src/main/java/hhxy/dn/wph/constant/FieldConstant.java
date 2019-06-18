package hhxy.dn.wph.constant;

import hhxy.dn.wph.entity.User;

import java.lang.reflect.Field;

/**
 * @Author: 邓宁
 * @Date: Created in 19:54 2019/6/18
 */
//字段常量类
public class FieldConstant {

    static String getFiele(Object object){
        Class c = object.getClass();
        System.out.println(c.getName());
        Field [] fields = c.getDeclaredFields();
        Field [] fields1 = c.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        return null;
    }

    public static void main(String[] args) {
        getFiele(new User());
    }
}
