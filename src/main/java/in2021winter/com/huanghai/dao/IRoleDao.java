package in2021winter.com.huanghai.dao;

import in2021winter.com.huanghai.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author HuangHai
 * @date 2021/1/29 14:29
 */
@CacheNamespace(blocking = true)  //开启该Dao的二级缓存
public interface IRoleDao {


    /**查询所有
     * 测试mybatis的注释配置
     * @return
     */
    @Select("select * from roles2")
    @Results(id = "roles2Map",value={
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "theName"),
            @Result(property = "desc",column = "theDesc"),
            @Result(property = "student",column = "id",one = @One(select = "in2021winter.com.huanghai.dao.IStudentDao.findById",fetchType = FetchType.EAGER))
            //上面那个result是一对一映射的立即加载
    })
    List<Role> findAll();

    /**
     * 模糊查询
     * @param name
     * @return
     */
    @Select("select * from roles2 where theName like #{name}")
    @ResultMap("roles2Map")
    List<Role> findByName(String name);


    /**
     * 查询一个
     * @param id
     * @return
     */
    @Select("select * from roles2 where id=#{id}")
    @ResultMap("roles2Map")
    Role findById(Integer id);


    /**
     * 修改
     * @param role
     */
    @Update("update roles2 set theName=#{name},theDesc=#{desc} where id=#{id}")
    @ResultMap("roles2Map")
    void updateRole(Role role);


    /**
     * 添加
     * @param role
     */
    @Insert("insert into roles2(theName,theDesc) values(#{name},#{desc})")
    @ResultMap("roles2Map")
    void insertRole(Role role);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from roles2 where id=#{id}")
    @ResultMap("roles2Map")
    void deleteRole(Integer id);
}


