package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @author 邓宁
 * @date Created in 12:15 2018/11/30
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
    private String productId;
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


    public ProductColor() {
        this.status = 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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



    @Override
    public String toString() {
        return "ProductColor{" +
                "id=" + id +
                ", productId=" + productId +
                ", color='" + color + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +

                '}';
    }
}
