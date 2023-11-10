package com.pan.demo.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 代表不存在错误
 *
 * @author panjb
 */
public class NotFoundException extends AbstractException{

    public NotFoundException(String message, String code) {
        super(message, HttpStatus.NOT_FOUND, code);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
