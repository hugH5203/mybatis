package in2021winter.com.huanghai.dao;

import in2021winter.com.huanghai.domain.Role;
import in2021winter.com.huanghai.domain.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/29 21:05
 */
public interface IStudentDao {

@Select("select * from student where id=#{id}")
@Results(id = "studentMap",value = {
        @Result(id = true,property = "id",column = "id"),
        @Result(property = "theName",column = "name"),
        @Result(property = "theSex",column = "sex"),
        @Result(property = "theSno",column = "sno"),
        @Result(property = "theClassName",column = "classname"),
        @Result(property = "roles",column = "id",many = @Many(select = "in2021winter.com.huanghai.dao.IRoleDao.findById",fetchType = FetchType.LAZY))
        //上面那个是一对多映射的延迟加载
})
    Student findById(Integer id);


@Select("select * from student")
@ResultMap("studentMap")
    List<Student> findAll();
}
