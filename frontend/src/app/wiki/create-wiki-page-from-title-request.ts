import {Title} from "./wiki-page/domain/values/title";

export class CreateWikiPageFromTitleRequest {
  title: string;

  constructor(title: Title) {
    this.title = title.title;
  }
}
