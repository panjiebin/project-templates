package com.pan.demo.framework.exception;

import org.springframework.http.HttpStatus;

/**
 * 自定义异常基类
 *
 * @author panjb
 */
public abstract class AbstractException extends RuntimeException {
    private final HttpStatus status;
    /**
     * 异常提示消息
     */
    private final String code;

    public AbstractException(String message, HttpStatus status, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public AbstractException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.code = status.getReasonPhrase();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
