package com.zihler.wiki.application.use_cases.wiki_page_creation_utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Tokens {
    private final List<Token> tokens;

    private Tokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    private static Tokens from(List<Token> tokens) {
        return new Tokens(tokens);
    }

    public static Tokens from(Object value) {
        var tokens = tokenizeByNonCharacterTokens(value);
        return from(tokens);
    }

    private static List<Token> tokenizeByNonCharacterTokens(Object value) {
        String[] characters = value.toString().split(NON_CHARACTER_TOKEN_REGEX.toString());

        return Arrays.stream(characters)
                .filter(token -> !token.isBlank())
                .map(Token::from)
                .collect(toList());
    }

    public static Tokens withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(String value) {

        List<Token> tokens = IntStream.range(0, value.length())
                .mapToObj(i -> Token.from(value.charAt(i)))
                .map(Token::withHeadingSpace)
                .collect(toList());

        return Tokens.from(tokens);
    }

    public String toCamelCase() {
        return tokens.stream()
                .map(Token::toCamelCase)
                .collect(joining());
    }

    @Override
    public String toString() {
        return tokens.stream()
                .map(Token::toString)
                .collect(joining())
                .trim();
    }

}
