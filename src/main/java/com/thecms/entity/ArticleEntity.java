package com.thecms.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class ArticleEntity {
        private int id;
        private String article_name;
        private String cover_img;
        private int status;
        private int sort_rank;
        private int parent_id;
        private String content;

}
