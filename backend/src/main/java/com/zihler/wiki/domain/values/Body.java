package com.zihler.wiki.domain.values;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.zihler.wiki.utils.WordSplitterUtils.NON_CHARACTER_TOKENS;

public class Body {
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

    public ReferenceTags foundReferenceTags() {
        return ReferenceTags.from(this);
    }

    public String get() {
        return body;
    }

    Stream<String> toWordTokens() {
        return Arrays.stream(get().split(NON_CHARACTER_TOKENS));
    }

}
