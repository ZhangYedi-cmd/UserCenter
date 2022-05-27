package com.zhang.base.response;
/**
 * 响应工具类
 * */
public class ResultUtils {

    /**
     * 返回正常请求
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(200, data, "ok");
    }

    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(200, data, message);
    }

    /**
     * 返回错误请求
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(int code, String message)  {
        return new BaseResponse<T>(code,null, message);
    }

}
