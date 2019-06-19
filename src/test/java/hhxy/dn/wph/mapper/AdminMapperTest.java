package hhxy.dn.wph.mapper;

import hhxy.dn.wph.entity.Category;
import hhxy.dn.wph.entity.ProductAttribute;
import hhxy.dn.wph.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 18:28 2019/5/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminMapperTest {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void saveCategory() {

        List<String> list = new ArrayList<>();
        list.add("女士保暖");
        list.add("男士保暖");
        list.add("女士羊绒衫");
        list.add("男士羊绒衫");
        list.add("羊绒羊毛大衣");
        list.add("羊绒裙");
        Category category = new Category();
        list.forEach(s -> {
            category.setCategoryName(s);
            category.setCategorySort(0);
            category.setParentId(7);
            category.setAdminId(1);
            category.setCreated(DateUtil.getDate());
            category.setStatus(1);
            adminMapper.saveCategory(category);
        });
    }

    @Test
    public void saveAttribute() {
        List<String> list = new ArrayList<>();
        list.add("规格数量");
//        list.add("");
        ProductAttribute attribute = new ProductAttribute();
        list.forEach(s -> {
            attribute.setAttrName(s);
            attribute.setIsSearch(1);
            attribute.setId(1);
            attribute.setCreated(DateUtil.getDate());
            attribute.setStatus(1);
            adminMapper.saveAttribute(attribute);
        });


    }
}