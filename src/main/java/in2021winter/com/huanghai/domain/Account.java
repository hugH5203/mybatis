package in2021winter.com.huanghai.domain;

import java.io.Serializable;

/**
 * @author HuangHai
 * @date 2021/1/28 20:00
 */
public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double money;
    private User user;  //一对一映射

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
