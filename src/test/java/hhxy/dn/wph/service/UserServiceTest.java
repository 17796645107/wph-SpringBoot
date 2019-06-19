package hhxy.dn.wph.service;

import com.aliyuncs.exceptions.ClientException;
import hhxy.dn.wph.TestTemplate;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.util.DateUtil;
import hhxy.dn.wph.util.RedisUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 邓宁
 * @Date: Created in 10:07 2019/6/15
 */

public class UserServiceTest extends TestTemplate {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void userRegister() {
        redisUtil.set("12345678900","123456",60*5);
        UserRegister userRegister = new UserRegister();
        userRegister.setTelephone("12345678900");
        userRegister.setPassword("123456");
        userRegister.setTelephoneCode("123456");
        userService.userRegister(userRegister);
    }

    @Test
    public void userLogin() {
        User user = userService.userLogin("12345678900","123456");
        LOGGER.info(user.toString());
    }

    @Test
    public void userCheckTelephone() {
        userService.userCheckTelephone("12345678900");
    }

    @Test
    public void userSendCode() {
        try {
            userService.userSendCode("13673812737");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userImageCode() {
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(26);
        user.setSex("男");
        userService.updateUser(user,"3efc2f43-4a73-4b34-82e7-52da6ab131fd");
    }

    @Test
    public void saveUserAddress() {
        UserAddress address = new UserAddress();
        address.setUserId(26);
        address.setName("李慢慢");
        address.setProvince("河南省");
        address.setCity("郑州市");
        address.setTown("龙湖镇");
        address.setArea("河南工程学院西区");
        address.setPostcode("6666666");
        address.setTelephone("13333713719");
        address.setCreated(DateUtil.getDate());
        userService.saveUserAddress(address);
    }

    @Test
    public void updateUserAddress() {
        UserAddress address = new UserAddress();
        address.setId(12);
        address.setName("李慢慢2");
        address.setProvince("河南省2");
        address.setCity("郑州市2");
        address.setTown("龙湖镇2");
        address.setArea("河南工程学院西区2");
        address.setPostcode("6666666");
        address.setTelephone("13333713719");
        address.setUpdated(DateUtil.getDate());
        userService.updateUserAddress(address);
    }

    @Test
    public void updateDefaultUserAddress() {
        userService.updateDefaultUserAddress(26,12);
    }

    @Test
    public void deleteUserAddressByAddressID() {
        userService.deleteUserAddressByAddressId(26,12);
    }

    @Test
    public void findAllUserAddress() {
        List<UserAddress> addressList = userService.findAllUserAddress(26);
        addressList.forEach(System.out::println);
    }

    @Test
    public void findAllSearchHistory() {
    }

    @Test
    public void deleteAllSearchHistory() {
    }

    @Test
    public void collectSeller() {
        UserCollectSeller collectSeller = new UserCollectSeller();
        collectSeller.setUserId(26);
        collectSeller.setSellerId(2);
        userService.collectSeller(collectSeller);
    }

    @Test
    public void getCollectSellerByUserId() {
        List<Seller> sellerList = userService.getCollectSellerByUserId(26);
        sellerList.forEach(System.out::println);
    }

    @Test
    public void getUserDetail() {
        User user = userService.getUserDetail(26);
        LOGGER.info(user.toString());
    }

    @Test
    public void saveUserHeadIcon() {
    }

    @Test
    public void selectUserCollectSeller() {
        String result = userService.selectUserCollectSeller(15,26);
        LOGGER.info(result);
    }

    @Test
    public void loadUserByUsername() {
    }
}