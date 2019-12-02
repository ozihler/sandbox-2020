import {Component, OnInit} from '@angular/core';
import {CreateWikiPageFromTitleRequest} from "./create-wiki-page-from-title-request";
import {FormControl, FormGroup} from "@angular/forms";
import {WikiPage} from "./wiki-page";
import {CreateWikiPage} from "./create-wiki-page.use-case";
import {CreateWikiPageService} from "./create-wiki-page.service";

@Component({
  selector: 'app-wiki',
  templateUrl: './wiki.component.html',
  styleUrls: ['./wiki.component.css'],
  providers: [{provide: CreateWikiPage, useClass: CreateWikiPageService}]
})
export class WikiComponent implements OnInit {
  wikiPageInputForm: FormGroup = new FormGroup({
    title: new FormControl(''),
    body: new FormControl('')
  });

  private wikiPageViewModel: WikiPage = WikiPage.empty();


  constructor(private createWikiPage: CreateWikiPage) {
  }

  ngOnInit() {
  }

  updateTitle($event: Event): void {
    console.warn("Update title event occured", $event);

    if (this.wikiPageInputForm.controls.title.value.trim().length == 0) {
      return;
    }

    let wikiPageUpdateRequest = new CreateWikiPageFromTitleRequest(this.wikiPageInputForm.controls.title.value);

    this.createWikiPage.fromTitle(wikiPageUpdateRequest)
      .subscribe(response => {
        this.wikiPageViewModel = response;
      });
  }

  updateBody($event: KeyboardEvent) {

  }
}
