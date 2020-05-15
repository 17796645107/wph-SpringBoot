package hhxy.dn.wph.entity; /**
 * @Author 邓宁
 * @Date Created in 21:25 2019/6/30
 *
 */

/**
 *   @author 邓宁
 *   @date Created in 21:25 2019/6/30
 *   @Describe: 查询商品条件
 */
public class ProductSelectCondition {
    /**
     * 商户ID
     */
    private Integer sellerId;
    /**
     * 分类ID
     */
    private Integer categoryId;
    /**
     * 商品尺寸ID
     */
    private Integer productSizeId;
    /**
     * 排序类型 1:按价格排序,2:按折扣排序
     */
    private Integer type;
    /**
     * 是否有货 1:有货,2:库存为0
     */
    private Integer hasNum;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(Integer productSizeId) {
        this.productSizeId = productSizeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHasNum() {
        return hasNum;
    }

    public void setHasNum(Integer hasNum) {
        this.hasNum = hasNum;
    }
}
