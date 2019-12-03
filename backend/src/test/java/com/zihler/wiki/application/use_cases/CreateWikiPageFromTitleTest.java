package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepositoryByTitle;
import com.zihler.wiki.application.use_cases.outbound_ports.BodyDocument;
import com.zihler.wiki.application.use_cases.outbound_ports.IFindWikiPagesByTitle;
import com.zihler.wiki.application.use_cases.outbound_ports.Presenter;
import com.zihler.wiki.application.use_cases.outbound_ports.WikiPageDocument;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateWikiPageFromTitleTest {

    @Test
    @DisplayName("A wiki page can be created from a title")
    void test_create_wiki_page_from_title() {
        TestPresenter presenter = new TestPresenter();

        String title = "mySuper stupidTitle";
        new CreateWikiPageFromTitleUseCase((IFindWikiPagesByTitle) new InMemoryWikiPageRepositoryByTitle(), wikiPageStorage).from(Title.from(title), presenter);

        assertEquals(ReferenceTag.from("#MySuperStupidTitle"), presenter.getDocument().getReferenceTag());
        assertEquals(Title.from(title), presenter.getDocument().getTitle());
        assertEquals(BodyDocument.from(""), presenter.getDocument().getBody());
    }

    private static class TestPresenter implements Presenter<WikiPageDocument> {
        private WikiPageDocument document;

        WikiPageDocument getDocument() {
            return document;
        }

        @Override
        public void present(WikiPageDocument document) {
            this.document = document;
        }
    }
}
