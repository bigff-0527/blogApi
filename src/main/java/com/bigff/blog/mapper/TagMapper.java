package com.bigff.blog.mapper;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {

  //标签数量
  @Select("select * from t_tag  ")
  @Results({
          @Result(property = "tagId", column = "tagid"),
          @Result(property = "tagName", column = "tagname"),
          @Result(property = "blogs",column = "tagId",one = @One(select = "com.bigff.blog.mapper.BlogMapper.getBlogByTagId",
                  fetchType = FetchType.LAZY)),
  })
  List<Tag> getTagList();


  @Select("select * from t_blog_tags where tags_id = #{id}")
  @Results({
          @Result(property = "tagId", column = "tags_id"),

  })
  List<Tag> findTagByBlogID();

  @Select("select * from t_tag where id = #{id}")
  @Results({
          @Result(property = "tagId", column = "tagid"),
          @Result(property = "tagName",column = "tagname")
  })
  Tag findTagById();
}
