package com.thecms.controller.manage;

import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageColumn;
import com.thecms.service.manage.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/manage/api")
public class ManageController {

    @Autowired
    ManageService manageService;


    @PostMapping("upload")
    public Result uploadImg(@RequestParam("avatar")MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        return manageService.upload(multipartFile,httpServletRequest);
    }
}
