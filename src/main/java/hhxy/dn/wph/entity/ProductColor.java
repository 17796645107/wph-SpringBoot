package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 12:15 2018/11/30
 * 商品颜色
 */
public class ProductColor {
    /**
     *
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 颜色
     */
    private String color;
    /**
     *
     */
    private Integer status;
    /**
     *
     */
    private String created;
    /**
     *
     */
    private String updated;

    public ProductColor() {
        this.status = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "ProductColor{" +
                "id=" + id +
                ", productId=" + productId +
                ", color='" + color + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
