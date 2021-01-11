package com.bigff.blog.service;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface TagService {

  List<Tag> getTagList();

  int deleteTag(Long id);

  int insertTag(Tag tag);

  int updateTag(Tag tag);

  Tag findTagById(Long id);

  Tag checkTag(String name);

  List<Tag> getTagByBlogId(Long id);

  List<Tag> getBlogAllTag();
}