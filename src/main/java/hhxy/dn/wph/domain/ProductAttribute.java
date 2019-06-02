package hhxy.dn.wph.domain;

import hhxy.dn.wph.util.DateUtil;

import java.io.Serializable;
import java.util.List;

public class ProductAttribute implements Serializable {
    private Integer attrId;

    private String attrName;

    private int isSearch;

    private int status;

    private Integer adminId;

    private String createdTime;

    private List<ProductAttributeValue> attributeValues;

    private static final long serialVersionUID = 1L;

    public ProductAttribute() {
        this.isSearch = 1;
        this.status = 1;
        this.adminId = 1;
        this.createdTime = DateUtil.getDate();
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<ProductAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<ProductAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }
}