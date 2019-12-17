import {WikiPage} from '../../../domain/wiki-page';

export class WikiPagesDto {
  referenceTag: string;
  title: string;
  body: string;

  constructor(wikiPage: WikiPage) {
    this.referenceTag = wikiPage.referenceTag.referenceTag;
    this.title = wikiPage.title.title;
    this.body = wikiPage.body.body;
  }

}
