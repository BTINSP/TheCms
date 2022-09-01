package com.thecms.mapper;

import com.thecms.entity.ArticleEntity;
import com.thecms.entity.ColumnEntity;
import com.thecms.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DialectMapper {

    List<UserEntity> getAllUser();

    List<ArticleEntity> getArticleById(@Param("parent_id")int parent_id);

    List<ColumnEntity> getColumn();
}
