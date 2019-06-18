package hhxy.dn.wph.entity;

import java.io.Serializable;
//支付渠道
public class OrderChanner implements Serializable {
    private Integer id;

    private Integer payChanner;//支付渠道

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPayChanner() {
        return payChanner;
    }

    public void setPayChanner(Integer payChanner) {
        this.payChanner = payChanner;
    }
}