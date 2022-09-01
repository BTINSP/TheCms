package com.thecms.mapper.manage;

import com.thecms.entity.manage.ManageArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    /**
     * 查询分页文章
     * @param start
     * @param size
     * @return
     */
    List<ManageArticle> getArticleList(@Param("start")int start, @Param("size")int size);

    boolean addArticle(
            @Param("article_name")String article_name,
            @Param("cover_img")String cover_img,
            @Param("status")int status,
            @Param("sort_rank")int sort_rank,
            @Param("parent_id")int parent_id,
            @Param("content")String content
    );

    boolean deleteArticleById(@Param("id")int id);

    boolean updateArticle(
            @Param("article_id")int article_id,
            @Param("article_name")String article_name,
            @Param("cover_img")String cover_img,
            @Param("status")int status,
            @Param("sort_rank")int sort_rank,
            @Param("parent_id")int parent_id,
            @Param("content")String content
    );

}
