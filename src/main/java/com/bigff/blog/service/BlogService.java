package com.bigff.blog.service;

import com.bigff.blog.common.dto.SearchDto;
import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.PageResult;

import java.util.List;

public interface BlogService {

  //blog列表
  List<Blog> getBlogList(SearchDto searchDto);

  //

  //根据类型查找博客
  PageResult getBlogByTypeId(PageRequest pageRequest,Long id);

  //根据标签查找博客
  List<Blog> getBlogByTagId(Long id);

  void deleteBlog(Long id);

  Blog findBlogById(Long id);

  int updateBlog(Blog blog);

  int insertBlog(Blog blog);

  int deleteTags(Long id);

  //新增博客标签
  int insertTags(Long id, List<Tag> tags);

//  //根据标题||类型||标签查询博客
//  List<Blog> searchBlog(SearchDto searchDto);

  //views自增
  int updateViews(Long id);

  //查询浏览记录
  Integer getAllViews();

  //查询留言记录
  Integer getAllComments();

}