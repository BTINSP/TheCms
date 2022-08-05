package com.thecms.controller.manage;

import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageColumn;
import com.thecms.service.manage.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/manage/api")
public class ManageController {

    @Autowired
    ManageService manageService;

    @GetMapping("getMenu")
    public Result getMenuList(){
        return manageService.getMenuList();
    }

    @GetMapping("getColumn")
    public Result getColumnList(){
        return manageService.getColumnList();
    }

    @PostMapping("addColumn")
    public Result addColumn(@RequestBody ManageColumn manageColumn){
        return manageService.addColumn(manageColumn);
    }

    @PostMapping("deleteColumn")
    public Result deleteColumn(@RequestBody ManageColumn manageColumn){
        return manageService.deleteColumn(manageColumn);
    }
    @PostMapping("updateColumn")
    public Result updateColumn(@RequestBody List<ManageColumn> manageColumn){
        return manageService.updateColumn(manageColumn.get(0),manageColumn.get(1));
    }

    @PostMapping("changeColumnStatus")
    public Result changeColumnStatus(@RequestBody ManageColumn manageColumn){
        System.out.println(manageColumn);
        return manageService.updateColumnStatus(manageColumn);
    }
}
