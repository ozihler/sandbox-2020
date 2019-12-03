package com.zihler.wiki.domain.values;

import java.util.Arrays;
import java.util.List;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Tokens {
    private final List<Token> tokens;

    private Tokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public static Tokens from(Stringifiable value) {
        return new Tokens(tokenize(value));
    }

    private static List<Token> tokenize(Stringifiable value) {
        return Arrays.stream(value.asString().split(NON_CHARACTER_TOKEN_REGEX.toString()))
                .filter(token -> !token.isBlank())
                .map(Token::from)
                .collect(toList());
    }

    String toCamelCase() {
        return tokens.stream()
                .map(Token::toCamelCase)
                .collect(joining());
    }

}
