package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 邓宁
 * @date Created in 22:23 2019/5/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void getOrderByOrderId() {
        Order order = orderMapper.getOrderByOrderId("!11");
        System.out.println(order);
    }
}