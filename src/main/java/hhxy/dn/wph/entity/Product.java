package hhxy.dn.wph.entity;

/**
 * @author 邓宁
 * @date Created in 12:45 2018/11/16
 * 商品基本信息表
 */
public class Product {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 二级分类ID
     */
    private Integer categoryId;
    /**
     * 商户ID
     */
    private Integer sellerId;
    /**
     * 品牌ID
     */
    private Integer brandId;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品描述
     */
    private String detail;
    /**
     * 商品价格
     */
    private Integer price;
    /**
     * 商品收藏量
     */
    private Integer collect;
    /**
     * 是否热门商品。1：是；0：否
     */
    private Integer isHot;
    /**
     * 是否新品。1：是；0：否
     */
    private Integer isNew;
    /**
     * 状态。1：正常；0：删除
     */
    private Integer state;
    /**
     * 创建时间
     */
    private String created;

    /**
     * 商品默认展示的图片
     */
    private ProductImage defaultImage;
    /**
     * 所属商户
     */
    private Seller seller;


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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
                ", created=" + created +
                ", defaultImage=" + defaultImage +
                ", seller=" + seller +
                '}';
    }
}
