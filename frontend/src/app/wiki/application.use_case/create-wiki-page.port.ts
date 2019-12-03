import {Observable} from "rxjs";
import {WikiPage} from "../domain/wiki-page";
import {Title} from "../domain/title";

export abstract class CreateWikiPageFromTitle {
  abstract from(title: Title): Observable<WikiPage>;
}
