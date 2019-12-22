import {Injectable} from '@angular/core';
import {environment} from '../../../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {WikiPage} from '../../../domain/wiki-page';
import {map} from 'rxjs/operators';
import {ReferenceTag} from '../../../domain/reference-tag';
import {WikiPageDto} from '../create-wiki-page/wiki-page-dto';

export interface FetchWikiPage {
  withReferenceTag(referenceTag: ReferenceTag): Observable<WikiPage>;
}

@Injectable({
  providedIn: 'root'
})
export class FetchWikiPageGateway implements FetchWikiPage {
  private url = `${environment.baseUrl}/sandbox/wiki/pages/`;

  constructor(private http: HttpClient) {
  }


  withReferenceTag(referenceTag: ReferenceTag): Observable<WikiPage> {
    return this.http.get<WikiPageDto>(this.url + '/' + referenceTag.referenceTag)
      .pipe(
        map(response => WikiPage.from(response))
      );
  }
}
