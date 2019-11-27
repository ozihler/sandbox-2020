package com.zihler.wiki.use_case;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.domain.values.*;
import com.zihler.wiki.use_case.outbound_ports.WikiPagePresenter;
import com.zihler.wiki.use_case.outbound_ports.WikiPageRepository;
import org.junit.jupiter.api.Test;

import static com.zihler.wiki.domain.values.DetailsBuilder.newWikiPageDetails;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateWikiPagesTest {

    @Test
    void create_wiki_page__creates_new_wiki_page() {
        Details details = newWikiPageDetails(ReferenceTag.from("#MyTestWikiPage"))
                .withTitle(Title.from("My Test Title"))
                .withBody(Body.from("# my test body"))
                .build();

        TestWikiPagePresenter testPresenter = new TestWikiPagePresenter();

        WikiPageRepository repo = new InMemoryWikiPageRepository();

        CreateWikiPages createWikiPages = new CreateWikiPages(repo);

        createWikiPages.from(details, testPresenter);

        assertTrue(testPresenter.document.id().longValue() > 0L);
        //assertEquals(details.asDocument().toString(), testPresenter.document.toString());
    }

    static class TestWikiPagePresenter implements WikiPagePresenter {
        WikiPageDocument document;

        @Override
        public void present(WikiPageDocument document) {
            this.document = document;
        }
    }

}
