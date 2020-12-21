package com.bigff.blog.service;

import com.bigff.blog.entity.User;
import com.bigff.blog.entity.util.MD5utils;
import com.bigff.blog.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserMapper userMapper;

  @Override
  public User checkUser(String username, String password) {
    return userMapper.checkUser(username,MD5utils.getMD5(password));
  }
}