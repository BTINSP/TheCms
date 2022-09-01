package com.thecms.config.interceptor;


import com.thecms.config.exception.NotFoundUserException;
import com.thecms.entity.manage.ManageUser;
import com.thecms.mapper.manage.ManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        ManageUser user = (ManageUser)session.getAttribute("user");

        if (Objects.nonNull(user)){
            return true;
        }

        throw new NotFoundUserException("请先登录");
    }
}