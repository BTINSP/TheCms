package com.thecms.controller;

import com.thecms.compenont.Result;
import com.thecms.entity.UserEntity;
import com.thecms.mapper.DialectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    DialectMapper dialectMapper;


    @GetMapping("/")
    @ResponseBody
    public String Index(){
        return "spring";
    }

    @GetMapping("/dialect")
    public String Dialect(){
        return "Dialect";
    }

    @GetMapping("/get")
    @ResponseBody
    public Result getUser(){
        List<UserEntity> allUser = dialectMapper.getAllUser();
        return Result.success(allUser);
    }

}
