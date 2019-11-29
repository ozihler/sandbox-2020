import {Injectable} from '@angular/core';
import {WikiPageUpdateRequest} from "./wiki-page-update-request";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {map} from "rxjs/operators";
import {WikiPageUpdateResponse} from "./wiki-page-update-response";
import {WikiPage} from "./wiki-page";


@Injectable({
  providedIn: 'root'
})
export class CreateWikiPageService {

  constructor(private http: HttpClient) {
  }

  updateWith(wikiPageUpdateRequest: WikiPageUpdateRequest): Observable<WikiPage> {
    return this.http.post<WikiPageUpdateResponse>
    (environment.baseUrl + "/sandbox/wiki/page/title", wikiPageUpdateRequest)
      .pipe(
        map(response => WikiPage.from(response))
      );

  }
}
