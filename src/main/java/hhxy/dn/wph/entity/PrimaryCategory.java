package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 22:02 2018/11/29
 */
//一级商品目录
public class PrimaryCategory {
    private Integer primary_id;//编号
    private String name;//目录名称
    private Integer sort_number;//目录显示顺序。数字越大，名称越靠后
    private Integer status;//状态。1：正常，0：删除

    public Integer getPrimary_id() {
        return primary_id;
    }

    public void setPrimary_id(Integer primary_id) {
        this.primary_id = primary_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort_number() {
        return sort_number;
    }

    public void setSort_number(Integer sort_number) {
        this.sort_number = sort_number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
