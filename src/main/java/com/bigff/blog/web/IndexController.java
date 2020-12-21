package com.bigff.blog.web;

import com.bigff.blog.entity.util.PageRequest;
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

  @RequestMapping(value="homeData")
  public Map<String,Object> findPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize ) {
    PageRequest pageQuery = new PageRequest();
    pageQuery.setPageNum(pageNum);
    pageQuery.setPageSize(pageSize);

    Map<String,Object> homeData = new HashMap<>();
    homeData.put("blog",blogService.getBlogList(pageQuery));
    homeData.put("class", typeService.findHomeType());
    homeData.put("tag",tagService.findHomeTag());
    return homeData;
  }
}
