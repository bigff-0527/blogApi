package com.bigff.blog.web.admin;

import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class TagController {
  @Autowired
  TagService tagService;

  @GetMapping("tagList")

  public Result getTagList(){
    List tags =tagService.getTagList();
    return ResultUtil.success(tags);
  }

  @GetMapping("tagPageList")
  public Result getTagPageList(int pageNum,int pageSize){
    PageHelper.startPage(pageNum,pageSize);
    List tags =tagService.getTagList();
    PageInfo<Tag> pageInfo = new PageInfo<Tag>(tags);
    return ResultUtil.success(pageInfo);

  }
}