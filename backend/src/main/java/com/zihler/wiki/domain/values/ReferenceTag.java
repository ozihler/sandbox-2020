package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.exceptions.IllegalReferenceTagException;

import java.util.Objects;

import static com.zihler.wiki.domain.values.Patterns.REFERENCE_TAG_MATCHING_REGEX;

public class ReferenceTag implements Comparable<ReferenceTag> {
    public static final String REFERENCE_SYMBOL = "#";
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
    public String toString() {
        return tag;
    }
}
