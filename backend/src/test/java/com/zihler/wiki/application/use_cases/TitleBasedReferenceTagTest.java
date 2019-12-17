package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.TitleBasedReferenceTag;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TitleBasedReferenceTagTest {

    @Test
    @DisplayName("Can create a reference tag from a title")
    void test_create_reference_tag_from_title() {
        Title title = Title.from(" title with%& ç? whit33espaces, commas2,2 and 2other ;:-/ things... -,;:_£}][{[[ mySuper stupidTitle");
        TitleBasedReferenceTag titleBasedReferenceTag = TitleBasedReferenceTag.from(title);

        ReferenceTag actualReferenceTag = titleBasedReferenceTag.get();
        ReferenceTag expectedReferenceTag = ReferenceTag.from("#TitleWithWhit33EspacesCommas22And2OtherThingsMySuperStupidTitle");

        assertThat(expectedReferenceTag)
                .isEqualTo(actualReferenceTag);
    }
}
