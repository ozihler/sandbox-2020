package com.zihler.wiki.domain.values;

import java.util.ArrayList;
import java.util.List;

import static com.zihler.wiki.domain.values.Patterns.NUMBERS;
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

    private static String toUpperCase(String token) {
        return token.substring(0, 1).toUpperCase() + (token.length() > 1 ? token.substring(1) : "");
    }

    String toCamelCase() {
        List<String> upperCaseTokens = new ArrayList<>();
        String[] nonNumberTokens = token.split(NUMBERS.toString());
        for (String nonNumberToken : nonNumberTokens) {
            if (!nonNumberToken.isBlank()) {
                upperCaseTokens.add(toUpperCase(nonNumberToken));
            }
        }

        String camelCaseToken = token;
        for (String nonNumberToken : nonNumberTokens) {
            for (String token : upperCaseTokens) {
                if (nonNumberToken.equalsIgnoreCase(token)) {
                    camelCaseToken = camelCaseToken.replace(nonNumberToken, token);
                }
            }
        }

        return camelCaseToken;
    }

    private boolean isUpperCase() {
        return UPPER_CASE_LETTERS_REGEX.toPattern().matcher(token).find();
    }

    Token withHeadingSpace() {
        if (isUpperCase()) {
            return from(" " + this);
        }

        return this;
    }

    @Override
    public String toString() {
        return token;
    }
}
