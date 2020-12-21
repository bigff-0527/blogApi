package com.bigff.blog.service;

import com.bigff.blog.entity.Type;
import com.bigff.blog.mapper.TypeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

  @Autowired
  private TypeMapper typeMapper;


  @Override
  public List<Type> findHomeType() {
    return typeMapper.findHomeType();
  }
}
