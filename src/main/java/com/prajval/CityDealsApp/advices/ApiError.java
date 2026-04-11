package com.prajval.CityDealsApp.advices;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiError {

    private String message;
    private LocalDateTime timeStamp;
    private HttpStatusCode httpStatusCode;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }
    public ApiError(String message, HttpStatusCode httpStatusCode) {
        this();
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
