package hhxy.dn.wph.constant;

import hhxy.dn.wph.entity.Admin;
import hhxy.dn.wph.entity.User;
import hhxy.dn.wph.util.ReflectUtil;

import java.lang.reflect.Field;

/**
 * @Author: 邓宁
 * @Date: Created in 19:54 2019/6/18
 * @Description 字段常量类
 */
public class FieldConstant {
    public static final String USER_FIELD = " id,user_no,nickname,telephone,name,sex,birthday,email," +
            "vip,head_image,status,created,updated ";

    public static final String ADMIN_FIELD = ReflectUtil.getField(Admin.class);

    public static final String USER_ADDRESS_FIELD = " id,user_id,name,province,city,town,area,postcode," +
            "telephone,is_default,created,updated ";

    public static final String SELLER_FIELD = " id,seller_no,name,show_image,type,status,created,updated ";

    public static final String PRODUCT_FIELD = " id,product_no,seller_id,category_id,title,detail,price," +
            "collect,is_hot,is_new,status,created,updated ";

    public static final String CATEGORY_FIELD = " id,category_name,parent_id,category_sort," +
            "admin_id,status,created,updated ";

    public static final String PRODUCT_ATTRIBUTE_FIELD = " id,attr_name,is_search,status,created,updated ";

}
