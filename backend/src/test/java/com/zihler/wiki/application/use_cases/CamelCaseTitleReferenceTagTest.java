package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.CamelCaseTitle;
import com.zihler.wiki.application.use_cases.create_single_wiki_page.roles.CamelCaseTitleReferenceTag;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CamelCaseTitleReferenceTagTest {

    @Test
    @DisplayName("Can create a reference tag from a title")
    void test_create_reference_tag_from_title() {
        Title title = Title.from(" title with%& ç? whit33espaces, commas2,2 and 2other ;:-/ things... -,;:_£}][{[[ mySuper stupidTitle");
        CamelCaseTitle camelCaseTitle = CamelCaseTitle.from(title);
        CamelCaseTitleReferenceTag camelCaseTitleReferenceTag = CamelCaseTitleReferenceTag.from(camelCaseTitle);

        ReferenceTag actualReferenceTag = camelCaseTitleReferenceTag.get();
        ReferenceTag expectedReferenceTag = ReferenceTag.from("#TitleWithWhit33EspacesCommas22And2OtherThingsMySuperStupidTitle");

        assertThat(expectedReferenceTag)
                .isEqualTo(actualReferenceTag);
    }
}
