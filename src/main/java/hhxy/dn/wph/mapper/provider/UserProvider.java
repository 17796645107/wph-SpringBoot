package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.User;
import hhxy.dn.wph.entity.UserAddress;
import org.apache.ibatis.jdbc.SQL;

import static hhxy.dn.wph.util.DBTableUtil.*;
/**
 * @Author: 邓宁
 * @Date: Created in 22:33 2018/11/12
 */
//用户动态SQL类
public class UserProvider {

    //更新用户信息
    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(USER);
                if (user.getNickname() != null){
                    SET("nickname = #{nickname}");
                }
                if (user.getSex() != null){
                    SET("sex = #{sex}");
                }
                if (user.getBirthday() != null){
                    SET("birthday = #{birthday}");
                }
                if (user.getEmail() != null){
                    SET("email = #{email}");
                }
                WHERE("user_no = #{user_no}");
                WHERE("status = 1");
            }
        }.toString();
    }

    //添加用户收货地址
    public String saveUserAddress(UserAddress address){
        return new SQL(){
            {
                INSERT_INTO(USER_ADDRESS);
                VALUES("user_id","#{user_id}");
                VALUES("name","#{name}");
                VALUES("province","#{province}");
                VALUES("city","#{city}");
                VALUES("town","#{town}");
                VALUES("area","#{area}");
                VALUES("postcode","#{postcode}");
                VALUES("telephone","#{telephone}");
                VALUES("status","#{status}");
                VALUES("is_default","#{is_default}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    //更新用户收货地址
    public String updateUserAddress(UserAddress address){
        return new SQL(){
            {
                UPDATE(USER_ADDRESS);
                if(address.getName() != null){
                    SET("name = #{name}");
                }
                if(address.getProvince() != null){
                    SET("province = #{province}");
                }
                if(address.getCity() != null){
                    SET("city = #{city}");
                }
                if(address.getArea() != null){
                    SET("town = #{town}");
                }
                if(address.getPostcode() != null){
                    SET("postcode = #{postcode}");
                }
                if(address.getTelephone() != null){
                    SET("telephone = #{telephone}");
                }
                SET("updated = #{updated}");//更新时间
                WHERE("address_id = #{address_id}");
            }
        }.toString();
    }

    //查询所有用户收货地址
    public String findAllUserAddress(Integer user_id){
        return new SQL(){
            {
                SELECT("address_id,name,province,city,town,area,postcode,telephone,is_default");
                FROM(USER_ADDRESS);
                WHERE("user_id = #{user_id}");
                WHERE("status = 1");
            }
        }.toString();
    }
}
