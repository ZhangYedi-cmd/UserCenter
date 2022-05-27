package com.zhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.model.entity.User;
import com.zhang.model.response.LoginResponse;

import java.util.List;

/**
 * @author ZYD -- TUST
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     */
    LoginResponse userLogin(String userAccount, String password);


    /**
     * 注册
     */
    User userRegister(String username, String userAccount, String userPassword);


    /**
     * 脱敏
     * */
    User getSafeUser(User user);


    /**
     * 返回脱敏后的用户list
     * */
    List<User> getSafeUserList(IPage<User> userIPage);
}

