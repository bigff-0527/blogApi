package com.bigff.blog.web.admin;

import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.Type;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import org.springframework.util.StringUtils;

import com.bigff.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class TypeController {
  @Autowired
  TypeService typeService;

  @GetMapping("/typePageList")
  public Result getTypePageList(int pageNum,int pageSize){
    PageHelper.startPage(pageNum,pageSize);
    List types =typeService.getTypeList();
    PageInfo<Type> pageInfo = new PageInfo<Type>(types);
    return ResultUtil.success(pageInfo);

  }

  @GetMapping("/typeList")
  public Result getTypeList(){
    List types =typeService.getTypeList();
    return ResultUtil.success(types);

  }

  @GetMapping("deleteType")
  public Result deleteType(Long id){
    if (typeService.deleteType(id)>0){
      return ResultUtil.success();
    }
    return ResultUtil.error("删除失败");
  }

  @PostMapping("insertType")
  public Result insertType(@RequestBody Type type){
    if (typeService.insertType(type)>0){
      return ResultUtil.success();
    }
    return ResultUtil.error("新增失败");
  }

  @PostMapping("updateType")
  public Result updateType(@RequestBody Type type){
    if (typeService.updateType(type)>0){
      return ResultUtil.success();
    }
    return ResultUtil.error("修改失败");
  }

  @GetMapping("checkType")
  public Result checkType(String name){
    System.out.println(name);
    if (typeService.checkType(name)!=null)
      return ResultUtil.error(300,"已存在");
    return ResultUtil.success();
  }

  @GetMapping("findType")
  public Result findType(Long id){
    if (typeService.findTypeById(id)!=null){
      return ResultUtil.success(typeService.findTypeById(id));
    }
    return ResultUtil.error(404,"不存在");
  }

  @GetMapping("getBlogAllType")
  public Result getBlogAllType(){
    List blog = typeService.getBlogAllType();
    return ResultUtil.success(blog);
  }
}