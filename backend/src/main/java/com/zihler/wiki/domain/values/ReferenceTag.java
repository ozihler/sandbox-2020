package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.exceptions.IllegalReferenceTagException;

import java.util.Objects;

import static java.lang.String.format;

public class ReferenceTag implements Comparable<ReferenceTag>, Stringifiable {
    private static final String REFERENCE_SYMBOL = "#";
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
        return tag.contains("#") && Patterns.REFERENCE_TAG_MATCHING_REGEX.toPattern().matcher(tag).find();
    }

    public static ReferenceTag from(Title title) {
        return new ReferenceTag(format("%s%s", REFERENCE_SYMBOL, title.toCamelCase()));
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
        return this.tag.toUpperCase().compareTo(referenceTag.tag.toUpperCase());
    }

    @Override
    public String asString() {
        return tag;
    }

    String withoutReferenceSymbol() {
        return tag.replace(REFERENCE_SYMBOL, "");
    }
}
