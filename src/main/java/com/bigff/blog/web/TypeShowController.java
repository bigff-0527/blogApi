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
@RequestMapping("front")
public class TypeShowController {

  @Autowired
  BlogService blogService;

  @Autowired
  TypeService typeService;

  @Autowired
  TagService tagService;

  @GetMapping("getTypeList")
  public Result getTypeList(){
    return  ResultUtil.success(typeService.getTypeList());
  }


  @GetMapping("getBlogByTypeId")
  public Result getBlogByTypeId(@RequestParam("pageNum") int pageNum,
                                @RequestParam("pageSize")int pageSize,
                                @RequestParam("typeId")Long id){
    PageRequest pageQuery = new PageRequest();
    pageQuery.setPageNum(pageNum);
    pageQuery.setPageSize(pageSize);
    return ResultUtil.success(blogService.getBlogByTypeId(pageQuery,id));
  }




}
