package com.bigff.blog.service;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.PageResult;

import java.util.List;

public interface BlogService {



  List<Blog> getBlogList();

  //根据类型查找博客
  PageResult getBlogByTypeId(PageRequest pageRequest,Long id);

  //根据标签查找博客
  PageResult getBlogByTagId(PageRequest pageRequest,Long id);

  void deleteBlog(Long id);

  Blog findBlogById(Long id);

  int updateBlog(Blog blog);

  int insertBlog(Blog blog);

  int deleteTags(Long id);

  //新增博客标签
  int insertTags(Long id, List<Tag> tags);

  //根据标题||类型||推荐查询博客
  List<Blog> searchBlog(String title,Long typeId,boolean recommend);

}