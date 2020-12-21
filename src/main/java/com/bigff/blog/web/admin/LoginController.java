package com.bigff.blog.web.admin;


import com.bigff.blog.entity.User;
import com.bigff.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RestController
public  class LoginController{
  @Autowired
  private UserService userService;

  @PostMapping("login")
  public String checkUser(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session,
                          RedirectAttributes attributes){
    User user = userService.checkUser(username,password);
    if (user!=null){
      user.setPassword(null);
      session.setAttribute("user",user);
      return  "/admin/index";
    }
      attributes.addFlashAttribute("message","用户名密码错误！");
      return "redirect:/login";

  }

}