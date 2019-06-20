package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author: 邓宁
 * @Date: Created in 21:49 2018/11/4
 * 商品属性值
 */
public class ProductAttributeValue implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 属性值
     */
    private String value;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 属性ID
     */
    private Integer attributeId;
    /**
     * 创建时间
     */
    private String created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "ProductAttributeValue{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", status=" + status +
                ", attributeId=" + attributeId +
                ", created='" + created + '\'' +
                '}';
    }
}