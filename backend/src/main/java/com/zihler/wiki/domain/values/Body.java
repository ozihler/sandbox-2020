package com.zihler.wiki.domain.values;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.zihler.wiki.domain.values.Patterns.NON_CHARACTER_TOKEN_REGEX;

public class Body implements Stringifiable {
    private String body;

    private Body(String body) {
        this.body = body;
    }

    public static Body from(String body) {
        return new Body(body);
    }

    public static Body empty() {
        return new Body("");
    }

    // TODO: 12/6/2019 Move to use case 
    Stream<String> toWordTokens() {
        return Arrays.stream(asString().split(NON_CHARACTER_TOKEN_REGEX.toString()));
    }

    @Override
    public String asString() {
        return body;
    }
}
