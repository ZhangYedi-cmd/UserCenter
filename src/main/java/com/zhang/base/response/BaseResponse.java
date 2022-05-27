package com.zhang.base.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BaseResponse<T> {

    /**
     * 状态码
     */
    private int code;
    /**
     * 数据
     * *
     */
    private T data;
    /**
     * 消息
     */
    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
