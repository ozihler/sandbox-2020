import {WikiPageDto} from '../adapter/gateway/create-wiki-page/wiki-page-dto';
import {Title} from './title';
import {Body} from './body';
import {ReferenceTag} from './reference-tag';
import {ReferencedWikiPages} from './referenced-wiki-pages';

export class WikiPage {
  constructor(
    private _referenceTag: ReferenceTag,
    private _title: Title,
    private _body: Body,
    private _referencedWikiPages: ReferencedWikiPages = new ReferencedWikiPages()) {
  }

  get referenceTag(): ReferenceTag {
    return this._referenceTag;
  }

  set referenceTag(value: ReferenceTag) {
    this._referenceTag = value;
  }

  get title(): Title {
    return this._title;
  }

  set title(value: Title) {
    this._title = value;
  }

  get body(): Body {
    return this._body;
  }

  set body(value: Body) {
    this._body = value;
  }

  get referencedWikiPages(): ReferencedWikiPages {
    return this._referencedWikiPages;
  }

  set referencedWikiPages(value: ReferencedWikiPages) {
    this._referencedWikiPages = value;
  }

  static new(referenceTag: string, title: string, body: string, referencedWikiPages: string[]): WikiPage {
    return new WikiPage(
      ReferenceTag.from(referenceTag),
      Title.from(title),
      Body.from(body),
      ReferencedWikiPages.from(referencedWikiPages)
    );
  }

  static from(response: WikiPageDto): WikiPage {
    return new WikiPage(
      ReferenceTag.from(response.referenceTag),
      Title.from(response.title),
      Body.from(response.body),
      ReferencedWikiPages.from(response.referencedWikiPages)
    );
  }

  static empty(): WikiPage {
    return new WikiPage(ReferenceTag.empty(), Title.empty(), Body.empty());
  }

  asDto(): {} {
    return {
      referencedWikiPages: this.referencedWikiPages.referencedWikiPages.map(r => r.referenceTag),
      referenceTag: this.referenceTag.referenceTag,
      title: this.title.title,
      body: this.body.body
    };
  }
}
