package hhxy.dn.wph.entity;

import java.io.Serializable;
/**
 *  URL资源类
 * @date 2019/6/22
 * @author 邓宁
 */
public class Resource implements Serializable {
    private Integer id;
    /**
     *  URL路径
     */
    private String url;
    /**
     *  资源名称
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