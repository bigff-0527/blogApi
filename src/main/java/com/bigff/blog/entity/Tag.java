package com.bigff.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@ToString
public class Tag {

    private Long tagId;
    private String tagName;
    private List<Blog> blogs = new ArrayList<Blog>();

    public Tag() {
    }

}
