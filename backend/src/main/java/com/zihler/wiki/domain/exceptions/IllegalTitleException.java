package com.zihler.wiki.domain.exceptions;

public class IllegalTitleException extends RuntimeException {
    public IllegalTitleException(String message) {
        super(message);
    }
}
