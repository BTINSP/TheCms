package com.thecms.controller.manage;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageArticle;
import com.thecms.service.manage.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/manage/api")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("getArticleList")
    public Result getArticleList(@RequestParam("start")int start,@RequestParam("size")int size){
        return articleService.getArticleList(start,size);
    }

    @PostMapping("addArticle")
    public Result addArticle(@RequestBody ManageArticle manageArticle) throws JsonProcessingException {
        System.out.println(manageArticle);
        return articleService.addArticle(manageArticle);
    }

    
}
