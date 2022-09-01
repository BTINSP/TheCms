package com.thecms.controller.manage;

import com.thecms.compenont.Result;
import com.thecms.config.exception.NotFoundUserException;
import com.thecms.entity.manage.ManageUser;
import com.thecms.service.manage.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;


@RestController
@RequestMapping(value = "/manage/api")
public class ManageController {

    @Autowired
    ManageService manageService;


    @PostMapping("upload")
    public Result uploadImg(@RequestParam("avatar")MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        return manageService.upload(multipartFile,httpServletRequest);
    }

    @PostMapping("login")
    public Result login(@RequestBody ManageUser manageUser,HttpServletRequest request){
        return manageService.login(manageUser,request);
    }
}
