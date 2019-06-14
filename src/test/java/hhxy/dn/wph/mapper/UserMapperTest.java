package hhxy.dn.wph.mapper;

import hhxy.dn.wph.TestTemplate;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: 邓宁
 * @Date: Created in 17:26 2019/6/14
 */
//用户Mapper接口测试
public class UserMapperTest extends TestTemplate {

    @Resource
    private UserMapper userMapper;

    @Test
    public void userRegister() {
        User user = new User();
        user.setTelephone("17796645107");
        Integer user_no = userMapper.saveUser(user);
        LOGGER.info("user_no = {}",user.getUser_no());
    }

    @Test
    public void userPasswordRegister() {
        UserPassword userPassword = new UserPassword();
        userPassword.setUser_no(4);
        userPassword.setPassword("123456");
        Integer result = userMapper.saveUserPassword(userPassword);
        LOGGER.info("result = {}",result);
    }

    @Test
    public void getUserByTelephone() {
        User user = userMapper.findUserByTelephone("17796645107");
        LOGGER.info("user = {}",user.toString());
    }

    @Test
    public void getUserPassword() {
        String password = userMapper.findUserPasswordByNo(1);
        assertEquals(MD5Util.getMD5("123456"),password);
    }

    @Test
    public void userCheckTelephone() {
        String telephone = userMapper.findTelephone("17796645107");
        assertEquals(null,telephone,false);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setUser_no(1);
        user.setSex("女");
        user.setNickname("李慢慢是头猪");
        user.setEmail("222@qq.com");
        user.setName("大傻子");
        int result = userMapper.updateUser(user);
        assertEquals(result,1);
    }

    @Test
    public void saveUserAddress() {
        UserAddress address = new UserAddress();
        address.setUser_no(1);
        address.setName("李慢慢");
        address.setProvince("河南省");
        address.setCity("郑州市");
        address.setTown("龙湖镇");
        address.setArea("河南工程学院西区");
        address.setPostcode("6666666");
        address.setTelephone("13333713719");
        int result = userMapper.saveUserAddress(address);
        assertEquals(result,1);
    }

    @Test
    public void updateUserAddress() {
        UserAddress address = new UserAddress();
        address.setAddress_id(6);
        address.setName("李慢慢");
        address.setProvince("河南省");
        address.setCity("郑州市");
        address.setTown("龙湖镇");
        address.setArea("河南工程学院西区");
        address.setPostcode("6666666");
        address.setTelephone("13333713719");
        int result = userMapper.updateUserAddressById(address);
        assertEquals(result,1);
    }

    @Test
    public void updateAllUserAddress() {
        int result = userMapper.updateAllUserAddressByNo(1);
        LOGGER.info("result = {}",result);
    }

    @Test
    public void updateDefaultUserAddress() {
        int result = userMapper.updateDefaultUserAddressByID(8);
        assertEquals(result,1);
    }

    @Test
    public void deleteUserAddressByAddressID() {
        int result = userMapper.deleteUserAddressByID(4);
        assertEquals(result,1);
    }

    @Test
    public void findAllUserAddress() {
        List<UserAddress> addressList = userMapper.findAddressListByUserNo(1);
        addressList.forEach(System.out::println);
    }

    @Test
    public void saveSearchHistory() {
        int result = userMapper.saveSearchHistory("笨猪李慢慢",1);
        assertEquals(result,1);
    }

    @Test
    public void findAllSearchHistory() {
        List<String> searchList = userMapper.findAllSearchHistory(1);
        searchList.forEach(System.out::println);
    }

    @Test
    public void deleteAllSearchHistory() {
        int result = userMapper.deleteAllSearchHistory(1);
        assertEquals(result,1);
    }

    @Test
    public void collectSeller() {
        UserCollectSeller collectSeller = new UserCollectSeller();
        collectSeller.setUser_no(1);
        collectSeller.setSeller_no(5);
        int result = userMapper.saveCollectSeller(collectSeller);
        assertEquals(result, 1);
    }

    @Test
    public void getCollectSellerByUserId() {
        List<Seller> sellerList = userMapper.findCollectSellerById(1);
        sellerList.forEach(System.out::println);
    }

    @Test
    public void updateUserHeadIcon() {
        User user = new User();
        user.setUser_no(1);
        user.setHeadImage("avatar_89373029_1496285287409.jpg");
        int result = userMapper.updateUser(user);
        assertEquals(result,1);
    }

    @Test
    public void selectUserCollectSeller() {
        UserCollectSeller collectSeller = userMapper.findUserCollectSellerById(1,1);
        assertEquals(collectSeller.getUser_no().toString(),"1");
        assertEquals(collectSeller.getSeller_no().toString(),"1");
    }

    @Test
    public void getUserByNo() {
        User user = userMapper.findUserByNo(1);
        LOGGER.info(user.toString());
    }
}