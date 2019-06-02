package hhxy.dn.wph.domain;

import hhxy.dn.wph.util.DateUtil;

import java.io.Serializable;

public class ProductAttributeValue implements Serializable {
    private Integer valueId;

    private String value;

    private Integer status;

    private Integer attributeId;

    private String createdTime;

    public ProductAttributeValue() {
        this.status = 1;
        this.createdTime = DateUtil.getDate();
    }

    private static final long serialVersionUID = 1L;

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}