package com.thecms.service.manage;

import com.thecms.compenont.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ManageService {


    Result upload(MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException;

}
