package com.bigff.blog.mapper;

import com.bigff.blog.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
//  @Select("select * from t_blog iat GROUP BY update_time desc,id desc")
//  @Results({
//          @Result(id = true,property = "id",column = "id"),
//          @Result(property = "type",column = "type_id",one = @One(select = "com.bigff.blog.mapper.TypeMapper.findTypeById",
//                  fetchType = FetchType.LAZY)),
//          @Result(property = "user",column = "user_id",one = @One(select = "com.bigff.blog.mapper.UserMapper.findUserById",
//                  fetchType = FetchType.LAZY)),
//          @Result(property = "tags",column = "id",many = @Many(select = "com.bigff.blog.mapper.TagMapper.findTagByBlogID",
//                  fetchType = FetchType.LAZY)),
//
//
//  })
//  List<Blog> selectPage();

  List getBlogList();

  @Select("select * from t_blog where type_id = #{id}")
  Blog findBlogByTypeId(Long id);


  @Select("select * from t_blog_tags where tags_id = #{id}")
  @Results({
          @Result(property = "id", column = "blogs_id"),
  })
  Blog findBlogByTagId(Long id);

}
