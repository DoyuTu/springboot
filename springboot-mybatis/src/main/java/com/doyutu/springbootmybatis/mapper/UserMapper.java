package com.doyutu.springbootmybatis.mapper;

import com.doyutu.springbootmybatis.SuperMapper;
import com.doyutu.springbootmybatis.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends SuperMapper<User> {

    int deleteAll();

    @Select("select test_id as id, name, age, test_type from user")
    List<User> selectListBySQL();
}
