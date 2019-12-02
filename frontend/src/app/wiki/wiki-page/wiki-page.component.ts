import {Component, OnInit} from '@angular/core';
import {WikiPage} from "../wiki-page";

@Component({
  selector: 'app-wiki-page',
  template: `
      <app-wiki-page-title
              [title]="wikiPage.title"
              [referenceTag]="wikiPage.referenceTag"
              (wikiPageCreated)="wikiPageCreated($event)">
      </app-wiki-page-title>`
})
export class WikiPageComponent implements OnInit {
  private wikiPage: WikiPage = WikiPage.empty();

  constructor() {
  }

  ngOnInit() {
  }

  wikiPageCreated(wikiPage: WikiPage) {
    console.log("Received wiki page: ", wikiPage);
    this.wikiPage = wikiPage;
  }
}
