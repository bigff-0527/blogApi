package com.bigff.blog.web;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Comment;
import com.bigff.blog.entity.util.Result;
import com.bigff.blog.entity.util.ResultUtil;
import com.bigff.blog.service.BlogService;
import com.bigff.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController{
  @Autowired
  CommentService commentService;

  @Autowired
  private BlogService blogService;

  @Value("${comment.avatar}")
  private String avatar;

  //    查询评论列表
  @GetMapping("/comments")
  public Result comments(Long blogId) {
    List<Comment> comments = commentService.listCommentByBlogId(blogId);
//    model.addAttribute("comments", comments);
    return ResultUtil.success(comments);
  }

  //    新增评论
  @PostMapping("/commentsPost")
  public Result post(@RequestBody  Comment comment) {
    Long blogId = comment.getBlogId();
//    User user = (User) session.getAttribute("user");
//    if (user != null) {
//      comment.setAvatar(user.getAvatar());
//      comment.setAdminComment(true);
//    } else {
//      //设置头像
//      comment.setAvatar(avatar);
//    }
    comment.setAvatar(avatar);
    try {
      if (comment.getParentComment().getId() != null) {
        comment.setParentCommentId(comment.getParentComment().getId());
      }
    }catch (NullPointerException e){

    }

    commentService.saveComment(comment);
    List<Comment> comments = commentService.listCommentByBlogId(blogId);
//    model.addAttribute("comments", comments);
    return ResultUtil.success(comments);
  }

  //    删除评论
  @GetMapping("/comment/delete")
  public Result delete(Long blogId,Long id,Comment comment  ){
    commentService.deleteComment(comment,id);
//    Blog blog = blogService.findBlogById(blogId);
    List<Comment> comments = commentService.listCommentByBlogId(blogId);
//    model.addAttribute("blog", detailedBlog);
//    model.addAttribute("comments", comments);
    return ResultUtil.success(comments);
  }
}