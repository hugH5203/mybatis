package in2021winter;

import in2021winter.com.huanghai.dao.IRoleDao;
import in2021winter.com.huanghai.dao.IUserDao;
import in2021winter.com.huanghai.domain.Role;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/29 14:53
 */
public class RoleTest {/*

    InputStream in;
    SqlSession sqlSession;
    IRoleDao roleDao;

    @Before  //用于所有测试方法之前
    public void init(){
        //1.读取配置文件
        in = UserTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlsessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlsession对象
        sqlSession = factory.openSession(true);//参数为true的意思是，事务自动提交
        //4.使用sqlsession对象创建dao接口的代理对象
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After  //用于所有测试方法之后
    public void destroy() throws Exception{
        //6.释放资源
        sqlSession.close();
        in.close();
    }


    @Test   //测试注解方式查询
    public void testFindAll(){     //一对一查询，立即加载，所以即使不查询getStudent还是会执行ById的方法
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println(role);
            System.out.println(role.getStudent());
            System.out.println("-------------");
        }
    }

    @Test
    public void testFindByName(){
        List<Role> roles = roleDao.findByName("%长%");
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void testFindById(){
        Role roleDaoById = roleDao.findById(10);
        System.out.println(roleDaoById);
    }


    @Test
    public void testUpdate(){
        Role role = new Role();
        role.setId(10);
        role.setName("园长");
        role.setDesc("管理动物园");
        roleDao.updateRole(role);
    }


    @Test
    public void testDelete(){
        roleDao.deleteRole(6);
    }


    @Test
    public void tesetInsert(){
        Role role = new Role();
        role.setName("社长");
        role.setDesc("管理社团");
        roleDao.insertRole(role);
    }*/


    @Test
    public void testSecondLevelCache() throws IOException {   //测试一下mybatis的二级缓存,测试之前注释掉before和after的代码与成员变量
        InputStream in = UserTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlsessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlsession对象
        SqlSession sqlSession= factory.openSession(true);
        //4.使用sqlsession对象创建dao接口的代理对象
        IRoleDao roleDao= sqlSession.getMapper(IRoleDao.class);
        List<Role> roles1 = roleDao.findAll();
        for (Role role : roles1) {
            System.out.println(role);
        }
        System.out.println("-----------------");
        //清理sqlsession的缓冲区(直接关掉!)
        sqlSession.close();

        SqlSession sqlSession2= factory.openSession(true);
        //4.使用sqlsession对象创建dao接口的代理对象
        IRoleDao roleDao2= sqlSession2.getMapper(IRoleDao.class);
        List<Role> roles2 = roleDao2.findAll();
        for (Role role : roles2) {
            System.out.println(role);
        }
        System.out.println("----------------------------------------------------");
        System.out.println(roles1==roles2);//false
        System.out.println(roles1.hashCode());//-244163587
        System.out.println(roles2.hashCode());//   697489315
        //判断两个对象是不是一样的，应该地址不一样，但是内容数据是相同的。因为一级缓存是按对象存在sqlsession中，
        //而二级缓存是按数据存在sqlsessionFactory中,在log4j中可以看到，select * from roles2 语句只执行了一遍，另一个重复的语句是因为配置了一对一映射
        sqlSession2.close();
        in.close();




    }
}
