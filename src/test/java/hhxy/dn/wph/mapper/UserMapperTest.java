package hhxy.dn.wph.mapper;

import hhxy.dn.wph.TestTemplate;
import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.util.DateUtil;
import hhxy.dn.wph.util.IDUtil;
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
import java.util.Date;
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
        user.setUserNo(IDUtil.createPeopleID("U"));
        user.setCreated(DateUtil.getDate());
        Integer user_no = userMapper.saveUser(user);
        LOGGER.info("id = {}",user.getId());
    }

    @Test
    public void userPasswordRegister() {
        UserPassword userPassword = new UserPassword();
        userPassword.setUserId(26);
        userPassword.setPassword("123456");
        Integer result = userMapper.saveUserPassword(userPassword);
        LOGGER.info("result = {}",result);
    }

    @Test
    public void getUserByTelephone() {
        User user = userMapper.getUserByTelephone("17796645107");
        LOGGER.info("user = {}",user.toString());
    }

    @Test
    public void getUserPassword() {
        String password = userMapper.getPasswordByUserId(26);
        assertEquals(MD5Util.getMD5("123456"),password);
    }

    @Test
    public void userCheckTelephone() {
        String telephone = userMapper.getTelephone("17796645107");
        assertEquals("17796645107",telephone);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(26);
        user.setSex("女");
        user.setNickname("李慢慢是头猪");
        user.setEmail("222@qq.com");
        user.setName("大傻子");
        user.setBirthday(DateUtil.getBirthday());
        user.setUpdated(DateUtil.getDate());
        int result = userMapper.updateUser(user);
        assertEquals(result,1);
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
        int result = userMapper.saveUserAddress(address);
        assertEquals(result,1);
    }

    @Test
    public void updateUserAddress() {
        UserAddress address = new UserAddress();
        address.setId(11);
        address.setName("李慢慢1");
        address.setProvince("河南省1");
        address.setCity("郑州市1");
        address.setTown("龙湖镇1");
        address.setArea("河南工程学院西区1");
        address.setPostcode("66666661");
        address.setTelephone("133337137191");
        address.setUpdated(DateUtil.getDate());
        int result = userMapper.updateUserAddressById(address);
        assertEquals(result,1);
    }

    @Test
    public void updateAllUserAddress() {
        int result = userMapper.updateAllUserAddressById(26);
        assertEquals(result,1);
    }

    @Test
    public void updateDefaultUserAddress() {
        int result = userMapper.updateDefaultUserAddressById(11);
        assertEquals(result,1);
    }

    @Test
    public void deleteUserAddressByAddressID() {
        int result = userMapper.deleteUserAddressById(2);
        assertEquals(result,1);
    }

    @Test
    public void findAllUserAddress() {
        List<UserAddress> addressList = userMapper.listAddressByUserId(26);
        addressList.forEach(System.out::println);
    }

    @Test
    public void saveSearchHistory() {
        int result = userMapper.saveSearchHistory(26,"笨猪李慢慢");
        assertEquals(result,1);
    }

    @Test
    public void findAllSearchHistory() {
        List<String> searchList = userMapper.listSearchHistory(1);
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
        collectSeller.setUserId(26);
        collectSeller.setSellerId(5);
        int result = userMapper.saveCollectSeller(collectSeller);
        assertEquals(result, 1);
    }

    @Test
    public void getCollectSellerByUserId() {
        List<Seller> sellerList = userMapper.listCollectSellerByUserId(26);
        sellerList.forEach(System.out::println);
    }

    @Test
    public void updateUserHeadIcon() {
        User user = new User();
        user.setId(26);
        user.setHeadImage("avatar_89373029_1496285287409.jpg");
        user.setUpdated(DateUtil.getDate());
        int result = userMapper.updateUser(user);
        assertEquals(result,1);
    }

    @Test
    public void selectUserCollectSeller() {
        UserCollectSeller collectSeller = userMapper.getUserCollectSeller(5,26);
        assertEquals(collectSeller.getUserId().toString(),"26");
        assertEquals(collectSeller.getSellerId().toString(),"5");
    }

    @Test
    public void getUserByNo() {
        User user = userMapper.getUserById(26);
        LOGGER.info(user.toString());
    }
}