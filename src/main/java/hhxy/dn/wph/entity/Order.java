package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 邓宁
 * @Date: Created in 14:01 2019/5/3
 */
//订单实体类
public class Order implements Serializable {

    private Integer order_no;//自动编号
    private String order_id;//订单编号
    private Integer user_id;//用户ID
    private Integer address_id;//用户地址ID
    private Double product_total;//商品总价
    private Integer order_count;//订单商品数量
    private Integer pay_channel;//订单支付渠道
    private String pay_no;//第三方支付流水号
    private Integer status;//订单状态
    private String created;//创建时间

    private List<GoodCart> goodCartList;//商品详情

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public Double getProduct_total() {
        return product_total;
    }

    public void setProduct_total(Double product_total) {
        this.product_total = product_total;
    }

    public Integer getOrder_count() {
        return order_count;
    }

    public void setOrder_count(Integer order_count) {
        this.order_count = order_count;
    }

    public Integer getPay_channel() {
        return pay_channel;
    }

    public void setPay_channel(Integer pay_channel) {
        this.pay_channel = pay_channel;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
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

    public List<GoodCart> getGoodCartList() {
        return goodCartList;
    }

    public void setGoodCartList(List<GoodCart> goodCartList) {
        this.goodCartList = goodCartList;
    }
}
