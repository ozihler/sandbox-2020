import {Component, OnInit} from '@angular/core';
import {WikiPage} from "../wiki-page";

@Component({
  selector: 'app-wiki-page',
  template: `
      <app-wiki-page-title
              [title]="wikiPage.title"
              [referenceTag]="wikiPage.referenceTag"
              (wikiPageCreated)="updateWikiPage($event)">
      </app-wiki-page-title>
      <app-wiki-page-body
              [body]="wikiPage.body"
              [referenceTag]="wikiPage.referenceTag">
      </app-wiki-page-body>`
})
export class WikiPageComponent implements OnInit {
  private wikiPage: WikiPage = WikiPage.empty();

  constructor() {
  }

  ngOnInit() {
  }

  updateWikiPage(wikiPage: WikiPage) {
    this.wikiPage = wikiPage;
  }
}
