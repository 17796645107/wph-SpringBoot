package hhxy.dn.wph.entity;

import java.util.List;

/**
 * 项目名称: hhxy.dn.wph.entity
 * 文件名称: TreeItem.java
 * 描述: 级联选择器树形结构
 * 创建时间: 2020/3/27 16:35
 *
 * @author 邓宁宁
 * @version 1.0
 */
public class TreeItem {
    private String value;
    private String label;
    private List<TreeItem> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItem> children) {
        this.children = children;
    }
}
