package com.bigff.blog.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data   //生成set和get方法
@ToString   //生成tostring方法
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String first_picture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean share_statement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date create_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date update_time;
    private Long type_id;
    private Type type;

    private List<Tag> tags = new ArrayList<>();

    private Long user_id;
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private String description;
    public Blog() {
    }

}
