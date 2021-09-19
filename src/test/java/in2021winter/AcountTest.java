package in2021winter;

import in2021winter.com.huanghai.dao.IAccountDao;
import in2021winter.com.huanghai.dao.IUserDao;
import in2021winter.com.huanghai.domain.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/28 20:09
 */
public class AcountTest {
    InputStream in;
    SqlSession sqlSession;
    IAccountDao accountDao;

    @Before  //用于所有测试方法之前
    public void init(){
        //1.读取配置文件
        in = UserTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlsessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlsession对象
        sqlSession = factory.openSession(true);
        //4.使用sqlsession对象创建dao接口的代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After  //用于所有测试方法之后
    public void destroy() throws Exception{
        //6.释放资源
        sqlSession.close();
        in.close();
    }


    @Test
    public void testFindAll(){
       List<Account> accounts= accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
