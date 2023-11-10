package com.pan.demo.framework.aop;

import com.pan.demo.framework.ExceptionResponseEntity;
import com.pan.demo.framework.exception.AbstractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author panjb
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
    * 处理参数校验异常
    */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseEntity> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ExceptionResponseEntity respBody = new ExceptionResponseEntity(HttpStatus.BAD_REQUEST.getReasonPhrase());
        List<String> errorMessageList = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            if (!StringUtils.hasText(error.getDefaultMessage())) {
                continue;
            }
            errorMessageList.add(error.getDefaultMessage());
        }
        respBody.setMessage(errorMessageList.stream().distinct().collect(Collectors.joining(";")));
        if (logger.isErrorEnabled()) {
            logger.error(respBody.getMessage(), e);
        }
        return new ResponseEntity<>(respBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = AbstractException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseEntity> customDefineExceptionHandler(AbstractException e) {
        ExceptionResponseEntity respBody = new ExceptionResponseEntity(e.getCode(), e.getMessage());
        if (logger.isErrorEnabled()) {
            logger.error(respBody.getMessage(), e);
        }
        return new ResponseEntity<>(respBody, e.getStatus());
    }

    /**
     * 处理默认异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponseEntity> defaultExceptionHandler(Exception e) {
        ExceptionResponseEntity respBody = new ExceptionResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
        if (logger.isErrorEnabled()) {
            logger.error(respBody.getMessage(), e);
        }
        return new ResponseEntity<>(respBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
