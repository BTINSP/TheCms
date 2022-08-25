package com.thecms.service.manage.impl;

import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageArticle;
import com.thecms.mapper.manage.ArticleMapper;
import com.thecms.service.manage.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Result getArticleList(int start, int size) {
        List<ManageArticle> articleList = articleMapper.getArticleList(start, size);
        if (Objects.isNull(articleList)){
            return Result.fail("查询错误");
        }
        return Result.success(articleList);
    }

    @Override
    public Result addArticle(ManageArticle manageArticle) {
        if (Objects.isNull(manageArticle)){
            return Result.fail("请重试");
        }
        boolean result = articleMapper.addArticle(
                manageArticle.getArticle_name(),
                manageArticle.getCover_img(),
                manageArticle.getStatus(),
                manageArticle.getSort_rank(),
                manageArticle.getParent_id(),
                manageArticle.getContent()
        );
        if (!result){
            return Result.fail("文章添加失败");
        }
        return Result.success("添加成功");
    }


}
