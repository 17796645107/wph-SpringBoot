package hhxy.dn.wph.domain;

import java.io.Serializable;

public class CategoryAttributeRelation implements Serializable {
    private Integer categoryId;

    private Integer attributeId;

    private static final long serialVersionUID = 1L;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }
}