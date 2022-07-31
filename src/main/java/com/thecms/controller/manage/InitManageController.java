package com.thecms.controller.manage;

import com.thecms.compenont.Result;
import com.thecms.service.manage.InitManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/manage")
public class InitManageController {

    @Autowired
    InitManageService initManageService;

    @GetMapping("getMenu")
    public Result getMenuList(HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        return initManageService.getMenuList();
    }

}
