package in2021winter.com.huanghai.dao;

import in2021winter.com.huanghai.domain.Account;
import in2021winter.com.huanghai.domain.User;

import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/28 20:03
 */
public interface IAccountDao {

    /**
     *用于测试一对一，一个订单属于一个用户
     * @return
     */
    List<Account> findAll();  //测试一对一映射

    /**
     * 用于测试懒加载即延迟加载
     * @param id
     * @return
     */
    Account findByUid(Integer id);
}
