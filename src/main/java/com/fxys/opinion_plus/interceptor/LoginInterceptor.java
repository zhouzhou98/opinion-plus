package com.fxys.opinion_plus.interceptor;


import com.auth0.jwt.JWT;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.domain.User;
import com.fxys.opinion_plus.enums.ResultCodeEnums;
import com.fxys.opinion_plus.exception.OpinionException;
import com.fxys.opinion_plus.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :suyuzhou
 * @date :20201213
 * @description 登录拦截器
 * @email :jack.su@jodoinc.com
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;
    /**
     * 放行的白名单
     */
    private static String[] whiteList = {
            PathConstants.USER_SING_IN,PathConstants.USER_REGISTER,PathConstants.KEYWORD_EXPORT,PathConstants.BLOG_CLOUD,PathConstants.BLOG_EXPORT
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (containsWhiteList(request.getRequestURI())) {
            return true;
        }
        
        // 获取token
        String token = request.getHeader("Authentication");

        if (StringUtils.isNotBlank(token)) {
            // token不为空，获取当前登录用户
            Long id;
            try{

                id= JWT.decode(token).getClaim("id").asLong();

            }catch (Exception e){
                throw new OpinionException(ResultCodeEnums.SYSTEM_ERROR);
            }
            User u=userService.selectById(id);
            if (u == null) {
                throw new OpinionException(ResultCodeEnums.USER_NOT_FIND);
            }else{
                return true;
            }
        }
        throw new OpinionException(ResultCodeEnums.USER_NOT_FIND);
    }

    /**
     * 判断url是否在白名单内
     * @param s  请求路径
     * @return true或者false
     */
    private boolean containsWhiteList(String s) {
        for (String url : whiteList) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }

}
