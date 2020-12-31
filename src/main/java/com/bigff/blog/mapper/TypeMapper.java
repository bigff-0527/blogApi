package com.bigff.blog.mapper;

import com.bigff.blog.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
  @Select("select id,name from t_type where id=#{id}")
  Type findTypeById(Long id);

  @Select("select * from t_type ")
  @Results({
          @Result(property = "typeId",column = "typeid"),
          @Result(property = "typeName",column = "typename"),
          @Result(property = "blogs",column = "typeId",one = @One(select = "com.bigff.blog.mapper.BlogMapper.getBlogByTypeId",
                  fetchType = FetchType.LAZY)),
  })
  List<Type> getTypeList();

  @Delete("delete from t_type where typeid = #{id}")
  int deleteType(Long id);

  @Insert("insert into t_type(typename) values(#{type.typeName})")
  int insertType(Type type);

  @Update("update t_type set typename = #{type.typeName} where typeid = #{type.typeId} ")
  int updateType(Type type);
}
