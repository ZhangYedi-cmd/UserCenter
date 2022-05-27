package com.zhang.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.base.request.DelParams;
import com.zhang.base.response.BaseResponse;
import com.zhang.base.response.ResultUtils;
import com.zhang.constans.RoleConstants;
import com.zhang.exception.BusinessException;
import com.zhang.model.enums.ErrorCodeEnum;
import com.zhang.model.request.SearchUserRequest;
import com.zhang.model.request.UserLoginRequest;
import com.zhang.model.response.LoginResponse;
import com.zhang.model.entity.User;
import com.zhang.model.response.SearchUserResponse;
import com.zhang.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    public BaseResponse<User> doUserRegister(@RequestBody User user) {
        User resUser = userService.userRegister(user.getUsername(), user.getUserAccount(), user.getUserPassword());
        return new BaseResponse<>(200, resUser, "register success");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public BaseResponse<LoginResponse> doUserLogin(@RequestBody UserLoginRequest params) {
        LoginResponse loginResponse = userService.userLogin(params.getUserAccount(), params.getUserPassword());
        return ResultUtils.success(loginResponse, "login success");
    }

    /**
     * 搜索用户
     */
    @GetMapping("/search")
    public BaseResponse<SearchUserResponse> getUserList(SearchUserRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // 参数搜索
        if (!StringUtils.isBlank(request.getUserName())) {
            userQueryWrapper.like("username", request.getUserName());
        }
        if (!StringUtils.isBlank(request.getNickName())) {
            userQueryWrapper.like("nickName", request.getNickName());
        }
        IPage<User> userPage = userService.page(new Page<>(request.getPageNo(), request.getPageSize()), userQueryWrapper);
        List<User> users = userService.getSafeUserList(userPage);
        return ResultUtils.success(new SearchUserResponse(userPage.getCurrent(),userPage.getTotal(), users));
    }

    /**
     * 删除用户
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DelParams delParams, HttpServletRequest request) {
        if (delParams == null) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        User currentUser = (User) request.getAttribute("currentUser");
        if (!isAdmin(currentUser)) {
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        }
        long userId = delParams.getId();
        if (userId < 0) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        // 不能删除自己
        if (userId == currentUser.getId()) {
            throw new BusinessException(ErrorCodeEnum.CAN_NOT_DEL_YOUR_SELF);
        }
        User targetUser = userService.getById(userId);
        if (targetUser == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND_USER);
        }
        // 逻辑删除
        userService.removeById(userId);
        return ResultUtils.success(null, "删除成功！");
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<User> updateUserInfo(@RequestBody User user, HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        if (isAdmin(currentUser)) {
            throw new BusinessException(ErrorCodeEnum.NO_AUTH);
        }
        Long userId = user.getId();
        if (userId < 0) {
            throw new BusinessException(ErrorCodeEnum.REQUEST_PARAMS_ERROR);
        }
        userService.updateById(user);
        return ResultUtils.success(user, "更新成功！");
    }

    /**
     * 获取当前用户信息
     *
     * @param request
     * @return
     */
    @PostMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        currentUser.setSafeUser(); // 脱敏
        return ResultUtils.success(currentUser, "ok");
    }

    /**
     * 鉴权
     */
    private boolean isAdmin(User user) {
        return RoleConstants.ADMIN_POWER == user.getRole();
    }
}
