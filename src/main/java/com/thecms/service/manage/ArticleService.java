package com.thecms.service.manage;

import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageArticle;

public interface ArticleService {
    Result getArticleList(int start,int size);


    Result addArticle(ManageArticle manageArticle);
}
