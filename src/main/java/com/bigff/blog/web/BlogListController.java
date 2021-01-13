package com.bigff.blog.web;

import com.alibaba.fastjson.JSON;
import com.bigff.blog.common.dto.SearchDto;
import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.*;
import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogListController {
  @Autowired
  BlogService blogService;

  @Autowired
  TagService tagService;

  @Autowired
  RedisUtils redisUtils;

  @PostMapping("blogList")
  public Result getBlogList(@RequestBody SearchDto searchDto) {
    PageHelper.startPage(searchDto.getPageNum(),searchDto.getPageSize());
    List<Blog> blogs  = blogService.getBlogList(searchDto);
    for (Blog b : blogs){
      Long id = b.getId();
      List<Tag> tags = tagService.getTagByBlogId(id);
      b.setTags(tags);
    }
    PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
    return ResultUtil.success(pageInfo);
  }

  @GetMapping("queryBlogById")
  public Result queryBlogById(Long id){
    blogService.updateViews(id);
//    Blog blog  = blogService.findBlogById(id);
    Blog blog;
    boolean haskey = redisUtils.hasKey("queryBlogById"+id);
    if (haskey){
       Object blog1 = redisUtils.get("queryBlogById"+id);
       blog = (Blog) blog1;
    }else{
      //从数据库中获取信息
      blog = blogService.findBlogById(id);
      redisUtils.set("queryBlogById"+id,blog);
    }
    return ResultUtil.success(blog);
  }
}
