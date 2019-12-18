import {ReferenceTag} from './reference-tag';

export class ReferencedWikiPages {
  constructor(private _referencedWikiPages: ReferenceTag[] = []) {
  }

  get referencedWikiPages(): ReferenceTag[] {
    return this._referencedWikiPages;
  }

  set referencedWikiPages(value: ReferenceTag[]) {
    this._referencedWikiPages = value;
  }

  static from(referencedWikiPages: string[]) {
    return new ReferencedWikiPages(referencedWikiPages.map(referenceTagString => ReferenceTag.from(referenceTagString)));
  }

  static empty() {
    return new ReferencedWikiPages();
  }
}
