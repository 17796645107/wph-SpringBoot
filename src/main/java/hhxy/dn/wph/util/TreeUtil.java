package hhxy.dn.wph.util;

import hhxy.dn.wph.entity.TreeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目名称: hhxy.dn.wph.util
 * 文件名称: TreeUtil.java
 * 描述: 转换树形结构
 * 创建时间: 2020/3/27 16:37
 *
 * @author 邓宁宁
 * @version 1.0
 */
public class TreeUtil {
    static class TreeItem {
        private Integer id;

        private Integer parentId;

        private String name;

        private List<TreeItem> children;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TreeItem> getChildren() {
            return children;
        }

        public void setChildren(List<TreeItem> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "TreeItem{" +
                    "id=" + id +
                    ", parentId=" + parentId +
                    ", name='" + name + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    static class Department {
        private Integer id;

        private Integer parentId;

        private String name;

        public Department(Integer id, Integer parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        public static TreeItem deptNode(Department dept) {
            TreeItem treeItem = new TreeItem();
            treeItem.setId(dept.getId());
            treeItem.setName(dept.getName());
            treeItem.setParentId(dept.getParentId());
            return treeItem;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Employee {

        private Integer id;

        private String name;

        private Integer deptId;


        public Employee(Integer id, String name, Integer deptId) {
            this.id = id;
            this.name = name;
            this.deptId = deptId;
        }

        public static TreeItem empNode(Employee emp) {
            TreeItem treeItem = new TreeItem();
            treeItem.setId(emp.getId());
            treeItem.setName(emp.getName());
            treeItem.setParentId(emp.getDeptId());
            return treeItem;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getDeptId() {
            return deptId;
        }

        public void setDeptId(Integer deptId) {
            this.deptId = deptId;
        }
    }

    public static void main(String[] args) {
            //创建部门
            List<Department> deptList = Arrays.asList(
                    new Department(1, 0, "星宇"),
                    new Department(2, 1, "金3"),
                    new Department(3, 2, "金3/1"),
                    new Department(4, 2, "金3/2"),
                    new Department(5, 1, "总1"),
                    new Department(6, 5, "总1/2")

            );
            //创建员工
            List<Employee> empList = Arrays.asList(
                    new Employee(1, "张三", 5),
                    new Employee(2, "李四", 6),
                    new Employee(3, "王五", 2),
                    new Employee(4, "赵六", 2),
                    new Employee(5, "田七", 3),
                    new Employee(6, "牛八", 4)
            );
            //转换成结点
            List<TreeItem> allDept = deptList.stream()
                    .map(Department::deptNode)
                    .collect(Collectors.toList());
            //按照上级部门分组
            Map<Integer, List<TreeItem>> deptMap = allDept.stream()
                    .collect(Collectors.groupingBy(TreeItem::getParentId));
            //给每个部门绑定子部门
            allDept.forEach(node ->
                    node.setChildren(deptMap.get(node.getId()))
            );

            List<TreeItem> treeItem = deptMap.get(0);

            Map<Integer, List<TreeItem>> empMap = empList.stream()
                    .filter(emp -> emp.getDeptId() != null)
                    .map(Employee::empNode)
                    .collect(Collectors.groupingBy(TreeItem::getParentId));
            //BFS辅助队列
            List<TreeItem> queue = new ArrayList<>();
            queue.addAll(treeItem);
            for (int i = 0; i < queue.size(); i++) {
                TreeItem node = queue.get(i);
                //遍历时先将子部门放入队列中
                if (node.getChildren() != null) {
                    queue.addAll(node.getChildren());
                } else {
                    node.setChildren(new ArrayList<>());
                }
                //再将部门成员放入子结点中
                List<TreeItem> children = empMap.get(node.getId());
                if (children != null) {
                    node.getChildren().addAll(children);
                }
            }

            treeItem.forEach(x -> System.out.println(x));

    }
}
