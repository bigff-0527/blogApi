package com.bigff.blog.service;

import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.Type;
import com.bigff.blog.mapper.TagMapper;
import com.bigff.blog.mapper.TypeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

  @Autowired
  private TagMapper tagMapper;

  @Override
  public List<Tag> getTagList() {
    return tagMapper.getTagList();
  }

  @Override
  public int deleteTag(Long id) {
    return tagMapper.deleteTag(id);
  }

  @Override
  public int insertTag(Tag tag) {
    return tagMapper.insertTag(tag);
  }

  @Override
  public int updateTag(Tag tag) {
    return tagMapper.updateTag(tag);
  }
}
