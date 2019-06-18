package hhxy.dn.wph.entity;

import java.io.Serializable;
//类目属性
public class CategoryAttribute implements Serializable {
    private Integer id;

    private Integer categoryId;//类目ID

    private Integer attributeId;//属性ID

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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