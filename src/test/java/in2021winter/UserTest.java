package in2021winter;

import in2021winter.com.huanghai.dao.IUserDao;
import in2021winter.com.huanghai.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/28 13:16
 */
public class UserTest {
    InputStream in;
    SqlSession sqlSession;
    IUserDao userDao;

    @Before  //用于所有测试方法之前
    public void init(){
        //1.读取配置文件
        in = UserTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlsessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlsession对象
        sqlSession = factory.openSession(true);
        //4.使用sqlsession对象创建dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After  //用于所有测试方法之后
    public void destroy() throws Exception{
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     *测试查询操作
     * @throws
     */
    @Test
    public  void testFindAll(){
        //5.使用代理对象执行方法
        List<User> users1 = userDao.findAll();
        for (User user : users1) {  //测试sqlSession一级缓存(当使用增删改或者commit，clearCache,close等方法时会删除缓存,缓存区域为map的键值对结构)
            System.out.println(user);
  //一级缓存存储在sqlSession中，存储的是对象，所以他们对象的地址相同。二级缓存存储在sqlSessionFactory中，存储的是数据，所以拿出来赋值的对象地址是不相同的，但里面的内容相同

        }
        List<User> users2 = userDao.findAll();
        for (User user : users2) { //测试sqlSession一级缓存
            System.out.println(user);
        }
        System.out.println(users1==users2);//true
    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave(){
        User user =new User();
        user.setUseraddress("通城");
        user.setUserbirthday(new Date());
        user.setUsersex("女");
        user.setUsername("哈哈");
        userDao.saveUser(user);
    }


    @Test
    public void testUpdate(){
        User user =new User();
        user.setUserid(41);
        user.setUseraddress("通城的");
        user.setUserbirthday(new Date());
        user.setUsersex("女");
        user.setUsername("天下无双");
        userDao.updateUser(user);
    }


    @Test
    public void testDelete(){
        userDao.deleteUser(50);
    }


    @Test
    public void testFindById(){
      User user=  userDao.findById(45);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> userList=userDao.findByName("%王%");
        for (User user : userList) {
            System.out.println(user);
        }
    }


    @Test
    public void testFindTotal(){
        System.out.println(userDao.findTotal());
    }


    @Test
    public void testFindByCondition(){//动态语句测试
           User user=  new User();
           user.setUsername("小二王");
           user.setUsersex("男");
         List<User> users= userDao.findByCondition(user);
        for (User user3 : users) {
            System.out.println(user3);
        }
    }


    @Test
    public  void  testFindAllAccount(){
        List<User> users=userDao.findAllAccount();
        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getAccounts());
            System.out.println("------------");
        }
    }
}
