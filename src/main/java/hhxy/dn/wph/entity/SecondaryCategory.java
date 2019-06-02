package hhxy.dn.wph.entity;

/**
 * @Author: 邓宁
 * @Date: Created in 10:40 2018/11/30
 */
//商品二级分类目录
public class SecondaryCategory {

    private Integer secondary_id;//编号
    private Integer parent_id;//父目录编号
    private String name;//目录名称
    private Integer sort_number;//目录显示顺序。数字越大，名称越靠后
    private Integer status;//状态。1：正常，0：删除

    public Integer getSecondary_id() {
        return secondary_id;
    }

    public void setSecondary_id(Integer secondary_id) {
        this.secondary_id = secondary_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
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
