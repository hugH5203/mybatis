package in2021winter;

import in2021winter.com.huanghai.dao.IRoleDao;
import in2021winter.com.huanghai.dao.IStudentDao;
import in2021winter.com.huanghai.domain.Role;
import in2021winter.com.huanghai.domain.Student;
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
 * @date 2021/1/29 21:41
 */
public class StudentTest {
    InputStream in;
    SqlSession sqlSession;
    IStudentDao studentDao;

    @Before  //用于所有测试方法之前
    public void init(){
        //1.读取配置文件
        in = UserTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlsessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产sqlsession对象
        sqlSession = factory.openSession(true);
        //4.使用sqlsession对象创建dao接口的代理对象
        studentDao = sqlSession.getMapper(IStudentDao.class);
    }

    @After  //用于所有测试方法之后
    public void destroy() throws Exception{
        //6.释放资源
        //sqlSession.commit();//手动提交，但是前面有sqlSession = factory.openSession(true)，设置了自动提交
        sqlSession.close();
        in.close();
    }



    @Test
    public void testFindAll(){
        /*多对多映射查询，懒加载，即按需加载，因为在IRoleDao的findByid方法的封装结果用的是roles2Map,里面配置了一对一映射，
        所以查相关的student时会把一对一的ById方法也执行，解决方法是将IRoleDao的findByid方法的封装结果注释重新写一个@Results就好*/
            List<Student> studentList=studentDao.findAll();
        for (Student student: studentList) {
            System.out.println(student);
            System.out.println(student.getRoles());  //调用设置了需要懒加载的属性的get方法时，才会触发懒加载
            System.out.println("--------------------------------");
        }
    }
}
