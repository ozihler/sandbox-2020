package com.zihler.wiki.application.use_cases;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.Presenter;
import com.zihler.wiki.application.use_cases.create_wiki_page.CreateWikiPageUseCase;
import com.zihler.wiki.application.use_cases.create_wiki_page.inbound_ports.CreateWikiPage;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.CamelCaseTitle;
import com.zihler.wiki.application.use_cases.create_wiki_page.roles.TitleReferenceTag;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateWikiPageTest {


    @Test
    @DisplayName("A wiki page can be created from a title")
    void test_create_wiki_page_from_title() {

        InMemoryWikiPageRepository repo = new InMemoryWikiPageRepository();

        CreateWikiPage createWikiPage = new CreateWikiPageUseCase(repo, repo);

        Title title = Title.from("mySuper stupidTitle");
        TestPresenter presenter = new TestPresenter();

        createWikiPage.from(title, presenter);

        WikiPageDocument document = presenter.getDocument();

        assertThat(document)
                .extracting(WikiPageDocument::getReferenceTag)
                .isEqualTo(TitleReferenceTag.from(CamelCaseTitle.from(title)).get());

        assertThat(document)
                .extracting(WikiPageDocument::getTitle)
                .isEqualTo(title);

        assertThat(document)
                .extracting(WikiPageDocument::getBody)
                .isEqualTo(BodyDocument.from(Body.empty()));
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
