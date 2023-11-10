package com.pan.demo.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 代表禁止访问资源
 *
 * @author panjb
 */
public class AccessDeniedException extends AbstractException {

    public AccessDeniedException(String message, String code) {
        super(message, HttpStatus.FORBIDDEN, code);
    }

    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
