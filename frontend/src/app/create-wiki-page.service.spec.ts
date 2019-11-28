import {TestBed} from '@angular/core/testing';

import {CreateWikiPageService} from './create-wiki-page.service';

describe('CreateWikiPageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateWikiPageService = TestBed.get(CreateWikiPageService);
    expect(service).toBeTruthy();
  });
});
