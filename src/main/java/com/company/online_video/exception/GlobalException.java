package com.company.online_video.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(ApiExceptionResponse.class)
    public ResponseEntity<?> handleApiException(ApiExceptionResponse exceptionResponse){
        return ResponseEntity.status(exceptionResponse.getStatus()).body(exceptionResponse.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        e.printStackTrace();
        log.error("From exception :: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sorry, It has something wrong. Please contact to developers");
    }


}
