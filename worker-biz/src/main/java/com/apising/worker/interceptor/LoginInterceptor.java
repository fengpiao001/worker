package com.apising.worker.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.apising.common.lang.domain.Result;
import com.apising.common.lang.session.SessionDto;
import com.apising.common.lang.session.SessionLocal;
import com.apising.common.lang.util.CookieUtil;
import com.apising.common.lang.util.EncodeUtil;
import com.apising.common.lang.util.SessionUtil;
import com.apising.worker.config.SessionHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 登录拦截器
 * @author
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    SessionHandler sessionHandler;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionToken = request.getParameter(SessionUtil.SessionIdKey);
        if(StringUtils.isEmpty(sessionToken)){
            sessionToken = request.getHeader(SessionUtil.SessionIdKey);
        }
        if(StringUtils.isEmpty(sessionToken)){
            sessionToken = CookieUtil.getStringFromCookie(request,SessionUtil.SessionIdKey,null);
        }
        if(StringUtils.isEmpty(sessionToken)){
            responseFailMsg(Result.fail(600,"您当前未登录"),response);
            return false;
        }
        SessionDto sessionDto = sessionHandler.getSessionById(sessionToken);
        if(sessionDto == null){
            responseFailMsg(Result.fail(601,"您当前登录已过期"),response);
            return false;
        }
        response.addCookie(new Cookie(SessionUtil.SessionIdKey,sessionDto.getSessionId()));
        response.addCookie(new Cookie("realName", URLEncoder.encode(sessionDto.getUserName(),"UTF-8")));
        SessionLocal.setSession(sessionDto);
        return true;
    }


    private void responseFailMsg(Result result,HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Methods","*");
        response.setHeader("Access-Control-Allow-Headers","*");
        pw.print(JSONObject.toJSONString(result));
    }
}
