import {Component, OnInit} from '@angular/core';
import {WikiPage} from "./wiki-page";
import {CreateWikiPageFromTitle} from "./create-wiki-page.port";
import {CreateWikiPageFromTitleUseCase} from "./create-wiki-page.service";

@Component({
  selector: 'app-wiki',
  templateUrl: './wiki.component.html',
  providers: [{provide: CreateWikiPageFromTitle, useClass: CreateWikiPageFromTitleUseCase}]
})
export class WikiComponent implements OnInit {
  private wikiPageViewModel: WikiPage = WikiPage.empty();


  constructor(private createWikiPage: CreateWikiPageFromTitle) {
  }

  ngOnInit() {
  }

  updateTitle($event: Event): void {

  }

  updateBody($event: KeyboardEvent) {

  }
}
