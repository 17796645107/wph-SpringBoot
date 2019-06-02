package hhxy.dn.wph.domain;

import java.io.Serializable;

public class Brand implements Serializable {
    private Integer brandId;

    private String brandName;

    private String brandIcon;

    private static final long serialVersionUID = 1L;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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
}