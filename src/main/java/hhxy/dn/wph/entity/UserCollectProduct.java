package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 23:10 2019/5/3
 */
//用户收藏商品实体类
public class UserCollectProduct {
    private Integer id;
    private Integer userId;
    private Integer productId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
