package com.zihler.wiki.domain.values;

import java.util.Objects;

public class ReferenceTag {
    private final String tag;

    private ReferenceTag(String tag) {
        this.tag = tag;
    }

    public static ReferenceTag from(String tag) {
        return new ReferenceTag(tag);
    }

    public boolean isValid() {
        return !isInvalid();
    }

    private boolean isInvalid() {
        return tag == null && tag.isBlank() && tag.contains(" ") && tag.contains("\\n") && !tag.contains("#");
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
}
