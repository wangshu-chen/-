package com.bishe.teamproject.interceptor;

import com.bishe.teamproject.jwt.JwtUtil;
import com.bishe.teamproject.model.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拦截器进行判断放行
        if (request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }


        //如果是图片也放行
        if (request.getRequestURI().contains("images")){
            return true;
        }

        if (request.getRequestURI().contains("fileUpload")){
            return true;
        }

        //获取token
        String token = request.getHeader("token");
        if (token == null){
            //没有token对象
            return false;
        }else {
            //如果存在 是否有效

            boolean b = JwtUtil.checkToken(token);
            if (b){
                //如果有效 token续期
                UserInfo info = new UserInfo();
                info.setType(JwtUtil.getUserType(token));
                info.setId(JwtUtil.getUserId(token));
                info.setUsername(JwtUtil.getMemberIdByJwtToken(token));
                token = JwtUtil.getJwtToken(info);
                response.addHeader("token",token);
                return true;
            }else{
                response.setHeader("code","50008");
                return false;
            }

        }
    }
}
