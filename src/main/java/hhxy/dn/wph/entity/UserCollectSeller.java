package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 23:08 2019/5/3
 */
//用户关注商家实体类
public class UserCollectSeller {
    private Integer user_id;
    private Integer seller_id;
    private Seller seller;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
