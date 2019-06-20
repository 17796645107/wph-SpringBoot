package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 12:15 2018/11/30
 * 商品尺寸表
 */
public class ProductSize {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 商品尺寸
     */
    private String size;
    /**
     * 状态。1：正常，0：删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 更新时间
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
        return "ProductSize{" +
                "id=" + id +
                ", productId=" + productId +
                ", size='" + size + '\'' +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }
}
