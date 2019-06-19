package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author: 邓宁
 * @Date: Created in 21:49 2018/11/4
 * 商品属性与属性值对应关系
 */
public class ProductAttributeRelation implements Serializable {
    /**
     *
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 属性ID
     */
    private Integer attributeId;
    /**
     * 属性值ID
     */
    private Integer valueId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 更新时间
     */
    private String updated;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}