package in2021winter.com.huanghai.dao;

import in2021winter.com.huanghai.domain.User;

import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/28 12:50
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 保存操作
     */
    void saveUser(User user);


    /**
     * 修改用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     */
    void deleteUser(Integer id);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
     User findById(Integer id);

    /**
     * 模糊查询
     * @param username
     * @return
     */
     List<User> findByName(String username);


    /**
     * 查询用户数量
     * @return
     */
    int findTotal();


    /**
     * 用于测试动态sql
     * @param user
     * @return
     */
    List<User> findByCondition(User user);


    /**
     * in关键字的范围查询
     * @param integer
     * @return
     */
    List<User> findByAround(Integer integer);

    /**
     * 用于测试一对多，一个用户有多个订单
     * @return
     */
    List<User> findAllAccount();


}
