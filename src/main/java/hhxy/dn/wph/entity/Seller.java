package hhxy.dn.wph.entity;

import hhxy.dn.wph.util.DateUtil;

/**
 * @author 邓宁
 * @date Created in 16:19 2019/4/7
 */
//商户信息表
public class Seller {
    private String id;//商户ID
    private String sellerNo;//自动编号
    private String name;//店铺名称
    private String showImage;//店铺展示图
    private Integer type;//店铺类型 category_id
    private Integer state;//状态
    private String created;//创建时间

    private Brand brand;//品牌

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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



    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id='" + id + '\'' +
                ", sellerNo='" + sellerNo + '\'' +
                ", name='" + name + '\'' +
                ", showImage='" + showImage + '\'' +
                ", type=" + type +
                ", status=" + state +
                ", created='" + created + '\'' +

                ", brand=" + brand +
                '}';
    }
}
