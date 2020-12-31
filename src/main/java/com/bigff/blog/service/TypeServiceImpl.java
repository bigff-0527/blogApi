package com.bigff.blog.service;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Type;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.mapper.TypeMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

  @Autowired
  private TypeMapper typeMapper;


  @Override
  public List<Type> getTypeList() {
    return typeMapper.getTypeList();
  }

  @Override
  public int deleteType(Long id) {
    return typeMapper.deleteType(id);
  }

  @Override
  public int insertType(Type type) {
    return typeMapper.insertType(type);
  }

  @Override
  public int updateType(Type type) {
    return typeMapper.updateType(type);
  }


}
