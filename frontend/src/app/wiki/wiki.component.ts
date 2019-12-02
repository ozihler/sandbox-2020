import {Component, OnInit} from '@angular/core';
import {CreateWikiPageFromTitleRequest} from "./create-wiki-page-from-title-request";
import {FormControl, FormGroup} from "@angular/forms";
import {CreateWikiPageService} from "./create-wiki-page.service";
import {WikiPage} from "./wiki-page";

@Component({
  selector: 'app-wiki',
  templateUrl: './wiki.component.html',
  styleUrls: ['./wiki.component.css']
})
export class WikiComponent implements OnInit {
  wikiPageInputForm: FormGroup = new FormGroup({
    title: new FormControl(''),
    body: new FormControl('')
  });

  private wikiPageViewModel: WikiPage = WikiPage.empty();


  constructor(private createWikiPage: CreateWikiPageService) {
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
