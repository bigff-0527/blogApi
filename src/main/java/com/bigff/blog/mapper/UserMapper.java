package com.bigff.blog.mapper;

import com.bigff.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

  @Select("select userid,username,avatar from t_user where userid = #{id}")
  User findUserById(Long id);

  @Select("select * from t_user where username = #{username}")
  User checkUser(String username);
}
