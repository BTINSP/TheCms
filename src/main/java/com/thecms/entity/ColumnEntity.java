package com.thecms.entity;

import lombok.Data;

@Data
public class ColumnEntity {

    private int id;
    private String column_name;
    private int status;
    private int sort_rank;
    private int parent_id;

}
