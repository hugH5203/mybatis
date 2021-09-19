package in2021winter.com.huanghai.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/29 17:57
 */
public class Student implements Serializable {
    private int id;
    private  String theName;
    private  String theSex;
    private String theSno;
    private String theClassName;
    private List<Role> roles;  //一对多映射，一个学生长大后有多种角色

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheName() {
        return theName;
    }

    public void setTheName(String theName) {
        this.theName = theName;
    }

    public String getTheSex() {
        return theSex;
    }

    public void setTheSex(String theSex) {
        this.theSex = theSex;
    }

    public String getTheSno() {
        return theSno;
    }

    public void setTheSon(String theSon) {
        this.theSno = theSon;
    }

    public String getTheClassName() {
        return theClassName;
    }

    public void setTheClassName(String theClassName) {
        this.theClassName = theClassName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", theName='" + theName + '\'' +
                ", theSex='" + theSex + '\'' +
                ", theSon='" + theSno + '\'' +
                ", theClassName='" + theClassName + '\'' +
                '}';
    }
}
