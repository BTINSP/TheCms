package com.thecms.entity.manage;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ManageColumn {
    @JsonProperty("key")
    private int id;
    @JsonProperty("columnName")
    private String column_name;
    private int status;
    @JsonProperty("sortRank")
    private int sort_rank;
}
