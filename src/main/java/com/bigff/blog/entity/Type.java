package com.bigff.blog.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data   //生成set和get方法
@ToString   //生成tostring方法
@JsonIgnoreProperties({" hibernateLazyInitializer", "handler"})
public class Type implements Serializable {
    private Long typeId;
    private String typeName;
    private List<Blog> blogs;
    public Type() {
    }
}
