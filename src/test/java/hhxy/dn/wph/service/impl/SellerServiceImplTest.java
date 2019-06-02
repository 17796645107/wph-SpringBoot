package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.Product;
import hhxy.dn.wph.mapper.SellerMapper;
import hhxy.dn.wph.service.SellerService;
import hhxy.dn.wph.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: 邓宁
 * @Date: Created in 11:43 2019/5/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
    public static final Logger logger = LoggerFactory.getLogger(SellerServiceImplTest.class);

    @Autowired
    private SellerMapper sellerMapper;

    @Test
    public void saveOneProduct() {

    }
}