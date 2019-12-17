package com.zihler.wiki.adapters.spring;

public class ErrorDto {
    private String message;

    ErrorDto(String message) {
        this.message = "An error occured: " + message;
    }

    public ErrorDto() {
    }

    public String getMessage() {
        return message;
    }
}
