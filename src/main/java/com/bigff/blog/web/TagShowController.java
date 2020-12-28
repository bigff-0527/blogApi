package com.bigff.blog.web;

import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.TagService;
import com.bigff.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TagShowController {

  @Autowired
  BlogService blogService;

  @Autowired
  TypeService typeService;

  @Autowired
  TagService tagService;

  @GetMapping("getTagList")
  public Result getTagList(){
    return  ResultUtil.success(tagService.getTagList());
  }


  @GetMapping("getBlogByTagId")
  public Result getBlogByTypeId(@RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize")int pageSize,
                                @RequestParam("tagId")Long id){
    PageRequest pageQuery = new PageRequest();
    pageQuery.setPageNum(pageNum);
    pageQuery.setPageSize(pageSize);
    return ResultUtil.success(blogService.getBlogByTagId(pageQuery,id));
  }




}
