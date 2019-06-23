package hhxy.dn.wph.entity;

import java.io.Serializable;
/**
 * @Description URL资源类
 * @Date: 2019/6/22
 * @Author: 邓宁
 */
public class Resource implements Serializable {
    private Integer id;
    /**
     * @Description URL路径
     */
    private String url;
    /**
     * @Description 资源名称
     */
    private String resourceName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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