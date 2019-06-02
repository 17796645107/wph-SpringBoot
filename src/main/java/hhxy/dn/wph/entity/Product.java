package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

/**
 * @Author: 邓宁
 * @Date: Created in 12:45 2018/11/16
 */
//商品基本信息表
public class Product {
    private Integer product_id;//商品ID(主键)
    private Integer category_id;//二级分类ID
    private Integer seller_id;//商户ID
    private Integer brand_id;//品牌ID
    private String title;//商品名称
    private String detail;//商品描述
    private Integer price;//商品价格
    private Integer collect;//商品收藏量
    private ProductImage default_image;//商品默认展示的图片
    private Integer is_hot;//是否热门商品。1：是；0：否
    private Integer is_new;//是否新品。1：是；0：否
    private Integer status;//状态。1：正常；0：删除
    private String created;//创建时间
    private Seller seller;//所属商户

    //初始化操作
    public Product(){
        this.collect = 0;
        this.is_hot = 0;
        this.is_new = 1;
        this.status = 1;
        this.created = DateUtil.getDate();
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

   public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public ProductImage getDefault_image() {
        return default_image;
    }

    public void setDefault_image(ProductImage default_image) {
        this.default_image = default_image;
    }

    public Integer getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(Integer is_hot) {
        this.is_hot = is_hot;
    }

    public Integer getIs_new() {
        return is_new;
    }

    public void setIs_new(Integer is_new) {
        this.is_new = is_new;
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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", category_id=" + category_id +
                ", seller_id=" + seller_id +
                ", brand_id=" + brand_id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", collect=" + collect +
                ", default_image=" + default_image +
                ", is_hot=" + is_hot +
                ", is_new=" + is_new +
                ", status=" + status +
                ", created='" + created + '\'' +
                ", seller=" + seller +
                '}';
    }
}
