package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateway.FindWikiPageByTitle;
import com.zihler.wiki.application.outbound_ports.gateway.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.create_wiki_page.CreateWikiPageUseCase;
import com.zihler.wiki.domain.values.ReferenceTag;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateWikiPageTest {

    @Test
    @DisplayName("A wiki page can be created from a title")
    void test_create_wiki_page_from_title() {
        TestPresenter presenter = new TestPresenter();

        String title = "mySuper stupidTitle";
        InMemoryWikiPageRepository repo = new InMemoryWikiPageRepository();
        FindWikiPageByTitle findByTitle = repo::having;
        StoreWikiPage storeWikiPage = repo::as;

        new CreateWikiPageUseCase(findByTitle, storeWikiPage).from(Title.from(title), presenter);

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
