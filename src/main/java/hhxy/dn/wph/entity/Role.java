package hhxy.dn.wph.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sang on 2017/12/28.
 */
//角色
public class Role implements Serializable {
    private Integer id;
    private String name;//角色名称
    private String nameZh;//角色
    private Date create;

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
