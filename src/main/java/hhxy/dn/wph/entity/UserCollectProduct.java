package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 23:10 2019/5/3
 */
//用户收藏商品实体类
public class UserCollectProduct {
    private Integer user_id;
    private Integer product_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
}
