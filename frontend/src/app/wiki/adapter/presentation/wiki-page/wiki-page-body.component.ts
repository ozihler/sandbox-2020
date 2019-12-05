import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';

import {FormControl} from "@angular/forms";
import {ReferenceTag} from "../../../domain/reference-tag";
import {debounceTime, distinctUntilChanged} from "rxjs/operators";
import {Body} from "../../../domain/body";
import {UpdateWikiPageBody} from "../../../application.use_case/update-wiki-page-body.port";
import {UpdateWikiPageBodyGateway} from "../../gateway/update-wiki-page-body/update-wiki-page-body.service";

@Component({
  selector: 'app-wiki-page-body',
  providers: [{provide: UpdateWikiPageBody, useClass: UpdateWikiPageBodyGateway}],
  template: `
      <div>
          <textarea id="wiki-page-body"
                    class="form-control"
                    [formControl]="bodyInput"
                    *ngIf="!referenceTag.isEmpty()"></textarea>

          <span [innerHTML]="body.body"></span>
      </div>`
})
export class WikiPageBodyComponent implements OnChanges {

  @Input() referenceTag: ReferenceTag;
  @Input() body: Body;
  bodyInput: FormControl;

  constructor(private updateWikiPageBody: UpdateWikiPageBody) {
    this.referenceTag = ReferenceTag.empty();
    this.body = Body.empty();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.bodyInput = new FormControl(this.body.body);
    this.bodyInput.valueChanges.pipe(
      debounceTime(2000),
      distinctUntilChanged()
    ).subscribe(res => {
      this.updateWikiPageBody.with(Body.from(res))
        .subscribe(wikiPages => {
          // todo: move to use case, add presenter to format bodyInputValue and assign it to body here
          if (wikiPages.length > 0) {
            wikiPages.forEach(wikiPage => {
              this.body.body = this.bodyInput.value.replace(wikiPage.referenceTag.referenceTag,
                '<a href="#" routerLink="wiki-page" [queryParams]=[wikiPage.toDto()]>' + wikiPage.referenceTag.referenceTag + '</a>');
            });
          } else {
            this.body.body = this.bodyInput.value;
          }
        });
    });
  }
}
