package com.bigff.blog.web;

import com.bigff.blog.common.dto.SearchDto;
import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Tag;
import com.bigff.blog.entity.util.PageRequest;
import com.bigff.blog.entity.util.PageResult;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogListController {
  @Autowired
  BlogService blogService;

  @Autowired
  TagService tagService;

//  @PostMapping("search")
//  public Result searchBlog(@RequestBody SearchDto searchDto){
//    PageHelper.startPage(searchDto.getPageNum(),searchDto.getPageSize());
//    List<Blog> blogs = blogService.searchBlog(searchDto);
////    List<Blog> blogByTagId = blogService.getBlogByTagId(searchDto.getTagId());
////    for (Blog b : blogs){
////      Long id = b.getId();
////
////    }
////    PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
//    return ResultUtil.success(blogs);
//  }

  @PostMapping("blogList")
  public Result getBlogList(@RequestBody SearchDto searchDto) {
    System.out.println(searchDto.getDateStart());
      PageHelper.startPage(searchDto.getPageNum(),searchDto.getPageSize());
      List<Blog> blogs = blogService.getBlogList(searchDto);
      for (Blog b : blogs){
          Long id = b.getId();
        List<Tag> tags = tagService.getTagByBlogId(id);
        b.setTags(tags);
      }
      PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
    return ResultUtil.success(pageInfo);
  }

  @GetMapping("queryBlogById")
  public Result queryBlogById(Long id){
    blogService.updateViews(id);
    return ResultUtil.success(blogService.findBlogById(id));
  }
}
