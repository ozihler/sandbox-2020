import {Injectable} from '@angular/core';
import {WikiPageUpdateRequest} from "./wiki/wiki-page-update-request";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {map} from "rxjs/operators";

class WikiPageUpdateResponse {
  id: number;
  title: string;
  body: string;
  referenceTags: [];
}

class WikiPage {
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

@Injectable({
  providedIn: 'root'
})
export class CreateWikiPageService {

  constructor(private http: HttpClient) {
  }

  updateWith(wikiPageUpdateRequest: WikiPageUpdateRequest): Observable<WikiPage> {
    return this.http.post<WikiPageUpdateResponse>
    (environment.baseUrl + "/sandbox/wiki", wikiPageUpdateRequest)
      .pipe(
        map(response => WikiPage.from(response))
      );

  }
}
