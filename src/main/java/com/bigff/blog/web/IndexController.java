package com.bigff.blog.web;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.TagService;
import com.bigff.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

  @Autowired
  BlogService blogService;

  @Autowired
  TypeService typeService;

  @Autowired
  TagService tagService;


//  @RequestMapping(value = "getBlogList")
//  public List getUserList(){
//    List blogList = blogService.getBlogList();
//    return blogList;
//  }

  @RequestMapping(value="getIndexData")
  public Result findPage(int pageNum ) {
    PageHelper.startPage(pageNum,2);
    List blogs =blogService.getBlogList();
    PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
    Map<String,Object> homeData = new HashMap<>();
    homeData.put("blog",pageInfo);
    homeData.put("class", typeService.getTypeList());
    homeData.put("tag",tagService.getTagList());
    return ResultUtil.success(homeData);
  }
}
