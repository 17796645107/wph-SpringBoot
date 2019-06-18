package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Category;
import hhxy.dn.wph.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 20:24 2019/5/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findCategoryBySellerId() {
        List<Category> categoryList = productMapper.findCategoryBySellerId(1);
        categoryList.forEach(category -> System.out.println(category));
    }

    @Test
    public void getOneProduct(){
        Product product = productMapper.getProductByProductId(2019050503);
        System.out.println(product);
    }

}