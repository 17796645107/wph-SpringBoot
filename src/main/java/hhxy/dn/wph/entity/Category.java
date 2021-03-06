package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 商品类目
 * @author 邓宁宁
 * @date Created in 2019/6/19
 */
public class Category implements Serializable {
    /**
     * ID
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
    private Integer state;
    /**
     * 创建时间
     */
    private String created;
    /**
     * 子节点
     */
    private List<Category> children;

    /**
     * 统计此类目的商品数量
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
