package com.bigff.blog.entity.util;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Result<T>  implements Serializable {

  private Integer code;
  private String msg;
  private T data;

  public Result() {
    super();
  }

  public Result(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }



}
