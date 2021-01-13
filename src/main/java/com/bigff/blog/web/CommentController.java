package com.bigff.blog.web;

import com.bigff.blog.entity.Blog;
import com.bigff.blog.entity.Comment;
import com.bigff.blog.entity.util.RedisUtils;
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

  @Autowired
  RedisUtils redisUtils;
  //    查询评论列表
  @GetMapping("/comments")
  public Result comments(Long blogId) {
    boolean haskey = redisUtils.hasKey("comments"+blogId);
    List<Comment> comments;
    if (haskey){
      comments = (List<Comment>) redisUtils.get("comments"+blogId);
    }else{
      comments = commentService.listCommentByBlogId(blogId);
      redisUtils.set("comments"+blogId,comments);
    }
    return ResultUtil.success(comments);
  }

  //    新增评论
  @PostMapping("/commentsPost")
  public Result post(@RequestBody  Comment comment) {
    Long blogId = comment.getBlogId();
    comment.setAvatar(avatar);
    try {
      if (comment.getParentComment().getId() != null) {
        comment.setParentCommentId(comment.getParentComment().getId());
      }
    }catch (NullPointerException e){

    }
    commentService.saveComment(comment);
    redisUtils.remove("commentsPost"+blogId);
    redisUtils.remove("comments"+blogId);
    boolean haskey = redisUtils.hasKey("commentsPost"+blogId);
    List<Comment> comments;
    if (haskey){
      comments = (List<Comment>) redisUtils.get("commentsPost"+blogId);
    }else{
      comments = commentService.listCommentByBlogId(blogId);
      redisUtils.set("commentsPost"+blogId,comments);
    }
    return ResultUtil.success(comments);
  }

  //    删除评论
  @GetMapping("/comment/delete")
  public Result delete(Long blogId,Long id,Comment comment  ){
    commentService.deleteComment(comment,id);
    List<Comment> comments = commentService.listCommentByBlogId(blogId);
    return ResultUtil.success(comments);
  }
}