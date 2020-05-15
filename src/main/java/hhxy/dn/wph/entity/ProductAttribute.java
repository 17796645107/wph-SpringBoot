package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @author 邓宁
 * @date Created in 21:49 2018/11/4
 * 商品属性类
 */
public class ProductAttribute implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 属性名称
     */
    private String attrName;
    /**
     * 是否搜索
     */
    private int isSearch;
    /**
     * 状态
     */
    private int state;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 属性值列表
     */
    private List<ProductAttributeValue> attributeValues;

    private static final long serialVersionUID = 1L;



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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ProductAttributeValue> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<ProductAttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }

    @Override
    public String toString() {
        return "ProductAttribute{" +
                "id=" + id +
                ", attrName='" + attrName + '\'' +
                ", isSearch=" + isSearch +
                ", created='" + created + '\'' +
                ", attributeValues=" + attributeValues +
                '}';
    }
}