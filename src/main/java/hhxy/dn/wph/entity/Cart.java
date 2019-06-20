package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author: 邓宁
 * @Date: Created in 13:39 2018/11/12
 * 购物车类
 */
public class Cart implements Serializable {
    /**
     * @Description 主键
     */
    private Integer id;
    /**
     * @Description 用户ID
     */
    private Integer userId;
    /**
     * @Description 商品ID
     */
    private Integer productId;
    /**
     * @Description 商品数量
     */
    private Integer productNumber;
    /**
     * @Description 商品颜色
     */
    private String productColor;
    /**
     * @Description 商品尺寸
     */
    private String productSize;
    /**
     * @Description 状态
     */
    private Integer status;
    /**
     * @Description 创建时间
     */
    private String created;
    /**
     * @Description 更新时间
     */
    private String updated;
    /**
     * @Description 商品实体类,一对一
     */
    private Product product;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor == null ? null : productColor.trim();
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize == null ? null : productSize.trim();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productNumber=" + productNumber +
                ", productColor='" + productColor + '\'' +
                ", productSize='" + productSize + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", product=" + product +
                '}';
    }
}