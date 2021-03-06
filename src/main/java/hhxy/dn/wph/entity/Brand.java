package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @author 邓宁
 * @date Created in 13:39 2018/11/12
 * 品牌类
 */
public class Brand implements Serializable {
    /**
     *
     */
    private Integer id;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌Logo
     */
    private String brandIcon;
    /**
     * 创建时间
     */
    private String created;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandIcon() {
        return brandIcon;
    }

    public void setBrandIcon(String brandIcon) {
        this.brandIcon = brandIcon == null ? null : brandIcon.trim();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }



    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", brandIcon='" + brandIcon + '\'' +
                ", created='" + created + '\'' +

                '}';
    }
}