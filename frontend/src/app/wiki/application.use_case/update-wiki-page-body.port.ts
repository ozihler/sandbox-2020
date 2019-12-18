import {WikiPage} from '../domain/wiki-page';
import {Observable} from 'rxjs';


export abstract class UpdateWikiPageBody {
  abstract with(wikiPage: WikiPage): Observable<WikiPage>;
}
