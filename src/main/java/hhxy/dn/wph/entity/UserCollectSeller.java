package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 23:08 2019/5/3
 */
//用户关注商家实体类
public class UserCollectSeller {
    private Integer user_no;
    private Integer seller_no;
    private Seller seller;

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public Integer getSeller_no() {
        return seller_no;
    }

    public void setSeller_no(Integer seller_no) {
        this.seller_no = seller_no;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
