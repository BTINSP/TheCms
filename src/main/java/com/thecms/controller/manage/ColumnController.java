package com.thecms.controller.manage;

import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageColumn;
import com.thecms.service.manage.ColumnService;
import com.thecms.service.manage.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/manage/api")
public class ColumnController {

    @Autowired
    ColumnService columnService;

    @GetMapping("getMenu")
    public Result getMenuList(){
        return columnService.getMenuList();
    }

    @GetMapping("getColumn")
    public Result getColumnList(){
        return columnService.getColumnList();
    }

    @PostMapping("addColumn")
    public Result addColumn(@RequestBody ManageColumn manageColumn){
        return columnService.addColumn(manageColumn);
    }

    @PostMapping("deleteColumn")
    public Result deleteColumn(@RequestBody ManageColumn manageColumn){
        return columnService.deleteColumn(manageColumn);
    }
    @PostMapping("updateColumn")
    public Result updateColumn(@RequestBody List<ManageColumn> manageColumn){
        return columnService.updateColumn(manageColumn.get(0),manageColumn.get(1));
    }

    @PostMapping("changeColumnStatus")
    public Result changeColumnStatus(@RequestBody ManageColumn manageColumn){
        return columnService.updateColumnStatus(manageColumn);
    }
}
