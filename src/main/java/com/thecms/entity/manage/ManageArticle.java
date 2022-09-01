package com.thecms.entity.manage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManageArticle {
    @JsonProperty("key")
    private int id;
    @JsonProperty("articleName")
    private String article_name;
    @JsonProperty("coverImg")
    private String cover_img;
    private int status;
    @JsonProperty("sortRank")
    private int sort_rank;
    @JsonProperty("parentId")
    private int parent_id;
    private String content;
}
