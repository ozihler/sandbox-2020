import {Component, OnInit} from '@angular/core';
import {WikiPageUpdateRequest} from "./wiki-page-update-request";
import {FormControl, FormGroup} from "@angular/forms";
import {CreateWikiPageService} from "../create-wiki-page.service";

@Component({
  selector: 'app-wiki',
  templateUrl: './wiki.component.html',
  styleUrls: ['./wiki.component.css']
})
export class WikiComponent implements OnInit {
  wikiPage: FormGroup = new FormGroup({
    title: new FormControl(''),
    body: new FormControl('')
  });

  constructor(private createWikiPageService: CreateWikiPageService) {
  }

  ngOnInit() {
  }

  update($event: Event) {
    console.warn("Event occured", $event);
    console.warn(this.wikiPage.value);

    let wikiPageUpdateRequest = new WikiPageUpdateRequest(
      this.wikiPage.controls.title.value,
      this.wikiPage.controls.body.value);

    this.createWikiPageService.updateWith(wikiPageUpdateRequest);
  }
}
