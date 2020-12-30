package com.bigff.blog.web;


import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {

  @Autowired
  private BlogService blogService;

  @GetMapping("/getProfileData")
  public Result getProfileData(int pageNum, int pageSize){
    PageHelper.startPage(pageNum,pageSize);
    List blogs =blogService.getBlogList();
    PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
    return ResultUtil.success(pageInfo);
  }
}
