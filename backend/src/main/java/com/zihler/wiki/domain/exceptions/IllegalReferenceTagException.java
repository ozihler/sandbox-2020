package com.zihler.wiki.domain.exceptions;

public class IllegalReferenceTagException extends RuntimeException {
    public IllegalReferenceTagException(String message) {
        super(message);
    }
}
