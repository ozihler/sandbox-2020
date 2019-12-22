import {TestBed} from '@angular/core/testing';

import {FetchWikiPageGateway} from './fetch-wiki-page.service';

describe('FetchWikiPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FetchWikiPageGateway = TestBed.get(FetchWikiPageGateway);
    expect(service).toBeTruthy();
  });
});
