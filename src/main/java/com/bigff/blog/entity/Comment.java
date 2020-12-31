package com.bigff.blog.entity;


import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;

    private Date createTime;

    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();


    private Comment parentComment;

    private boolean adminComment;

    public Comment() {
    }


}
