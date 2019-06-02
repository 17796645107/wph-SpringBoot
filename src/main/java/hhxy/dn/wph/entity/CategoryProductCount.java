package hhxy.dn.wph.entity;

import hhxy.dn.wph.domain.Category;

/**
 * @Author: 邓宁
 * @Date: Created in 23:00 2019/5/19
 */

public class CategoryProductCount extends Category {
    private Integer productCount;

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
