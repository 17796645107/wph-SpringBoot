package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.ProductAttributeRelation;
import hhxy.dn.wph.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 11:07 2019/6/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findProductByArrtibute() {
        List<ProductAttributeRelation> productAttributeRelations = new ArrayList<>();

        ProductAttributeRelation relation = new ProductAttributeRelation();
        relation.setAttributeId(4);
        relation.setValueId(43);
        productAttributeRelations.add(relation);

        ProductAttributeRelation relation1 = new ProductAttributeRelation();
        relation1.setAttributeId(3);
        relation1.setValueId(43);
        productAttributeRelations.add(relation1);

        ProductAttributeRelation relation2 = new ProductAttributeRelation();
        relation2.setAttributeId(6);
        relation2.setValueId(62);
        productAttributeRelations.add(relation2);

        productService.findProductByArrtibute(productAttributeRelations);
    }

    @Test
    public void findProductInSeller() {
        List<Product> productList = productService.findProductInSeller(1,43,null,null,null);
        productList.forEach(product -> System.out.println(product));
    }

    /*@Test
    public void update() {
        productService.update();
    }*/
}