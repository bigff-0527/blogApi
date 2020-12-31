package com.bigff.blog.mapper;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

  List getBlogList();

  List<Blog> getBlogByTypeId(Long id);


//  @Select("select * from t_blog_tags where tags_id = #{id}")
//  @Results({
//          @Result(property = "id", column = "blogs_id"),
//  })
  List<Blog> getBlogByTagId(Long id);

  @Select("delete from t_blog where id = #{id}")
  void deleteBlog(Long id);

  Blog findBlogById(Long id);

  int updateBlog(@Param("blog") Blog blog);

  int insertBlog(@Param("blog") Blog blog);

  int deleteTags(@Param("blog_id") Long id);

  //新增博客标签
  int insertTags(@Param("blog_id") Long id, @Param("tags_id") List<Tag> tagId);

  //修改博客标签
  int updateTags(@Param("blog_id") Long id, @Param("tags_id") List<Tag> tagId);

  List<Blog> searchBlog(String title,Long typeId,boolean recommend);

}
