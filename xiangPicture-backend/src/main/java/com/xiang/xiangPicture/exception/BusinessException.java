package com.xiang.xiangPicture.exception;


import lombok.Getter;

/**
 *@ClassName BusinessException
 *@Description 全局自定义异常类
 *@Author 香香
 *@Date 2024/12/19
 *@Version 1.0
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}
