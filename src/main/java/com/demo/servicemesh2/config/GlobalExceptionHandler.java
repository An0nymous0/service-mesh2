package com.demo.servicemesh2.config;

import com.demo.servicemesh2.common.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Log4j2
/**
 * https://stackoverflow.com/questions/12806386/standard-json-api-response-format
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error(e.getMessage(), e);
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return ResponseMessage.error(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } else {
            return ResponseMessage.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}

