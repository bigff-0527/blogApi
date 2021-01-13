package com.bigff.blog.service;


import com.bigff.blog.common.dto.SearchDto;
import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.PageResult;
import com.bigff.blog.entity.util.PageUtils;
import com.bigff.blog.mapper.BlogMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

  @Autowired
  private BlogMapper blogMapper;

  @Override
  public List<Blog> getBlogList(SearchDto searchDto) {
    return blogMapper.getBlogList(searchDto);
  }

  @Override
  public PageResult getBlogByTypeId(PageRequest pageRequest,Long id) {
    return PageUtils.getPageResult( getPageInfoToType(pageRequest,id));
  }

  @Override
  public List<Blog> getBlogByTagId(Long id) {
    return blogMapper.getBlogByTagId(id);
  }

  @Override
  public void deleteBlog(Long id) {
     blogMapper.deleteBlog(id);
  }

  @Override
  public Blog findBlogById(Long id) {


    return blogMapper.findBlogById(id);
  }

  @Override
  public int updateBlog(Blog blog) {
    return blogMapper.updateBlog(blog);
  }

  @Override
  public int insertBlog(Blog blog) {
    return blogMapper.insertBlog(blog);
  }

  @Override
  public int deleteTags(Long id) {
    return blogMapper.deleteTags(id);
  }

  @Override
  public int insertTags(Long id, List<Tag> tags) {
    return blogMapper.insertTags(id,tags);
  }

  @Override
  public int updateViews(Long id) {
    return blogMapper.updateViews(id);
  }

  @Override
  public Integer getAllViews() {
    return blogMapper.getAllViews();
  }

  @Override
  public Integer getAllComments() {
    return blogMapper.getAllComments();
  }

//  @Override
//  public List<Blog> searchBlog(SearchDto searchDto) {
//    return blogMapper.searchBlog(searchDto);
//  }

  private PageInfo<Blog> getPageInfoToType(PageRequest pageRequest,Long id) {
    int pageNum = pageRequest.getPageNum();
    int pageSize = pageRequest.getPageSize();
    PageHelper.startPage(pageNum, pageSize);
    List<Blog> blogs = blogMapper.getBlogByTypeId(id);
    return new PageInfo<Blog>(blogs);
  }

}
