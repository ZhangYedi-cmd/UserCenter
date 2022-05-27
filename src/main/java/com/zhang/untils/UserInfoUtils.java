package com.zhang.untils;

import com.zhang.model.entity.User;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 加密信息工具类
 */
public class UserInfoUtils {

    /**
     * 获取一个随机的时间戳，再使用md5加密
     *
     * @return String
     */
    public static String getRandomSalt() {
        return DigestUtils.md5DigestAsHex(String.valueOf(new Date().getTime()).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 传入字符串和盐，使用md5加密
     *
     * @param str
     * @param salt
     * @return
     */
    public static String encrypt_str(String str, String salt) {
        return DigestUtils.md5DigestAsHex((str + salt).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 根据用户信息创建token
     *
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String userInfo = user.getUserPassword() + user.getUserAccount();
        StringBuffer token = new StringBuffer();
        token.append(user.getId());
        token.append("#");
        token.append(UserInfoUtils.getRandomSalt());
        token.append("#");
        token.append(DigestUtils.md5DigestAsHex((userInfo).getBytes(StandardCharsets.UTF_8)));
        return String.valueOf(token);
    }
}
