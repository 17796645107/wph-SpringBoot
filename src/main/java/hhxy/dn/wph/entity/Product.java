package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 12:45 2018/11/16
 */
//商品基本信息表
public class Product {
    private Integer id;//商品ID(主键)
    private String productNo;
    private Integer categoryId;//二级分类ID
    private Integer sellerId;//商户ID
    private Integer brandId;//品牌ID
    private String title;//商品名称
    private String detail;//商品描述
    private Integer price;//商品价格
    private Integer collect;//商品收藏量
    private Integer isHot;//是否热门商品。1：是；0：否
    private Integer isNew;//是否新品。1：是；0：否
    private Integer status;//状态。1：正常；0：删除
    private Date create;//创建时间
    private Date updated;//更新时间
    private ProductImage defaultImage;//商品默认展示的图片
    private Seller seller;//所属商户


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public ProductImage getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(ProductImage defaultImage) {
        this.defaultImage = defaultImage;
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
                "id=" + id +
                ", productNo='" + productNo + '\'' +
                ", categoryId=" + categoryId +
                ", sellerId=" + sellerId +
                ", brandId=" + brandId +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", collect=" + collect +
                ", isHot=" + isHot +
                ", isNew=" + isNew +
                ", status=" + status +
                ", create=" + create +
                ", updated=" + updated +
                ", defaultImage=" + defaultImage +
                ", seller=" + seller +
                '}';
    }
}
