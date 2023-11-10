package com.pan.demo.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 未授权错误
 *
 * @author panjb
 */
public class UnauthorizedException extends AbstractException{

    public UnauthorizedException(String message, String code) {
        super(message, HttpStatus.UNAUTHORIZED, code);
    }

    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
