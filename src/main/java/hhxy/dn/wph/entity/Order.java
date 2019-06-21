package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 14:01 2019/5/3
 * 订单实体类
 */

public class Order implements Serializable {
    /**
     * 订单编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户地址ID
     */
    private Integer addressId;
    /**
     * 商品总价
     */
    private Double productTotal;
    /**
     * 订单商品数量
     */
    private Integer orderCount;
    /**
     * 订单支付渠道
     */
    private Integer payChannel;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 第三方支付流水号
     */
    private String payNo;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 商品详情
     */
    private List<Cart> goodCartList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Double getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(Double productTotal) {
        this.productTotal = productTotal;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Cart> getGoodCartList() {
        return goodCartList;
    }

    public void setGoodCartList(List<Cart> goodCartList) {
        this.goodCartList = goodCartList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", productTotal=" + productTotal +
                ", orderCount=" + orderCount +
                ", payChannel=" + payChannel +
                ", status=" + status +
                ", payNo='" + payNo + '\'' +
                ", created='" + created + '\'' +
                ", goodCartList=" + goodCartList +
                '}';
    }
}
