package hhxy.dn.wph.service;

import hhxy.dn.wph.TestTemplate;
import hhxy.dn.wph.entity.UserRegister;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: 邓宁
 * @Date: Created in 10:07 2019/6/15
 */

public class UserServiceTest extends TestTemplate {

    private UserService userService;

    @Test
    public void userRegister() {
        UserRegister userRegister = new UserRegister();
        userRegister.setTelephone("12345678900");
        userRegister.setPassword("123456");
        userService.userRegister(userRegister);
    }

    @Test
    public void userLogin() {
    }

    @Test
    public void userCheckTelephone() {
    }

    @Test
    public void userSendCode() {
    }

    @Test
    public void userImageCode() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void saveUserAddress() {
    }

    @Test
    public void updateUserAddress() {
    }

    @Test
    public void updateDefaultUserAddress() {
    }

    @Test
    public void deleteUserAddressByAddressID() {
    }

    @Test
    public void findAllUserAddress() {
    }

    @Test
    public void findAllSearchHistory() {
    }

    @Test
    public void deleteAllSearchHistory() {
    }

    @Test
    public void collectSeller() {
    }

    @Test
    public void getCollectSellerByUserId() {
    }

    @Test
    public void getUserDetail() {
    }

    @Test
    public void saveUserHeadIcon() {
    }

    @Test
    public void selectUserCollectSeller() {
    }

    @Test
    public void loadUserByUsername() {
    }
}