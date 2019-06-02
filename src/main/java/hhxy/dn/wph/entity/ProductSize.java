package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

/**
 * @Author: 邓宁
 * @Date: Created in 12:15 2018/11/30
 */
//商品尺寸表
public class ProductSize {

    private Integer size_id;//编号
    private Integer product_id;//商品编号
    private String size;//商品尺寸
    private Integer status;//状态。1：正常，0：删除
    private String created;
    private String updated;

    //初始化操作
    public ProductSize(){
        this.created = DateUtil.getDate();
        this.status = 1;//默认为1：正常
    }

    public Integer getSize_id() {
        return size_id;
    }

    public void setSize_id(Integer size_id) {
        this.size_id = size_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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
}
