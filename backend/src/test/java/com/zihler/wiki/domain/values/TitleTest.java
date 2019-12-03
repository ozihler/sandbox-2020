package com.zihler.wiki.domain.values;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TitleTest {

    @Test
    void test_create_title_from_reference_tag_from_title() {
        Title title = Title.from("thi22s I3s a 2Ti,,tle");

        ReferenceTag referenceTag = ReferenceTag.from(title);

        Title title2 = Title.from(referenceTag);
        assertEquals(title.toCamelCase(), title2.toCamelCase());
    }


    @Test
    void test_create_title_from_reference_tag() {

        ReferenceTag referenceTag = ReferenceTag.from("#HelloWorld");

        Title title2 = Title.from(referenceTag);

        System.out.println(referenceTag);
    }
}
