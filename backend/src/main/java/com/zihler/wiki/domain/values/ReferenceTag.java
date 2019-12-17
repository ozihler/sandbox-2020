package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.exceptions.IllegalReferenceTagException;

import java.util.Objects;

import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;

public class ReferenceTag implements Comparable<ReferenceTag>, Stringifiable {
    private final String tag;

    private ReferenceTag(String tag) {
        this.tag = tag;
    }

    public static ReferenceTag from(String tag) {
        if (!isValid(tag.trim())) {
            throw new IllegalReferenceTagException("Incorrect reference tag: " + tag);
        }
        return new ReferenceTag(tag);
    }

    private static boolean isValid(String tag) {
        return tag.contains("#") && REFERENCE_TAG_MATCHING_REGEX.toPattern().matcher(tag).find();
    }

    @Override
    public String toString() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceTag that = (ReferenceTag) o;
        return Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }

    @Override
    public int compareTo(ReferenceTag referenceTag) {
        return tag.toUpperCase().compareTo(referenceTag.tag.toUpperCase());
    }

    @Override
    public String asString() {
        return tag;
    }

    // TODO: 12/6/2019 Maybe move to use case?
    String withoutReferenceSymbol() {
        return tag.replace("#", "");
    }
}
