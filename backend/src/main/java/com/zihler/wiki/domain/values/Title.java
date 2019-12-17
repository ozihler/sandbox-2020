package com.zihler.wiki.domain.values;

import com.zihler.wiki.domain.exceptions.IllegalTitleException;

import java.util.Objects;

public class Title implements Stringifiable {
    private String title;

    private Title(String title) {
        this.title = title;

        if (!isValid()) {
            throw new IllegalTitleException("A title cannot be empty!");
        }
    }

    public static Title from(String titleString) {
        return new Title(titleString);
    }

    public static Title from(ReferenceTag referenceTag) {
        Tokens tokens = Tokens.withTrailingWhiteSpaceBeforeEveryUpperCaseLetter(referenceTag.withoutReferenceSymbol());
        return from(tokens.toString());
    }

    private boolean isValid() {
        return !(title == null || title.isBlank());
    }

    @Override
    public String asString() {
        return title;
    }

    @Override
    public String toString() {
        return asString();
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
