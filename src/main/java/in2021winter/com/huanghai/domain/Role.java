package in2021winter.com.huanghai.domain;

import java.io.Serializable;

/**
 * @author HuangHai
 * @date 2021/1/29 14:50
 */
public class Role implements Serializable {

    private int id;
    private String name;
    private String desc;
    private Student student;  //一对一映射（一个身份，角色对应一个学生）

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
