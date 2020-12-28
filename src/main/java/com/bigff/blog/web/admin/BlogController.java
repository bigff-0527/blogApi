package com.bigff.blog.web.admin;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;

import com.bigff.blog.service.BlogService;
import com.sun.org.glassfish.external.probe.provider.annotations.ProbeParam;
import org.mybatis.generator.codegen.ibatis2.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public  class BlogController {
  @Autowired
  BlogService blogService;

  @RequestMapping("deleteBlog")
  public Result deleteBlog(@ProbeParam("id") Long id){

    if (blogService.deleteBlog(id)>0){
      return ResultUtil.success(blogService.deleteBlog(id));
    }
    return ResultUtil.error(400,"删除失败");

  }

  @RequestMapping("findBlogById")
  public Result findBlogById(@RequestParam("id") Long id){
    if (blogService.findBlogById(id)!=null){
      Blog blog = blogService.findBlogById(id);
      return ResultUtil.success(blog);
    }
    return ResultUtil.error(400,"查找失败");
  }

  @PostMapping(value = "Save")
  public Result Save(@RequestBody Blog blog){
    if (blog.getId()==null){
      blog.setCreate_time(new Date());
      blog.setUpdate_time(new Date());
      blog.setUser_id((long) 1);
      if(blogService.insertBlog(blog)>0){
        List tagIds = new ArrayList();
        for (Tag t :blog.getTags() ){
          tagIds.add(t.getTagId());
        }
        blogService.selectTags(blog.getId(),tagIds);
        return ResultUtil.success();
      }
      else return ResultUtil.error(400,"操作失败");
    }else{
      if(blogService.updateBlog(blog)>0){
        List tagIds = new ArrayList();
        for (Tag t :blog.getTags() ){
          tagIds.add(t.getTagId());
        }
        blogService.updateTags(blog.getId(),tagIds);
        return ResultUtil.success();
      }
      return ResultUtil.error(400,"操作失败");
    }
  }
}