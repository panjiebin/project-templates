package com.pan.demo.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 代表请求参数错误
 *
 * @author panjb
 */
public class BadRequestException extends AbstractException{

    public BadRequestException(String message, String code) {
        super(message, HttpStatus.BAD_REQUEST, code);
    }

    public BadRequestException(String message) {
        super(message,  HttpStatus.BAD_REQUEST);
    }
}
