package com.company.online_video.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ApiBaseResponse <T>{

    private T data;
    private String message;
    private HttpStatus status;
    private String dateTime;

    public ApiBaseResponse() {
        this.dateTime = setDateTime();
    }

    private String setDateTime() {
        // create an LocalDateTime object
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(format);
    }
}
