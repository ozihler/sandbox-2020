package com.zihler.sample.domain.values;

public class HelloWorldDocument {
    private String message;

    public HelloWorldDocument(String message) {
        this.message = message;
    }

    public static HelloWorldDocument from(HelloWorld helloWorld) {
        return new HelloWorldDocument(helloWorld.message());
    }

    public String message() {
        return message;
    }
}
