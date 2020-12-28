package com.bigff.blog.service;


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
  public List<Blog> getBlogList() {
    return blogMapper.getBlogList();
  }

  @Override
  public PageResult getBlogByTypeId(PageRequest pageRequest,Long id) {
    return PageUtils.getPageResult( getPageInfoToType(pageRequest,id));
  }

  @Override
  public PageResult getBlogByTagId(PageRequest pageRequest, Long id) {
    return PageUtils.getPageResult( getPageInfoToTag(pageRequest,id));
  }

  @Override
  public int deleteBlog(Long id) {
    return blogMapper.deleteBlog(id);
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
  public int selectTags(Long id, List<Tag> tags) {
    return blogMapper.selectTags(id,tags);
  }

  @Override
  public int updateTags(Long id, List<Tag> tagId) {
    return blogMapper.updateTags(id,tagId);
  }

  private PageInfo<Blog> getPageInfo(PageRequest pageRequest) {
    int pageNum = pageRequest.getPageNum();
    int pageSize = pageRequest.getPageSize();
    PageHelper.startPage(pageNum, pageSize);
    List<Blog> blogs = blogMapper.getBlogList();
    return new PageInfo<Blog>(blogs);
  }

  private PageInfo<Blog> getPageInfoToType(PageRequest pageRequest,Long id) {
    int pageNum = pageRequest.getPageNum();
    int pageSize = pageRequest.getPageSize();
    PageHelper.startPage(pageNum, pageSize);
    List<Blog> blogs = blogMapper.getBlogByTypeId(id);
    return new PageInfo<Blog>(blogs);
  }


  private PageInfo<Blog> getPageInfoToTag(PageRequest pageRequest,Long id) {
    int pageNum = pageRequest.getPageNum();
    int pageSize = pageRequest.getPageSize();
    PageHelper.startPage(pageNum, pageSize);
    List<Blog> blogs = blogMapper.getBlogByTagId(id);
    return new PageInfo<Blog>(blogs);
  }
}
