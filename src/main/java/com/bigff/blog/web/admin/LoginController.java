package com.bigff.blog.web.admin;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.lang.Assert;
import com.bigff.blog.common.dto.LoginDto;
import com.bigff.blog.entity.User;
import com.bigff.blog.entity.util.JwtUtils;
import com.bigff.blog.entity.util.MD5utils;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.UserService;
import org.apache.shiro.SecurityUtils;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("admin")
public  class LoginController{
  @Autowired
  private UserService userService;

  @Autowired
  JwtUtils jwtUtils;

//  @PostMapping("login")
//  public Result checkUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session,
//                          RedirectAttributes attributes){
//    User user = userService.checkUser(username,password);
//    if (user!=null){
//      user.setPassword(null);
//      session.setAttribute("user",user);
//      return ResultUtil.success();
//    }
//      return ResultUtil.error(400,"用户名密码错误！");
//
//  }

  @PostMapping("login")
  public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
    User user = userService.checkUser(loginDto.getUsername());
    Assert.notNull(user,"用户不存在");
    if (user==null || !user.getPassword().equals(MD5utils.getMD5(loginDto.getPassword()))){
      return ResultUtil.error("密码不正确");
    }
    String jwt = jwtUtils.generateToken(user.getUserId());
    response.setHeader("Authorization",jwt);
    response.setHeader("Access-control-Expose-Headers","Authorization");
    return ResultUtil.success(MapUtil.builder()
            .put("userId",user.getUserId())
            .put("username",user.getUsername())
            .put("userAvatar",user.getAvatar())
            .map()
            );
  }

//  @RequiresAuthentication
  @GetMapping("logout")
  public Result logout(){
    SecurityUtils.getSubject().logout();
    return ResultUtil.success();
  }

}