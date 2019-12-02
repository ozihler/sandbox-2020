import {Component, OnInit} from '@angular/core';
import {WikiPage} from "./wiki-page";
import {CreateWikiPage} from "./create-wiki-page.use-case";
import {CreateWikiPageService} from "./create-wiki-page.service";

@Component({
  selector: 'app-wiki',
  templateUrl: './wiki.component.html',
  providers: [{provide: CreateWikiPage, useClass: CreateWikiPageService}]
})
export class WikiComponent implements OnInit {
  private wikiPageViewModel: WikiPage = WikiPage.empty();


  constructor(private createWikiPage: CreateWikiPage) {
  }

  ngOnInit() {
  }

  updateTitle($event: Event): void {

  }

  updateBody($event: KeyboardEvent) {

  }
}
