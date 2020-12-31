package com.bigff.blog.web.admin;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.User;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;

import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.mybatis.generator.codegen.ibatis2.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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

  @RequestMapping("delete")
  public Result deleteBlog(@ProbeParam("id") Long id){
     blogService.deleteBlog(id);
      return ResultUtil.success();

  }

  @GetMapping("blogList")
  public Result getBlogList(int pageNum ,int pageSize) {
    System.out.println(pageSize);
    PageHelper.startPage(pageNum,pageSize);
    List blogs =blogService.getBlogList();
    System.out.println(blogs.size());
    PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
    return ResultUtil.success(pageInfo);
  }

  @RequestMapping("findBlogById")
  public Result findBlogById(@RequestParam("id") Long id){
    System.out.println(id);
    if (blogService.findBlogById(id)!=null){
      Blog blog = blogService.findBlogById(id);
      return ResultUtil.success(blog);
    }
    return ResultUtil.error(400,"查找失败");
  }

  @PostMapping(value = "Save")
  public Result Save(@RequestBody Blog blog){
    if (blog.getId()==null){
      //新增
      blog.setCreate_time(new Date());
      blog.setUpdate_time(new Date());
      blog.setUser_id((long) 1);
      if(blogService.insertBlog(blog)>0){
        List tagIds = new ArrayList();
        for (Tag t :blog.getTags() ){
          tagIds.add(t.getTagId());
        }
        blogService.insertTags(blog.getId(),tagIds);
        return ResultUtil.success();
      }
      else return ResultUtil.error(400,"操作失败");
    }else{
      //更新
      if(blogService.updateBlog(blog)>0){
        List tagIds = new ArrayList();
        if (blogService.deleteTags(blog.getId())>0){
          for (Tag t :blog.getTags() ){
            tagIds.add(t.getTagId());
          }
          blogService.insertTags(blog.getId(),tagIds);
          return ResultUtil.success();
        }
        return ResultUtil.error("操作失败");
      }
      return ResultUtil.error(400,"操作失败");
    }
  }

  @GetMapping("search")
  public Result searchBlog(String title , Long typeId ,boolean recommend){
    if (blogService.searchBlog(title,typeId,recommend)==null){
      return ResultUtil.error("查询失败");
    }
    return ResultUtil.success(blogService.searchBlog(title,typeId,recommend));
  }


}