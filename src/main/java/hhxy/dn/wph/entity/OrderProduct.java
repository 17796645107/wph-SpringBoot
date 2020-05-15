package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 邓宁
 * @date Created in 14:11 2019/5/3
 * 订单商品详情
 */
public class OrderProduct implements Serializable {

    /**
     * 自动编号
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 商品数量
     */
    private Integer productNumber;
    /**
     * 商品尺寸
     */
    private Integer productSize;
    /**
     * 商品颜色
     */
    private Integer productColor;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 商品类,一对一
     */
    private Product product;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getProductSize() {
        return productSize;
    }

    public void setProductSize(Integer productSize) {
        this.productSize = productSize;
    }

    public Integer getProductColor() {
        return productColor;
    }

    public void setProductColor(Integer productColor) {
        this.productColor = productColor;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
