package com.company.online_video.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ApiExceptionResponse extends RuntimeException{
    private String message;
    private HttpStatus status;
}
