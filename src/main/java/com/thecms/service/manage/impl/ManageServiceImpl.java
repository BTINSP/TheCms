package com.thecms.service.manage.impl;


import com.thecms.compenont.Result;
import com.thecms.entity.manage.ManageUser;
import com.thecms.mapper.manage.ManageMapper;
import com.thecms.service.manage.ManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ManageServiceImpl implements ManageService {

    private static final String STATIC_PATH = "static";
    private static final String IMAGES_UPLOAD_PATH = "/upload/images/";

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public Result upload(MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        //  判断是否为空
        if (multipartFile.isEmpty()) {
            return Result.fail("空文件");
        }
        //  如果图片大于10M
        if (multipartFile.getSize() > 10 *1024 * 1024){
            return Result.fail("图片大于10M");
        }

        //  获取图片名称
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null){
            return Result.fail("获取名称失败,请重试");
        }

        //  分割图片后缀
        String originalFileSuffix  = originalFilename.substring(originalFilename.lastIndexOf("."));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dataPath = simpleDateFormat.format(new Date());

        //  获取classPath路径
        String localPath = ResourceUtils.getURL("classpath:").getPath();

        File targetFile = new File(localPath + STATIC_PATH + IMAGES_UPLOAD_PATH, dataPath);
        if (!targetFile.exists()){
            if (!targetFile.mkdirs()) {
                return Result.fail("创建目录失败");
            }
        }

        String imgRandomName = UUID.randomUUID().toString();
        File targetFileName = new File(targetFile,imgRandomName + originalFileSuffix);

        //  保存图片
        multipartFile.transferTo(targetFileName);

        HashMap<String, String> imagePathMessage = new HashMap<>();
        imagePathMessage.put("path",IMAGES_UPLOAD_PATH + dataPath + "/" +  imgRandomName + originalFileSuffix);

        return Result.success( "success",imagePathMessage);
    }

    @Override
    public Result login(ManageUser manageUser, HttpServletRequest request) {
        if (Objects.isNull(manageUser)){
            return Result.fail("请输入信息");
        }

        ManageUser userByUsername = manageMapper.getUserByUsername(manageUser.getUsername());
        if (Objects.isNull(userByUsername)){
            return Result.fail("用户名或密码错误");
        }
        if (!userByUsername.getPassword().equals(manageUser.getPassword())){
            return Result.fail("用户名或密码错误");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user",manageUser);
        return Result.success("登录成功");
    }

}
