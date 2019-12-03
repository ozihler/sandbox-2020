package com.zihler.wiki.domain.values;

import static com.zihler.wiki.domain.values.Patterns.UPPER_CASE_LETTERS_REGEX;

public class Token {
    private String token;

    private Token(String token) {
        this.token = token;
    }

    public static Token from(String token) {
        return new Token(token);
    }

    public static Token from(char token) {
        return from("" + token);
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

    private boolean isUpperCase() {
        return UPPER_CASE_LETTERS_REGEX.toPattern().matcher(token).find();
    }

    Token withHeadingSpace() {
        if (!isUpperCase()) {
            return this;
        } else {
            return from(" " + this);
        }
    }

    @Override
    public String toString() {
        return token;
    }
}
