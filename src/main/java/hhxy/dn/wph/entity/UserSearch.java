package hhxy.dn.wph.entity;

import java.util.Date;

/**
 * @Author: 邓宁
 * @Date: Created in 23:08 2018/11/21
 */
//用户搜索记录
public class UserSearch {
    private Integer id;//编号
    private Integer user_id;//用户ID
    private String search_title;//搜索词
    private Date create;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getSearch_title() {
        return search_title;
    }

    public void setSearch_title(String search_title) {
        this.search_title = search_title;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}
