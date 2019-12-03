import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {CreateWikiPageFromTitle} from "../../create-wiki-page.port";
import {CreateWikiPageFromTitleUseCase} from "../../create-wiki-page.service";
import {FormControl} from "@angular/forms";
import {Title} from "../domain/values/title";
import {WikiPage} from "../../wiki-page";
import {ReferenceTag} from "../domain/values/reference-tag";

@Component({
  selector: 'app-wiki-page-title',
  providers: [{provide: CreateWikiPageFromTitle, useClass: CreateWikiPageFromTitleUseCase}],
  template: `
      <div>
          <h3>{{referenceTag.referenceTag}}</h3>

          <input id="wiki-page-title"
                 class="form-control"
                 [formControl]="titleInput"/>

          <button id="create-wiki-page-from-title-button"
                  *ngIf="referenceTag.isEmpty()"
                  (click)="createNewWikiPage($event)">create
          </button>
      </div>`
})
export class WikiPageTitleComponent implements OnChanges {
  @Input() title: Title = Title.empty();
  @Input() referenceTag: ReferenceTag = ReferenceTag.empty();
  @Output() wikiPageCreated = new EventEmitter<WikiPage>();

  titleInput: FormControl;

  constructor(private createWikiPage: CreateWikiPageFromTitle) {
  }

  createNewWikiPage($event: MouseEvent) {
    if (!this.hasTitle()) {
      return;
    }

    this.createWikiPage.from(Title.from(this.titleInput.value))
      .subscribe(wikiPage => {
        this.wikiPageCreated.emit(wikiPage);
      });
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.titleInput = new FormControl({
      value: this.title.title,
      disabled: !this.referenceTag.isEmpty()
    });
  }

  private hasTitle() {
    return this.titleInput.value.trim().length > 0;
  }

}
