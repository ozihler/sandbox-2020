package com.zihler.wiki.domain.values;

public class Token {
    private String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token from(String token) {
        return new Token(token);
    }

    public String toCamelCase() {
        return firstCharacter().toUpperCase() + tail();
    }

    private String tail() {
        return token.length() > 1 ? token.substring(1) : "";
    }

    private String firstCharacter() {
        return token.substring(0, 1);
    }
}
