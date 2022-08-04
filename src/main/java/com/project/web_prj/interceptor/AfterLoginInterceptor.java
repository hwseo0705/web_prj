package com.project.web_prj.interceptor;

import com.project.web_prj.util.LoginUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// You Can Only Go To 'Sign-In' Page Before Login
@Configuration
public class AfterLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (LoginUtils.isLogin(session)) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
