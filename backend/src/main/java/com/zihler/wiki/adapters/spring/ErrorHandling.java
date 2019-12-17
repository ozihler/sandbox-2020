package com.zihler.wiki.adapters.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@ControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDto> handle(Exception bad) {
        return ResponseEntity.status(NOT_IMPLEMENTED).body(new ErrorDto(bad.getMessage()));
    }

}
