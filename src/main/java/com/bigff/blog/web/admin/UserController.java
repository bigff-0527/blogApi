package com.bigff.blog.web.admin;

import com.bigff.blog.entity.User;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


  @Autowired
  UserService userService;



  @RequiresAuthentication
  @GetMapping("index")
  public Result index(){
    User user = userService.getById(1L);
    return ResultUtil.success(user);

  }
}
