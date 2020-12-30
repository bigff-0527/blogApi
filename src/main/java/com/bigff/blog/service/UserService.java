package com.bigff.blog.service;

import com.bigff.blog.entity.User;

public interface UserService{
  User checkUser(String username);

  User getById(Long id);
}