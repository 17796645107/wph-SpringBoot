package hhxy.dn.wph.mapper.provider;

import hhxy.dn.wph.entity.User;
import hhxy.dn.wph.entity.UserAddress;
import org.apache.ibatis.jdbc.SQL;

import static hhxy.dn.wph.constant.DataBaseTableConstant.*;
/**
 * @author 邓宁
 * @date Created in 22:33 2018/11/12
 * 动态SQL
 */

public class UserProvider {

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return sql
     */
    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(USER);
                if (user.getNickname() != null){
                    SET("nickname = #{nickname}");
                }
                if (user.getName() != null){
                    SET("name = #{name}");
                }
                if (user.getTelephone() != null){
                    SET("telephone = #{telephone}");
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
                if (user.getHeadImage() != null){
                    SET("head_image = #{headImage}");
                }
                WHERE("id = #{id}");
                WHERE("state = 1");
            }
        }.toString();
    }

    /**
     * 添加用户收货地址
     * @param address 收货地址
     * @return sql
     */
    public String saveUserAddress(UserAddress address){
        return new SQL(){
            {
                INSERT_INTO(USER_ADDRESS);
                VALUES("user_id","#{userId}");
                VALUES("name","#{name}");
                VALUES("province","#{province}");
                VALUES("city","#{city}");
                VALUES("town","#{town}");
                VALUES("area","#{area}");
                VALUES("postcode","#{postcode}");
                VALUES("telephone","#{telephone}");
                VALUES("created","#{created}");
            }
        }.toString();
    }

    /**
     * 更新用户收货地址
     * @param address 收货地址
     * @return sql
     */
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
                if(address.getTown() != null){
                    SET("town = #{town}");
                }
                if(address.getArea() != null){
                    SET("area = #{area}");
                }
                if(address.getPostcode() != null){
                    SET("postcode = #{postcode}");
                }
                if(address.getTelephone() != null){
                    SET("telephone = #{telephone}");
                }
                WHERE("id = #{id}");
                WHERE("state = 1");
            }
        }.toString();
    }
}
