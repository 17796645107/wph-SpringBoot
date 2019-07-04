package hhxy.dn.wph.entity;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 14:15 2019/5/3
 * 订单状态
 */
public class OrderState {
    private Integer id;
    private String state;
    private Date create;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}
