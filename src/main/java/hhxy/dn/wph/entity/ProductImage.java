package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @author 邓宁
 * @date Created in 19:41 2019/4/8
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
    private String productId;
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



    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", image='" + image + '\'' +
                ", colorId=" + colorId +
                ", status=" + status +
                ", created='" + created + '\'' +

                '}';
    }
}
