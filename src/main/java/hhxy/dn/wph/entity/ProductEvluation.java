package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
//商品评论表
public class ProductEvluation implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private String context;//评论内容

    private String state;//状态

    private Date create;//创建时间

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getStatus() {
        return state;
    }

    public void setStatus(String status) {
        this.state = status == null ? null : status.trim();
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}