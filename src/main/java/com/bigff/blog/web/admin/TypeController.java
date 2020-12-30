package com.bigff.blog.web.admin;

import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.Type;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;

import com.bigff.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}