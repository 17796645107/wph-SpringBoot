package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @author 邓宁
 * @date Created in 12:16 2018/11/30
 * 商品库存
 */
public class ProductNum {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 商品颜色
     */
    private String productColor;
    /**
     * 商品尺寸
     */
    private String productSize;
    /**
     * 库存值
     */
    private Integer num;
    /**
     * 创建时间
     */
    private String created;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
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
