package com.bigff.blog.web;

import com.bigff.blog.common.dto.SearchDto;
import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class IndexController {

  @Autowired
  BlogService blogService;

  @RequestMapping(value="getIndexData")
  public Result getIndexData() {
      List blogs = blogService.getBlogList(new SearchDto()).subList(0,3);
//    List indexBlogs = blogs.subList(0,3);
    return ResultUtil.success(blogs );
  }
}
