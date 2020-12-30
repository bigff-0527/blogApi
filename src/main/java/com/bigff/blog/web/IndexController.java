package com.bigff.blog.web;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("front")
public class IndexController {

  @Autowired
  BlogService blogService;

  @RequestMapping(value="getIndexData")
  public Result getIndexData(int pageNum ,int pageSize) {
    PageHelper.startPage(pageNum,pageSize);
    List blogs =blogService.getBlogList();
    PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
    return ResultUtil.success(pageInfo);
  }
}
