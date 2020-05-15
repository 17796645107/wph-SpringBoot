package hhxy.dn.wph.entity;

import java.util.Date;

/**
 * @author 邓宁
 * @date Created in 23:08 2018/11/21
 * 用户搜索记录类
 */
public class UserSearch {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 搜索关键词
     */
    private String searchTitle;
    /**
     *
     */
    private String created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "UserSearch{" +
                "id=" + id +
                ", userId=" + userId +
                ", searchTitle='" + searchTitle + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
