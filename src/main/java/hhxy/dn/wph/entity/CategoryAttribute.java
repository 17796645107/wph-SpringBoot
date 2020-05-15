package hhxy.dn.wph.entity;

import java.io.Serializable;
/**
 * @author 邓宁
 * @date Created in 13:39 2018/11/12
 * 类目属性类
 */
public class CategoryAttribute implements Serializable {
    /**
     *  主键
     */
    private Integer id;
    /**
     *  类目ID
     */
    private Integer categoryId;
    /**
     *  属性ID
     */
    private Integer attributeId;

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