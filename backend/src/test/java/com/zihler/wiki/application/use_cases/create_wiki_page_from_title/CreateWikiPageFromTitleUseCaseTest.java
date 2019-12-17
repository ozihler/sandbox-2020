package com.zihler.wiki.application.use_cases.create_wiki_page_from_title;

import com.zihler.wiki.adapters.data_access.in_memory.InMemoryWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.BodyDocument;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.presenter.WikiPagePresenter;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.inbound_ports.CreateWikiPageFromTitle;
import com.zihler.wiki.application.use_cases.create_wiki_page_from_title.roles.TitleBasedReferenceTag;
import com.zihler.wiki.domain.values.Body;
import com.zihler.wiki.domain.values.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreateWikiPageFromTitleUseCaseTest {
    @Test
    @DisplayName("A wiki page can be created from a title")
    void test_create_wiki_page_from_title() {
        InMemoryWikiPageRepository repo = new InMemoryWikiPageRepository();

        CreateWikiPageFromTitle createWikiPageFromTitle = new CreateWikiPageFromTitleUseCase(repo, repo);

        Title title = Title.from("mySuper stupidTitle");
        TestPresenter presenter = new TestPresenter();

        createWikiPageFromTitle.from(title, presenter);

        WikiPageDocument document = presenter.getDocument();

        assertThat(document)
                .extracting(WikiPageDocument::getReferenceTag)
                .isEqualTo(TitleBasedReferenceTag.from(title).get());

        assertThat(document)
                .extracting(WikiPageDocument::getTitle)
                .isEqualTo(title);

        assertThat(document)
                .extracting(WikiPageDocument::getBody)
                .isEqualTo(BodyDocument.from(Body.empty()));
    }

    private static class TestPresenter implements WikiPagePresenter {
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
