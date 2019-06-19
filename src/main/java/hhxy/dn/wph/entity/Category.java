package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
/**
 *   @Author 邓宁
 *   @Date Created in 2019/6/19
 *   @Description 商品类目
 */
public class Category implements Serializable {
    /**
     *
     */
    private Integer id;
    /**
     * 类目名称
     */
    private String categoryName;
    /**
     * 类目排序
     */
    private Integer categorySort;
    /**
     * 父ID
     */
    private Integer parentId;
    /**
     * 管理员ID
     */
    private Integer adminId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 更新时间
     */
    private String updated;
    /**
     * 统计此类目商品数量
     */
    private Integer productCount;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(Integer categorySort) {
        this.categorySort = categorySort;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}