package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date Created by sang on 2017/12/28.
 * @author 邓宁宁
 * 角色类
 */
public class Role implements Serializable {
    private Integer id;
    /**
     *  角色名称
     */
    private String name;
    /**
     *  角色
     */
    private String nameZh;
    /**
     *  创建时间
     */
    private String created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
