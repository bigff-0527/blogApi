package com.bigff.blog.web.admin;

import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("deleteTag")
  public Result deleteTag(Long id){
    if (tagService.deleteTag(id)>0){
      return ResultUtil.success();
    }
    return ResultUtil.error("删除失败");
  }

  @PostMapping("insertTag")
  public Result insertTag(@RequestBody Tag tag){
    if (tagService.insertTag(tag)>0){
      return ResultUtil.success();
    }
    return ResultUtil.error("新增失败");
  }

  @PostMapping("updateTag")
  public Result updateTag(@RequestBody Tag tag){
    if (tagService.updateTag(tag)>0){
      return ResultUtil.success();
    }
    return ResultUtil.error("修改失败");
  }
}