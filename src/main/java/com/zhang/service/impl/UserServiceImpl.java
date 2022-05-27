package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.exception.BusinessException;
import com.zhang.model.enums.ErrorCodeEnum;
import com.zhang.model.entity.User;
import com.zhang.model.response.LoginResponse;
import com.zhang.service.UserService;
import com.zhang.mapper.UserMapper;
import com.zhang.untils.UserInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author ZYD -- Tust
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private RedisServiceImpl redisService;


    @Override
    public LoginResponse userLogin(String userAccount, String password) {

        if (StringUtils.isAnyBlank(userAccount, password)) {
            throw new BusinessException(ErrorCodeEnum.NULL_PARAMS);
        }

        // 查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        User user = this.getOne(userQueryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND_USER);
        }

        // 校验密码
        String salt = user.getSalt();
        if (!UserInfoUtils.encrypt_str(password, salt).equals(user.getUserPassword())) {
            throw new BusinessException(ErrorCodeEnum.PWD_ERROR);
        }

        // 返回token信息
        String token = UserInfoUtils.getToken(user);
        redisService.setValue(String.valueOf(user.getId()), token);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setSafeUser(user);
        return loginResponse;
    }

    /**
     * 用户注册
     */
    @Override
    public User userRegister(String username, String userAccount, String userPassword) {

        if (StringUtils.isAnyBlank(username,
                userAccount,
                userPassword)) {
            throw new BusinessException(ErrorCodeEnum.NULL_PARAMS);
        }

        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_LENGTH_ERROR);
        }

        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCodeEnum.PWD_LENGTH_ERROR);
        }

        String regEx = ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
        Matcher matcher = Pattern.compile(regEx).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_WRONGFUL);
        }

        // 查看当前用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = this.count(queryWrapper);

        if (count > 0) {
            throw new BusinessException(ErrorCodeEnum.USER_HAS_REGISTER);
        }

        // 加密密码
        String salt = UserInfoUtils.getRandomSalt();
        String pwd = UserInfoUtils.encrypt_str(userPassword, salt);
        User user = new User(username, userAccount, pwd, salt);
        boolean result = this.save(user);

        if (!result) {
            throw new BusinessException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        // 脱敏
        user.setSafeUser();
        return user;
    }

    @Override
    public User getSafeUser(User user) {
        user.setUserPassword(null);
        user.setSalt(null);
        user.setRole(null);
        user.setUserStatus(null);
        return user;
    }

    @Override
    public List<User> getSafeUserList(IPage<User> userIPage) {
        return  userIPage.getRecords().stream().map(user -> this.getSafeUser(user)).collect(Collectors.toList());
    }

}




