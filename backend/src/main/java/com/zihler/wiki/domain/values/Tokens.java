package com.zihler.wiki.domain.values;

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

    public static Tokens from(Stringifiable value) {
        return from(tokenizeByNonCharacterTokens(value));
    }

    private static List<Token> tokenizeByNonCharacterTokens(Stringifiable value) {
        return Arrays.stream(value.asString().split(NON_CHARACTER_TOKEN_REGEX.toString()))
                .filter(token -> !token.isBlank())
                .map(Token::from)
                .collect(toList());
    }

    static Tokens withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(ReferenceTag referenceTag) {
        String referenceTagString = referenceTag.withoutReferenceSymbol();

        List<Token> tokens = IntStream.range(0, referenceTagString.length())
                .mapToObj(i -> Token.from(referenceTagString.charAt(i)))
                .map(Token::withHeadingSpace)
                .collect(toList());

        return Tokens.from(tokens);
    }

    String toCamelCase() {
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
