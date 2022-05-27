package com.zhang.interceptor;

import com.zhang.exception.BusinessException;
import com.zhang.model.enums.ErrorCodeEnum;
import com.zhang.model.entity.User;
import com.zhang.service.UserService;
import com.zhang.service.impl.RedisServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Resource
    private RedisServiceImpl redisService;

    @Resource
    private User currentUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("校验Token");
        String token = request.getHeader("token");

        // 如果Header中没有token信息 ， 校验不通过， 直接抛错
        if (token == null || token.equals("")) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        String userId = token.split("#")[0];
        // 查Redis
        String realToken = (String) redisService.getValue(userId);

        if (StringUtils.isBlank(realToken)) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_LOGIN);
        }
        // 校验Token
        if (!token.equals(realToken)) {
            log.error("Token信息错误！");
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        currentUser = userService.getById(userId);
        request.setAttribute("currentUser",currentUser);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }


}
