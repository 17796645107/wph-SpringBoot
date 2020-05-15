package hhxy.dn.wph.entity;

import java.io.Serializable;
/**
 * @author 邓宁
 * @date Created in 16:27 2019/4/7
 * 商户账户
 */
public class SellerAccount implements Serializable {
    /**
     *
     */
    private Integer sellerId;
    /**
     *
     */
    private String sellerUsername;
    /**
     *
     */
    private String sellerPassword;
    /**
     *
     */
    private String created;
    /**
     *
     */


    private static final long serialVersionUID = 1L;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername == null ? null : sellerUsername.trim();
    }

    public String getSellerPassword() {
        return sellerPassword;
    }

    public void setSellerPassword(String sellerPassword) {
        this.sellerPassword = sellerPassword == null ? null : sellerPassword.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


}