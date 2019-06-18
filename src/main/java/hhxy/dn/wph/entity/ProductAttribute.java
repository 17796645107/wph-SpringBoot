package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
//商品属性类
public class ProductAttribute implements Serializable {
    private Integer id;

    private String attrName;//属性名称

    private int isSearch;//是否搜索

    private int status;

    private Date create;

    private List<ProductAttributeValue> attributeValues;//属性值列表

    private static final long serialVersionUID = 1L;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public int getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(int isSearch) {
        this.isSearch = isSearch;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProductAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<ProductAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }
}