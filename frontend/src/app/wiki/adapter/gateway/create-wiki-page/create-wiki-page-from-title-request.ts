import {Title} from "../../../domain/title";

export class CreateWikiPageFromTitleRequest {
  title: string;

  constructor(title: Title) {
    this.title = title.title;
  }
}
