package com.pan.demo.framework;

/**
 * 异常响应body
 *
 * @author panjb
 */
public class ExceptionResponseEntity {

    private String code;

    private String message;

    public ExceptionResponseEntity() {
    }

    public ExceptionResponseEntity(String code) {
        this.code = code;
    }

    public ExceptionResponseEntity(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
