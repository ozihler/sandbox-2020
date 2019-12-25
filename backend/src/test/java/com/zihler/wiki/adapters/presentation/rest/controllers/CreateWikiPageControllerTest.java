package com.zihler.wiki.adapters.presentation.rest.controllers;

import com.zihler.wiki.adapters.data_access.persistence.in_memory.HashMapWikiPageRepository;
import com.zihler.wiki.application.outbound_ports.documents.WikiPageDocument;
import com.zihler.wiki.application.outbound_ports.gateways.FindWikiPage;
import com.zihler.wiki.application.outbound_ports.gateways.StoreWikiPage;
import com.zihler.wiki.application.outbound_ports.presenters.WikiPagePresenter;
import com.zihler.wiki.domain.exceptions.IllegalTitleException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateWikiPageControllerTest {

    private WikiPageDocument wikiPage;

    @Test
    void test() {
        var repo = new HashMapWikiPageRepository();

        StoreWikiPage s = repo::save;
        FindWikiPage r = repo::findBy;
        var controller = new CreateWikiPageController(r, s);
        WikiPagePresenter out = wikiPage -> this.wikiPage = wikiPage;

        controller.createWikiPage("Title 123", out);

        assertEquals("Title 123", wikiPage.title().toString());
        assertEquals("", wikiPage.body().toString());
        assertEquals("#Title123", wikiPage.referenceTag().toString());
    }

    @Test
    void testInvalidTitle() {
        var controller = new CreateWikiPageController(null, null);
        assertThrows(IllegalTitleException.class,
                () -> controller.createWikiPage(null, null));
    }
}
