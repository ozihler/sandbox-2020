package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.exceptions.IllegalTitleException;

import java.util.Objects;

public class Title implements Stringifiable {
    private String title;

    private Title(String title) {
        this.title = title;
    }

    public static Title from(String titleString) {
        Title title = new Title(titleString);

        if (!title.isValid()) {
            throw new IllegalTitleException("A title cannot be empty!");
        }

        return title;
    }

    public static Title from(ReferenceTag referenceTag) {
        Tokens tokens = Tokens.withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(referenceTag);
        return from(tokens.toString());
    }

    String toCamelCase() {
        return Tokens.from(this).toCamelCase();
    }

    @Override
    public String asString() {
        return title;
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

    private boolean isValid() {
        return !(title == null || title.isBlank());
    }
}
