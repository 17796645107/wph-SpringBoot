package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 23:08 2019/5/3
 * 用户关注商家实体类
 */
public class UserCollectSeller {
    private Integer userId;
    private Integer sellerId;
    private Seller seller;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
