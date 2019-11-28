import {WikiPageUpdateResponse} from "./wiki-page-update-response";

export class WikiPage {
  private id: number;
  private title: string;
  private body: string;
  private referenceTags: [];

  constructor(id: number, title: string, body: string, referenceTags: []) {
    this.id = id;
    this.title = title;
    this.body = body;
    this.referenceTags = referenceTags;
  }


  static from(response: WikiPageUpdateResponse): WikiPage {
    return new WikiPage(response.id, response.title, response.body, response.referenceTags);
  }
}
