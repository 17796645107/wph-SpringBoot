package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.*;
import hhxy.dn.wph.service.impl.SellerServiceImplTest;
import hhxy.dn.wph.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @Author: 邓宁
 * @Date: Created in 12:10 2019/5/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerMapperTest {
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private ProductMapper productMapper;

    Integer p_id = 2019052810;
    @Test
    public void saveOneProduct() {
        Product product = new Product();
        product.setProduct_id(p_id);
        product.setSeller_id(3);
        product.setBrand_id(3);
        product.setCategory_id(41);
        product.setTitle("【100%桑蚕丝】索菲丝尔V领印花小衫女宽松真丝衬衫");
        product.setDetail("性感V领 创意印花 度假风");
        product.setPrice(1098);
        sellerMapper.saveOneProduct(product);
    }

    @Test
    public void saveProductSize() {
        String[]size = {
                "S",
                "M",
                "L",
                "XL",
//                "XXL",
//                "XXXL",
        };
        ProductSize productSize = new ProductSize();
        productSize.setProduct_id(p_id);
        for (String s : size) {
            productSize.setSize(s);
            sellerMapper.saveProductSize(productSize);
        }
    }

    @Test
    public void saveProductColor() {
        ProductColor productColor = new ProductColor();
        productColor.setProduct_id(p_id);
        ProductImage productImage = new ProductImage();
        productImage.setProduct_id(p_id);

//        String[]colors = {"默认"};
        String[]colors = {
                "黑色",
//                "白色",
//                "粉色",
//                "红色",
                "红色",
//                "卡其色",
        };
        String[]images = {
//                "",
                "bce8bd65-2806-4afc-a6fb-b468806b17bf.jpg",
                "df965f72-9d1c-4e0f-982b-81d5ab83180b.jpg",
                "f36a789b-7fee-44c2-9510-57119dfc5f4c.jpg",
                "2a011708-0c17-48d2-a90c-f4fe34e53fbc.jpg",
                "8be986e0-bd6d-4734-ba60-8ee78b211b44.jpg",
                "a0d9da87-e3e5-4587-ae0f-08d492648bfe.jpg",
                "3a28c907-de61-42e4-8352-8c78c281fb4f.jpg",
                "bb1d600d-6110-4435-954e-1a351bf3208e.jpg",
                "2968ef62-34fc-4d6b-87a0-0fd91a2714c1.jpg",
                "70b5f2eb-82dc-4717-8fd2-3db47e6b38f5.jpg",
        };
        for (String color : colors) {
            productColor.setColor(color);
            sellerMapper.saveProductColor(productColor);
            int color_id = productColor.getColor_id();
            productImage.setColor_id(color_id);
            for (String image : images) {
                productImage.setImage(image);
                sellerMapper.saveProductImage(productImage);
            }
        }
    }

    @Test
    public void saveProductNum(){
        List<ProductColor> productColorList = productMapper.findProductColorByProductId(p_id);
        Set<ProductSize> productSizeList = productMapper.findProductSizeByProductId(p_id);
        ProductNum productNum = new ProductNum();
        productNum.setProduct_id(p_id);
        productNum.setNum(888);
        productColorList.forEach(productColor -> {
            productNum.setProduct_color(productColor.getColor());
            productSizeList.forEach(productSize -> {
                productNum.setProduct_size(productSize.getSize());
                sellerMapper.saveProductNum(productNum);
            });
        });
    }
}