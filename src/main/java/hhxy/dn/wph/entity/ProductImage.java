package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 19:41 2019/4/8
 * 商品图片类
 */
public class ProductImage {
    /**
     *
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 图片URL
     */
    private String image;
    /**
     * 颜色ID
     */
    private Integer colorId;
    /**
     * 状态
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
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
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", image='" + image + '\'' +
                ", colorId=" + colorId +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
