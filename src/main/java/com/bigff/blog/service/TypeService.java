package com.bigff.blog.service;
import com.bigff.blog.entity.Type;

import java.util.List;

public interface TypeService {

  List<Type> getTypeList();

  int deleteType(Long id);

  int insertType(Type type);

  int updateType(Type type);

}