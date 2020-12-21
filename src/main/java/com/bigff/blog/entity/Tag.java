package com.bigff.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data   //生成set和get方法
@ToString   //生成tostring方法
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Tag {

    private Long tagId;
    private String tagName;
    private List<Blog> blogs = new ArrayList<Blog>();

    public Tag() {
    }

}
