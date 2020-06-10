package hhxy.dn.wph.service;

import hhxy.dn.wph.TestTemplate;
import hhxy.dn.wph.entity.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceTest extends TestTemplate {

    @Autowired
    ProductService productService;

    @Test
    public void searchProduct() {
        List<Product> productList = productService.searchProduct(1, "/%");
        productList.forEach(System.out::println);
    }
}
