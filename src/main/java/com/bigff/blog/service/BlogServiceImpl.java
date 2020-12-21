package com.bigff.blog.service;


import com.bigff.blog.entity.Blog;
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
  public PageResult getBlogList(PageRequest pageRequest) {
    return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
  }

  private PageInfo<Blog> getPageInfo(PageRequest pageRequest) {
    int pageNum = pageRequest.getPageNum();
    int pageSize = pageRequest.getPageSize();
    PageHelper.startPage(pageNum, pageSize);
    List<Blog> blogs = blogMapper.getBlogList();
    return new PageInfo<Blog>(blogs);
  }
}
