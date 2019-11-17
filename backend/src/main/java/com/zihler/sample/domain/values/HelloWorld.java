package com.zihler.sample.domain.values;

public class HelloWorld {
    private String message;

    private HelloWorld(String message) {
        this.message = message;
    }

    public static HelloWorld from(String message) {
        return new HelloWorld(message);
    }

    public HelloWorldDocument asDocument() {
        return HelloWorldDocument.from(this);
    }

    public String message() {
        return message;
    }
}
