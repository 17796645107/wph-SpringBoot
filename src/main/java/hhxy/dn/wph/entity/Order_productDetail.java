package hhxy.dn.wph.entity;

import java.io.Serializable;

/**
 * @Author: 邓宁
 * @Date: Created in 14:11 2019/5/3
 */
//订单商品详情
public class Order_productDetail implements Serializable {

    private Integer detail_id;//自动编号
    private Integer product_id;//商品ID
    private Integer order_id;//订单编号
    private Integer product_number;//商品数量
    private Integer product_size;//商品尺寸
    private Integer product_color;//商品颜色
    private Product product;

    public Integer getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(Integer detail_id) {
        this.detail_id = detail_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_number() {
        return product_number;
    }

    public void setProduct_number(Integer product_number) {
        this.product_number = product_number;
    }

    public Integer getProduct_size() {
        return product_size;
    }

    public void setProduct_size(Integer product_size) {
        this.product_size = product_size;
    }

    public Integer getProduct_color() {
        return product_color;
    }

    public void setProduct_color(Integer product_color) {
        this.product_color = product_color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
