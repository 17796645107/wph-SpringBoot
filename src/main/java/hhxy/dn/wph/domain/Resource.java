package hhxy.dn.wph.domain;

import java.io.Serializable;

public class Resource implements Serializable {
    private Integer resourceId;

    private String url;

    private String resourceName;

    private static final long serialVersionUID = 1L;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }
}