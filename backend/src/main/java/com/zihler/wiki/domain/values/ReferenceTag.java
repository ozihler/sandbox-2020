package com.zihler.wiki.domain.values;

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
}
