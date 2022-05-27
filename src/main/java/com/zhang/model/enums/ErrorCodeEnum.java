package com.zhang.model.enums;

public enum ErrorCodeEnum {

    REQUEST_PARAMS_ERROR(400, "请求参数错误"),

    NULL_PARAMS(400, "请求参数为空"),

    SYSTEM_ERROR(500, "系统错误"),

    NO_AUTH(403, "无权限"),

    FILE_UPLOAD_ERROR(500, "文件上传错误"),

    PWD_ERROR(400, "密码错误"),

    PWD_LENGTH_ERROR(400, "密码长度必须大于八位"),

    ACCOUNT_LENGTH_ERROR(400, "登录账号长度必须在4位以上"),

    ACCOUNT_WRONGFUL(400, "账户名中不能包含特殊字符"),

    USER_HAS_REGISTER(400, "该用户已经注册过了"),

    USER_NOT_LOGIN(400, "用户未登录"),

    CAN_NOT_DEL_YOUR_SELF(400, "用户未登录"),

    NOT_FOUND_USER(400, "用户不存在");


    private final int code;

    private final String message;


    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
