package com.bigff.blog.web.admin;

import com.bigff.blog.common.dto.SearchDto;
import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.RedisUtils;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;

import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.TagService;
import com.bigff.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin")
public  class BlogController {
  @Autowired
  BlogService blogService;

  @Autowired
  UserService userService;

  @Autowired
  TagService tagService;

  @Autowired
  RedisUtils redisUtils;

  @RequestMapping("delete")
  public Result deleteBlog(@ProbeParam("id") Long id){
    blogService.deleteBlog(id);
    boolean haskey = redisUtils.hasKey("queryBlogById"+id);
    if (haskey){
      redisUtils.remove("queryBlogById"+id);
    }
      return ResultUtil.success();

  }

//  @GetMapping("blogList")
//  public Result getBlogList(int pageNum ,int pageSize) {
//    PageHelper.startPage(pageNum,pageSize);
//    List blogs =blogService.getBlogList();
//    System.out.println(blogs.size());
//    PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
//    return ResultUtil.success(pageInfo);
//  }

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


  @RequestMapping("queryBlogById")
  public Result queryBlogById(Long id){
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


  @PostMapping(value = "Save")
  public Result Save(@RequestBody Blog blog){
      blog.setCreate_time(new Date());
      blog.setUpdate_time(new Date());
      blog.setUser_id((long) 1);
      int newBlog = blogService.insertBlog(blog);
      if(newBlog>0){
        List tagIds = new ArrayList();
        for (Tag t :blog.getTags() ){
          tagIds.add(t.getTagId());
        }
        blogService.insertTags(blog.getId(),tagIds);
        redisUtils.set("queryBlogById"+blog.getId(),blog);
        return ResultUtil.success();
      }
      else return ResultUtil.error(400,"操作失败");
  }

  @PostMapping("updateBlog")
  public Result updateBlog(@RequestBody Blog blog){
      //更新
      int update = blogService.updateBlog(blog);
      if(update>0){
        blog.setUpdate_time(new Date());
        List tagIds = new ArrayList();
        if (blogService.deleteTags(blog.getId())>0){
          for (Tag t :blog.getTags() ){
            tagIds.add(t.getTagId());
          }
          blogService.insertTags(blog.getId(),tagIds);
          redisUtils.setRemove("queryBlogById"+blog.getId());
          return ResultUtil.success();
        }
        return ResultUtil.error("操作失败");
      }
      return ResultUtil.error(400,"操作失败");

  }

  @GetMapping("getAllViews")
  public Result getAllViews(){
    return ResultUtil.success(blogService.getAllViews());
  }

  @GetMapping("getAllComments")
  public Result getAllComments(){
    return ResultUtil.success(blogService.getAllComments());
  }
}