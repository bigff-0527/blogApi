package com.bigff.blog.service;

        import com.bigff.blog.entity.Blog;
        import com.bigff.blog.entity.Tag;
        import com.bigff.blog.entity.util.PageRequest;
        import com.bigff.blog.entity.util.PageResult;

        import java.util.List;

public interface BlogService {



  List<Blog> getBlogList();

  //根据类型查找博客
  PageResult getBlogByTypeId(PageRequest pageRequest,Long id);

  //根据标签查找博客
  PageResult getBlogByTagId(PageRequest pageRequest,Long id);

  int deleteBlog(Long id);

  Blog findBlogById(Long id);

  int updateBlog(Blog blog);

  int insertBlog(Blog blog);


  //新增博客标签
  int selectTags(Long id, List<Tag> tags);

  //修改博客标签
  int updateTags( Long id, List<Tag> tagId);
}