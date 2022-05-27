package com.zhang.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.zhang.model.enums.ErrorCodeEnum;
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private final int code;

    private final String message;

    private String description;

    public BusinessException(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String description) {
        this(errorCodeEnum.getCode(), errorCodeEnum.getMessage(), description);
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
        this.description = "";
    }

}
