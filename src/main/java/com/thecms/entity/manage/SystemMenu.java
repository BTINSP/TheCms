package com.thecms.entity.manage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = {"id", "status", "parent","priority"})
public class SystemMenu {
    private int id;

    @JsonProperty("label")
    private String menu_name;
    @JsonProperty("key")
    private String path;
    private int status;
    private int parent;
    private String icon;
    private int priority;
    private List<SystemMenu> children;
}
