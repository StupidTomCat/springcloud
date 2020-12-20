package com.yyh.springcloud.dao;

import com.yyh.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao {

    /*pom文件导入的依赖 所以可以获取Dept类*/
    @Insert("insert into dept(dname,db_source) values (#{dname},DATABASE());")
    public boolean addDept(Dept dept);

    @Select("select * from dept where deptno = #{deptno};")
    public Dept queryById(Long id);

    @Select("select * from dept")
    public List<Dept> queryAll();
}
