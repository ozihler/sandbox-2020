package com.zihler.wiki.domain.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReferenceTagTest {

    @Test
    @DisplayName("Can create a reference tag from a title")
    void test_create_reference_tag_from_title() {
        ReferenceTag referenceTag = ReferenceTag.from(Title.from(" title with%& ç? whitespaces, commas, and other ;:-/ things... -,;:_£}][{[["));

        assertEquals(ReferenceTag.from("#TitleWithWhitespacesCommasAndOtherThings"), referenceTag);
    }
}
