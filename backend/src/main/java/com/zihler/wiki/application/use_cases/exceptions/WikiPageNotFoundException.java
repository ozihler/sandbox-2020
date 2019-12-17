package com.zihler.wiki.application.use_cases.exceptions;

public class WikiPageNotFoundException extends RuntimeException {
    public WikiPageNotFoundException(String message) {
        super(message);
    }
}
