package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

/**
 * @Author: 邓宁
 * @Date: Created in 12:16 2018/11/30
 */

public class ProductNum {
    private Integer num_id;
    private Integer product_id;
    private String product_color;
    private String product_size;
    private Integer num;
    private String created;

    public ProductNum() {
        this.created = DateUtil.getDate();
    }

    public Integer getNum_id() {
        return num_id;
    }

    public void setNum_id(Integer num_id) {
        this.num_id = num_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
