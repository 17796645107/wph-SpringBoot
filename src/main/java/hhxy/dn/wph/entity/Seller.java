package hhxy.dn.wph.entity;

import hhxy.dn.wph.domain.Brand;
import hhxy.dn.wph.util.DateUtil;

/**
 * @Author: 邓宁
 * @Date: Created in 16:19 2019/4/7
 */
//商户信息表
public class Seller {
    private Integer seller_id;
    private String name;//店铺名称
    private String show_image;//店铺展示图
    private Integer type;//店铺类型 category_id
    private Integer status;
    private String created;
    private String updated;
    private Brand brand;//品牌

    public Seller() {
        this.status = 1;
        this.created = DateUtil.getDate();
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getShow_image() {
        return show_image;
    }

    public void setShow_image(String show_image) {
        this.show_image = show_image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
