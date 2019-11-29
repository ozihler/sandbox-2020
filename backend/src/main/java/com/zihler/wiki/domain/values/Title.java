package com.zihler.wiki.domain.values;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static com.zihler.wiki.utils.WordSplitterUtils.NON_CHARACTER_TOKENS;
import static java.util.stream.Collectors.joining;

public class Title {
    private String title;

    private Title(String title) {

        this.title = title;
    }

    public static Title from(String title) {
        return new Title(title);
    }

    public static Title empty() {
        return new Title("");
    }

    static String toCamelCase(Title title) {
        return title.toWordTokens()
                .filter(token -> !token.isBlank())
                .map(Title::toCamelCase)
                .collect(joining());
    }

    private static String toCamelCase(String token) {
        return firstCharacterOf(token).toUpperCase() + restOf(token);
    }

    private static String restOf(String token) {
        return token.length() > 1 ? token.substring(1) : "";
    }

    private static String firstCharacterOf(String token) {
        return token.substring(0, 1);
    }

    private Stream<String> toWordTokens() {
        return Arrays.stream(title.split(NON_CHARACTER_TOKENS));
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
