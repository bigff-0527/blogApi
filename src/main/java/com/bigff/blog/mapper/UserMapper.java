package com.bigff.blog.mapper;

import com.bigff.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

  @Select("select id,username,avatar from t_user where id=#{id}")
  User findUserById(Long id);

  @Select("select username,avatar from t_user where username = #{username} and password = #{password}")
  User checkUser(String username,String password);
}
