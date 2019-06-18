package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.io.Serializable;
import java.util.Date;
//商品属性值
public class ProductAttributeValue implements Serializable {
    private Integer id;

    private String value;//属性值

    private Integer status;

    private Integer attributeId;//属性ID

    private Date create;

    public ProductAttributeValue() {
        this.status = 1;
    }

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

}