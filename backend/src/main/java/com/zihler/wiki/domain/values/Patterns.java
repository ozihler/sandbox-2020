package com.zihler.wiki.domain.values;

import java.util.regex.Pattern;

public enum Patterns {
    NON_CHARACTER_TOKEN_REGEX("[^a-zA-Z0-9]"), REFERENCE_TAG_MATCHING_REGEX("(.*(?<!.)#[A-Z0-9]+[a-z0-9]*)+");

    private String regexPattern;

    Patterns(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    @Override
    public String toString() {
        return regexPattern;
    }

    public Pattern toPattern() {
        return Pattern.compile(regexPattern);
    }
}
