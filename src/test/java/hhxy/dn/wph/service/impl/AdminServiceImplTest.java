package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: 邓宁
 * @Date: Created in 0:58 2019/5/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void saveProductParam() {
        String param = "适用季节： \t夏";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam22() {
        String param = "适用季节： \t春";
        adminService.saveProductParam(param);
    }
    /*@Test
    public void saveProductParam1() {
        String param = "裤门襟： \t拉链";
        adminService.saveProductParam(param);
    }*/
    @Test
    public void saveProductParam133() {
        String param = "衣门襟： \t后拉链";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam1() {
        String param = "肩型： \t常规肩";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam13() {
        String param = "袖型： \t泡泡袖";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam2() {
        String param = "流行元素： \t蕾丝";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam3() {
        String param = "适用人群： \t青年";
        adminService.saveProductParam(param);
    }@Test
    public void saveProductParam32() {
        String param = "适用人群： \t中年";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam4() {
        String param = "适用场合： \t休闲";
        adminService.saveProductParam(param);
    }
    /*@Test
    public void saveProductParam5() {
        String param = "填充物： \t无填充";
        adminService.saveProductParam(param);
    }*/
    /*@Test
    public void saveProductParam6() {
        String param = "功能： \t便携";
        adminService.saveProductParam(param);
    }*/
    @Test
    public void saveProductParam7() {
        String param = "面料： \t锦纶(尼龙)";
        adminService.saveProductParam(param);
    }
    /*@Test
    public void saveProductParam11() {
        String param = "腰型： \t中腰";
        adminService.saveProductParam(param);
    }*/
    @Test
    public void saveProductParam111() {
        String param = "款式： \t套头";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam1113() {
        String param = "弹性： \t无弹";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam12() {
        String param = "风格： \t通勤";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam1231() {
        String param = "风格： \t休闲";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam14() {
        String param = "厚薄： \t薄";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam123() {
        String param = "版型： \t常规";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam145() {
        String param = "图案： \t纯色";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam126() {
        String param = "产地： \t宁波";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam1268() {
        String param = "领型： \tV领";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam1265() {
        String param = "洗涤说明： \t手洗，不可漂白，悬挂晾干，熨斗底板最高温度110℃，不可干洗";
        adminService.saveProductParam(param);
    }
    /*@Test
    public void saveProductParam12651() {
        String param = "裙长： \t中长裙";
        adminService.saveProductParam(param);
    }*/
    @Test
    public void saveProductParam126511() {
        String param = "衣长： \t常规";
        adminService.saveProductParam(param);
    }
    @Test
    public void saveProductParam121() {
        String param = "材质： \t面料成分-棉100% 网纱成分-聚酯纤维100% 花边成分-聚酯纤维100%";
        adminService.saveProductParam(param);
    }

    /*@Test
    public void saveProductParam1264() {
        String param = "袖长： \t短袖";
        adminService.saveProductParam(param);
    }*/
    @Test
    public void saveProductParam1263() {
        String param = "面料： \t棉";
        adminService.saveProductParam(param);
    }
    /*@Test
    public void saveProductParam1262() {
        String param = "重量： \t200g";
        adminService.saveProductParam(param);
    }*/
}

//    @Test
//    public void saveCategoryAttributeRelation() {
////        String[]attr={"适用项目","适用季节","尺码","功能"};
////        String[]attr={"裤长","腰型","版型","款式","裤门襟","厚薄","弹性","适用季节"};
////      String[]attr={"版型","衣长","厚薄","门襟","款式","袖型","选购热点","风格"};
////      String[]attr={"版型","衣长","袖长","领型","肩型","袖型","图案","选购热点"};
//      String[]attr={"裙型","裙长","版型","适用季节","弹性","裙/裤门襟","选购热点"};
//        CategoryAttributeRelation categoryAttributeRelation = new CategoryAttributeRelation();
//        categoryAttributeRelation.setCategoryId(60);
//        Integer integer = 0;
//        for (String s : attr) {
//            integer = adminMapper.findCategoryIdByCategoryName(s);
//            if (integer == null){
//                ProductAttribute attribute = new ProductAttribute();
//                attribute.setAttrName(s);
//                adminMapper.saveAttribute(attribute);
//                integer = attribute.getAttrId();
//            }
//            categoryAttributeRelation.setAttributeId(integer);
//            adminService.saveCategoryAttributeRelation(categoryAttributeRelation);
//        }
//    }
