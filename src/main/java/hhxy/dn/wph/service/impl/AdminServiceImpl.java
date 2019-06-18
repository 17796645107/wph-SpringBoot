package hhxy.dn.wph.service.impl;

import hhxy.dn.wph.entity.CategoryAttribute;
import hhxy.dn.wph.entity.ProductAttribute;
import hhxy.dn.wph.entity.ProductAttributeRelation;
import hhxy.dn.wph.entity.ProductAttributeValue;
import hhxy.dn.wph.mapper.AdminMapper;
import hhxy.dn.wph.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 23:10 2019/5/15
 */
@Service
public class AdminServiceImpl {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Transactional
    public void saveProductParam(String param){
        String[]params = new String[2];
        params = param.split("： \t");
        Integer r1 = adminMapper.getAttributeId(params[0]);
        ProductAttribute attribute = new ProductAttribute();
        if (r1 == null){
            LOGGER.info("添加商品属性-----------");
            attribute.setAttrName(params[0]);
            attribute.setId(1);
            attribute.setIsSearch(1);
            attribute.setCreate(new Date());
            attribute.setStatus(1);
            adminMapper.saveAttribute(attribute);
            r1 = attribute.getId();
        }
        Integer r2 = adminMapper.getAttributeValueId(params[1]);
        ProductAttributeValue attributeValue = new ProductAttributeValue();
        if (r2 == null){
            LOGGER.info("添加商品属性值***********");
            attributeValue.setAttributeId(r1);
            attributeValue.setValue(params[1]);
            adminMapper.saveAttributeValue(attributeValue);
            r2 = attributeValue.getId();
        }
        ProductAttributeRelation productAttributeRelation = new ProductAttributeRelation();
        productAttributeRelation.setProductId(2019050506);
        productAttributeRelation.setAttributeId(r1);
        productAttributeRelation.setValueId(r2);
        productAttributeRelation.setStatus(1);
        productAttributeRelation.setCreate(new Date());
        int result = adminMapper.saveProductAttributeValueRelation(productAttributeRelation);
    }

    public void saveCategoryAttributeRelation(CategoryAttribute categoryAttributeRelation){
        Integer result = adminMapper.saveCategoryAttributeRelation(categoryAttributeRelation);
    }
}
