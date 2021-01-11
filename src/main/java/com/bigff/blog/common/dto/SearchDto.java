package com.bigff.blog.common.dto;

import com.bigff.blog.entity.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
public class SearchDto {
  private int pageNum;
  private int pageSize;
  private String title;
  private Long typeId;
  private Long tagId;
  @DateTimeFormat(pattern = "yyyy-MM")
  @JsonFormat(pattern="yyyy-MM",timezone="GMT+8")
  private Date dateStart;

  @DateTimeFormat(pattern = "yyyy-MM")
  @JsonFormat(pattern="yyyy-MM",timezone="GMT+8")
  private Date dateEnd;

}
