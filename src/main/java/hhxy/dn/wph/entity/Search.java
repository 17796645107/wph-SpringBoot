package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 23:08 2018/11/21
 */
//搜索记录
public class Search {
    private Integer search_id;//编号
    private Integer user_no;//用户ID
    private String search_title;//搜索词

    public Integer getSearch_id() {
        return search_id;
    }

    public void setSearch_id(Integer search_id) {
        this.search_id = search_id;
    }

    public Integer getUser_no() {
        return user_no;
    }

    public void setUser_no(Integer user_no) {
        this.user_no = user_no;
    }

    public String getSearch_title() {
        return search_title;
    }

    public void setSearch_title(String search_title) {
        this.search_title = search_title;
    }
}
