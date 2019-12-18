import {Injectable} from '@angular/core';
import {CreateWikiPageFromTitleRequest} from './create-wiki-page-from-title-request';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../../../environments/environment';
import {map} from 'rxjs/operators';
import {WikiPageDto} from './wiki-page-dto';
import {WikiPage} from '../../../domain/wiki-page';
import {CreateWikiPageFromTitle} from '../../../application.use_case/create-wiki-page.port';
import {Title} from '../../../domain/title';


@Injectable({
  providedIn: 'root'
})
export class CreateWikiPageFromTitleUseCase implements CreateWikiPageFromTitle {

  private url = `${environment.baseUrl}/sandbox/wiki/pages/title`;

  constructor(private http: HttpClient) {
  }

  from(title: Title): Observable<WikiPage> {
    const request = new CreateWikiPageFromTitleRequest(title);

    return this.http.post<WikiPageDto>(this.url, request)
      .pipe(map(response => WikiPage.from(response)));
  }

}
